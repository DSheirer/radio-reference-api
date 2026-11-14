/*
 * *****************************************************************************
 * Copyright (C) 2026 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.rrapi;

import io.github.dsheirer.rrapi.RadioReferenceException.ErrorType;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import java.io.IOException;
import java.net.ConnectException;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Error categories for the responses the service returns.  The AUTH and SOAP-ENV:Client faults are recorded
 * responses.  The APP and SERVER faults use the same envelope with the codes and messages the service returned for a
 * wrong application key and invalid ids.
 */
public class ErrorClassificationTest
{
    private static final AuthorizationInformation AUTH = new AuthorizationInformation("key", "user", "password");
    private static String sAuthFault;
    private static String sClientFault;

    @BeforeAll
    static void load() throws Exception
    {
        sAuthFault = Files.readString(Path.of("src/test/resources/responses/fault-auth.xml"), StandardCharsets.ISO_8859_1);
        sClientFault = Files.readString(Path.of("src/test/resources/responses/fault-client.xml"));
    }

    /**
     * The recorded AUTH fault envelope with another fault code and message
     */
    private static String fault(String code, String message)
    {
        return sAuthFault.replace(">AUTH<", ">" + code + "<")
            .replaceAll("<faultstring([^>]*)>[^<]*</faultstring>", "<faultstring$1>" + message + "</faultstring>");
    }

    private static RadioReferenceException errorFor(SoapTransport transport)
    {
        return Assertions.assertThrows(RadioReferenceException.class,
            () -> new RadioReferenceService(AUTH, transport).getCountyInfo(1825));
    }

    private static RadioReferenceException errorFor(int status, String body)
    {
        return errorFor(xml -> new SoapTransport.Response(status, body));
    }

    @Test
    void invalidCredentials()
    {
        RadioReferenceException e = errorFor(500, sAuthFault);

        Assertions.assertEquals(ErrorType.AUTHENTICATION, e.getErrorType());
        Assertions.assertFalse(e.isRetryable());
        Assertions.assertEquals("AUTH", e.getFault().getFaultCode(), "fault code that existing callers check");
        Assertions.assertEquals(500, e.getHttpStatusCode());
        Assertions.assertTrue(e.getMessage().startsWith("AUTH: Invalid Username or Password"), e.getMessage());
    }

    @Test
    void invalidApplicationKey()
    {
        RadioReferenceException e = errorFor(500, fault("APP", "Invalid or empty application key specified"));

        Assertions.assertEquals(ErrorType.APPLICATION_KEY, e.getErrorType());
        Assertions.assertFalse(e.isRetryable());
    }

    @Test
    void invalidIdIsAnInvalidRequest()
    {
        RadioReferenceException county = errorFor(500, fault("SERVER", "Invalid County ID"));
        RadioReferenceException agency = errorFor(500, fault("SERVER", "Invalid Agency ID"));

        Assertions.assertEquals(ErrorType.INVALID_REQUEST, county.getErrorType());
        Assertions.assertEquals(ErrorType.INVALID_REQUEST, agency.getErrorType());
        Assertions.assertFalse(county.isRetryable());
        Assertions.assertEquals("SERVER: Invalid County ID", county.getMessage());
    }

    @Test
    void otherServerFaultIsARetryableServiceError()
    {
        RadioReferenceException e = errorFor(500, fault("SERVER", "Database connection failed"));

        Assertions.assertEquals(ErrorType.SERVICE_ERROR, e.getErrorType());
        Assertions.assertTrue(e.isRetryable());
    }

    @Test
    void clientFaultIsAnInvalidRequest()
    {
        RadioReferenceException e = errorFor(500, sClientFault);

        Assertions.assertEquals(ErrorType.INVALID_REQUEST, e.getErrorType());
        Assertions.assertEquals("SOAP-ENV:Client", e.getFault().getFaultCode());
        Assertions.assertTrue(e.getMessage().contains("is not defined in the WSDL"), e.getMessage());
    }

    @Test
    void subscriptionFault()
    {
        RadioReferenceException e = errorFor(500, fault("AUTH", "A current Premium Subscription is required"));

        Assertions.assertEquals(ErrorType.SUBSCRIPTION, e.getErrorType());
        Assertions.assertFalse(e.isRetryable());
    }

    @Test
    void faultWithHttp200IsStillAnError()
    {
        RadioReferenceException e = errorFor(200, sAuthFault);
        Assertions.assertEquals(ErrorType.AUTHENTICATION, e.getErrorType());
    }

    @Test
    void emptyErrorResponseIsARetryableServiceError()
    {
        //The service returns HTTP 500 with an empty body for malformed requests
        RadioReferenceException e = errorFor(500, "");

        Assertions.assertEquals(ErrorType.SERVICE_ERROR, e.getErrorType());
        Assertions.assertTrue(e.isRetryable());
        Assertions.assertTrue(e.getMessage().contains("HTTP 500 with an empty response"), e.getMessage());
    }

    @Test
    void htmlErrorPageIsAServiceErrorWithShortMessage()
    {
        String page = "<html><body>" + "Service Unavailable ".repeat(500) + "</body></html>";
        RadioReferenceException e = errorFor(503, page);

        Assertions.assertEquals(ErrorType.SERVICE_ERROR, e.getErrorType());
        Assertions.assertEquals(503, e.getHttpStatusCode());
        Assertions.assertTrue(e.getMessage().length() < 400, "message excerpt, not the whole page");
    }

    @Test
    void rateLimitedWithRetryAfter()
    {
        RadioReferenceException e = errorFor(xml -> new SoapTransport.Response(429, "", Duration.ofSeconds(30)));

        Assertions.assertEquals(ErrorType.RATE_LIMITED, e.getErrorType());
        Assertions.assertTrue(e.isRetryable());
        Assertions.assertEquals(Duration.ofSeconds(30), e.getRetryAfter().orElseThrow());
    }

    @Test
    void otherClientHttpErrorIsAnInvalidRequest()
    {
        Assertions.assertEquals(ErrorType.INVALID_REQUEST, errorFor(404, "<html>Not Found</html>").getErrorType());
    }

    @Test
    void unparseableSoapResponseIsUnknown()
    {
        //A response element the library doesn't know, e.g. after an API change.  Retrying won't help.
        String response = "<?xml version=\"1.0\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "<SOAP-ENV:Body><getCountyInfoResponse><return><newField>1</newField></return></getCountyInfoResponse>" +
            "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
        RadioReferenceException e = errorFor(200, response);

        Assertions.assertEquals(ErrorType.UNKNOWN, e.getErrorType());
        Assertions.assertFalse(e.isRetryable());
        Assertions.assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    void emptySuccessResponseIsAServiceError()
    {
        Assertions.assertEquals(ErrorType.SERVICE_ERROR, errorFor(200, "").getErrorType());
    }

    @Test
    void transportErrors()
    {
        Assertions.assertEquals(ErrorType.TIMEOUT, errorFor(xml -> {
            throw new HttpTimeoutException("request timed out");
        }).getErrorType());

        RadioReferenceException network = errorFor(xml -> {
            throw new ConnectException("Connection refused");
        });
        Assertions.assertEquals(ErrorType.NETWORK, network.getErrorType());
        Assertions.assertTrue(network.isRetryable());
        Assertions.assertTrue(network.getMessage().contains("Connection refused"), network.getMessage());

        RadioReferenceException interrupted = errorFor(xml -> {
            throw new InterruptedException();
        });
        Assertions.assertEquals(ErrorType.INTERRUPTED, interrupted.getErrorType());
        Assertions.assertFalse(interrupted.isRetryable());
        Assertions.assertTrue(Thread.interrupted(), "interrupt flag is preserved (and cleared here)");
    }

    @Test
    void libraryValidationErrorsAreInvalidRequests() throws Exception
    {
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> new SoapTransport.Response(200,
            Files.readString(Path.of("src/test/resources/responses/getCountryList.xml"))));

        Assertions.assertEquals(ErrorType.INVALID_REQUEST,
            Assertions.assertThrows(RadioReferenceException.class, () -> service.getCountry(999)).getErrorType());
        Assertions.assertEquals(ErrorType.INVALID_REQUEST,
            Assertions.assertThrows(RadioReferenceException.class, () -> service.getFccRadioServiceCode("A")).getErrorType());
        Assertions.assertEquals(ErrorType.INVALID_REQUEST,
            Assertions.assertThrows(RadioReferenceException.class, () -> new RadioReferenceService(null)).getErrorType());
    }

    @Test
    void legacyConstructorsDeriveTheErrorType()
    {
        Assertions.assertEquals(ErrorType.UNKNOWN, new RadioReferenceException("message").getErrorType());
        Assertions.assertEquals(ErrorType.NETWORK, new RadioReferenceException("message", new IOException()).getErrorType());
        Assertions.assertEquals(ErrorType.TIMEOUT,
            new RadioReferenceException("message", new HttpTimeoutException("timeout")).getErrorType());
    }

    @Test
    void parsesRetryAfterHeader()
    {
        Assertions.assertEquals(Duration.ofSeconds(120), RadioReferenceService.parseRetryAfter("120"));
        Assertions.assertEquals(Duration.ZERO, RadioReferenceService.parseRetryAfter("Wed, 21 Oct 2015 07:28:00 GMT"),
            "a date in the past");

        String inOneHour = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC).plusHours(1));
        Duration delay = RadioReferenceService.parseRetryAfter(inOneHour);
        Assertions.assertTrue(delay.toMinutes() >= 58 && delay.toMinutes() <= 60, delay.toString());

        Assertions.assertNull(RadioReferenceService.parseRetryAfter("soon"));
        Assertions.assertNull(RadioReferenceService.parseRetryAfter(""));
    }
}
