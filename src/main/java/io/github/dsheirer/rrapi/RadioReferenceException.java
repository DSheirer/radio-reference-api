/*
 * *****************************************************************************
 * Copyright (C) 2019 Dennis Sheirer
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

import io.github.dsheirer.rrapi.response.Fault;
import java.io.IOException;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.util.Locale;
import java.util.Optional;

/**
 * Radio reference API exception.
 *
 * {@link #getErrorType()} categorizes the error so that callers can decide how to react, and {@link #isRetryable()}
 * indicates if the same request could succeed later.
 */
public class RadioReferenceException extends Exception
{
    /**
     * Error categories.  The service reports every error as HTTP 500 with a SOAP fault code, or with an empty body:
     *
     * <ul>
     *     <li>AUTH - invalid user name or password</li>
     *     <li>APP - invalid or empty application key</li>
     *     <li>SERVER - invalid parameters, e.g. "Invalid County ID", and server side errors</li>
     *     <li>SOAP-ENV:Client - malformed request, or an operation that the requested API version doesn't define</li>
     * </ul>
     */
    public enum ErrorType
    {
        /**
         * Invalid user name or password (fault code AUTH).  Retrying doesn't help: ask the user for credentials.
         */
        AUTHENTICATION(false),

        /**
         * Invalid or empty application key (fault code APP).  An application configuration error.
         */
        APPLICATION_KEY(false),

        /**
         * The account doesn't have a current premium subscription, which the service requires.  Detected from the
         * fault text, since the service doesn't document a fault code for it.
         */
        SUBSCRIPTION(false),

        /**
         * The service rejected the request parameters, e.g. an id that doesn't exist, or the request itself.
         */
        INVALID_REQUEST(false),

        /**
         * Too many requests (HTTP 429).  Retry after {@link #getRetryAfter()} when the service provides it.
         */
        RATE_LIMITED(true),

        /**
         * The service failed or returned a response that isn't a valid SOAP response, e.g. an empty body or an HTML
         * error page.  Usually temporary.
         */
        SERVICE_ERROR(true),

        /**
         * The connection or the response didn't complete within the timeout.
         */
        TIMEOUT(true),

        /**
         * The request couldn't reach the service, e.g. no network or the connection was reset.
         */
        NETWORK(true),

        /**
         * The calling thread was interrupted, e.g. a cancelled {@link RadioReferenceService#async} call.
         */
        INTERRUPTED(false),

        /**
         * Any other error.
         */
        UNKNOWN(false);

        private final boolean mRetryable;

        ErrorType(boolean retryable)
        {
            mRetryable = retryable;
        }

        /**
         * Indicates if the same request could succeed when tried again later
         * @return true for errors that are usually temporary
         */
        public boolean isRetryable()
        {
            return mRetryable;
        }
    }

    /**
     * HTTP status code
     */
    private Integer mHttpStatusCode;

    /**
     * Fault
     */
    private Fault mFault;

    /**
     * Category of the error
     */
    private final ErrorType mErrorType;

    /**
     * Delay that the service requested before retrying, or null
     */
    private Duration mRetryAfter;

    /**
     * Constructs an instance with the error type derived from the cause: TIMEOUT, INTERRUPTED, NETWORK for other
     * I/O errors, otherwise UNKNOWN
     * @param message to report
     * @param throwable cause
     */
    public RadioReferenceException(String message, Throwable throwable)
    {
        this(typeOf(throwable), message, throwable);
    }

    /**
     * Constructs an instance with error type UNKNOWN
     * @param message to report
     */
    public RadioReferenceException(String message)
    {
        this(ErrorType.UNKNOWN, message);
    }

    /**
     * Constructs an instance with the error type derived from the fault and the HTTP status code
     * @param message to report
     * @param httpStatusCode for the request
     * @param fault being reported
     */
    public RadioReferenceException(String message, int httpStatusCode, Fault fault)
    {
        this(typeOf(fault, httpStatusCode), message, httpStatusCode, fault);
    }

    /**
     * Constructs an instance
     * @param errorType category of the error
     * @param message to report
     */
    public RadioReferenceException(ErrorType errorType, String message)
    {
        super(message);
        mErrorType = errorType != null ? errorType : ErrorType.UNKNOWN;
    }

    /**
     * Constructs an instance
     * @param errorType category of the error
     * @param message to report
     * @param throwable cause
     */
    public RadioReferenceException(ErrorType errorType, String message, Throwable throwable)
    {
        super(message, throwable);
        mErrorType = errorType != null ? errorType : ErrorType.UNKNOWN;
    }

    /**
     * Constructs an instance
     * @param errorType category of the error
     * @param message to report
     * @param httpStatusCode of the response
     * @param fault being reported, or null
     */
    public RadioReferenceException(ErrorType errorType, String message, int httpStatusCode, Fault fault)
    {
        super(message);
        mErrorType = errorType != null ? errorType : ErrorType.UNKNOWN;
        mHttpStatusCode = httpStatusCode;
        mFault = fault;
    }

    /**
     * Category of the error
     * @return error type, never null
     */
    public ErrorType getErrorType()
    {
        return mErrorType;
    }

    /**
     * Indicates if the same request could succeed when tried again later, e.g. after a timeout or a service error.
     * False for errors that need a change first, e.g. invalid credentials or an invalid id.
     * @return true if retrying could help
     */
    public boolean isRetryable()
    {
        return mErrorType.isRetryable();
    }

    /**
     * Delay that the service requested before retrying, from the HTTP Retry-After header
     * @return delay, or empty when the service didn't specify one
     */
    public Optional<Duration> getRetryAfter()
    {
        return Optional.ofNullable(mRetryAfter);
    }

    /**
     * Sets the delay that the service requested before retrying
     * @param retryAfter delay or null
     * @return this exception
     */
    public RadioReferenceException withRetryAfter(Duration retryAfter)
    {
        mRetryAfter = retryAfter;
        return this;
    }

    /**
     * HTTP status code
     * @return status code
     */
    public Integer getHttpStatusCode()
    {
        return mHttpStatusCode;
    }

    /**
     * Indicates if this exception has an HTTP status code
     * @return true if non-null
     */
    public boolean hasHttpStatusCode()
    {
        return mHttpStatusCode != null;
    }

    /**
     * SOAP fault
     * @return fault
     */
    public Fault getFault()
    {
        return mFault;
    }

    /**
     * Indicates if this exception contains a SOAP fault
     * @return true if non-null
     */
    public boolean hasFault()
    {
        return mFault != null;
    }

    /**
     * Error type for an exception thrown while sending a request
     */
    static ErrorType typeOf(Throwable throwable)
    {
        if(throwable instanceof HttpTimeoutException)
        {
            return ErrorType.TIMEOUT;
        }
        if(throwable instanceof InterruptedException)
        {
            return ErrorType.INTERRUPTED;
        }
        if(throwable instanceof IOException)
        {
            return ErrorType.NETWORK;
        }
        return ErrorType.UNKNOWN;
    }

    /**
     * Error type for a SOAP fault returned by the service
     * @param fault returned, or null if the response had no fault
     * @param httpStatusCode of the response
     */
    static ErrorType typeOf(Fault fault, int httpStatusCode)
    {
        String code = fault != null && fault.getFaultCode() != null ? fault.getFaultCode().trim() : "";
        String text = fault != null && fault.getFaultString() != null ? fault.getFaultString().toLowerCase(Locale.ROOT) : "";

        if(httpStatusCode == 429 || text.contains("too many requests") || text.contains("rate limit"))
        {
            return ErrorType.RATE_LIMITED;
        }

        //Checked before the codes because the service doesn't document a code for subscription errors
        if(text.contains("premium") || text.contains("subscription") || text.contains("subscriber"))
        {
            return ErrorType.SUBSCRIPTION;
        }

        String localCode = code.contains(":") ? code.substring(code.indexOf(':') + 1) : code;

        return switch(localCode.toUpperCase(Locale.ROOT))
        {
            case "AUTH" -> ErrorType.AUTHENTICATION;
            case "APP" -> ErrorType.APPLICATION_KEY;
            case "CLIENT" -> ErrorType.INVALID_REQUEST;
            //The service reports invalid parameters with fault code SERVER, e.g. "Invalid County ID"
            case "SERVER" -> text.startsWith("invalid") ? ErrorType.INVALID_REQUEST : ErrorType.SERVICE_ERROR;
            default -> fault == null && httpStatusCode >= 400 && httpStatusCode < 500 ? ErrorType.INVALID_REQUEST :
                ErrorType.SERVICE_ERROR;
        };
    }
}
