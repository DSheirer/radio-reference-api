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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.dsheirer.rrapi.request.GetUserData;
import io.github.dsheirer.rrapi.request.GetUserFeedBroadcasts;
import io.github.dsheirer.rrapi.request.RequestEnvelope;
import io.github.dsheirer.rrapi.response.GetUserDataResponse;
import io.github.dsheirer.rrapi.response.GetUserFeedBroadcastsResponse;
import io.github.dsheirer.rrapi.response.ResponseEnvelope;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.UserFeedBroadcast;
import io.github.dsheirer.rrapi.type.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

/**
 * Java client for accessing the radioreference.com web services API
 *
 * Uses the Java 11 HttpClient with no dependencies on legacy Axis 1.4 libraries.
 */
public class RadioReferenceService
{
    private final static Logger mLog = LoggerFactory.getLogger(RadioReferenceService.class);

    public static final String API_VERSION_15 = "15";
    private static final String RADIO_REFERENCE_API_URL = "http://api.radioreference.com/soap2/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String XML_CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_RRAPI = "io.github.dsheirer.rrapi";

    private HttpClient mHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private AuthorizationInformation mAuthorizationInformation;

    /**
     * Constructs an instance of the service
     *
     * @param authorizationInformation with username and password for accessing the web service
     * @throws RadioReferenceException if there are any errors while accessing the service
     */
    public RadioReferenceService(AuthorizationInformation authorizationInformation) throws RadioReferenceException
    {
        if(authorizationInformation == null)
        {
            throw new RadioReferenceException("Authorization information cannot be null");
        }

        mAuthorizationInformation = authorizationInformation;
    }

    /**
     * User information containing user name and account expiration date
     *
     * @return user information instance or null
     * @throws RadioReferenceException if there is an error
     */
    public UserInfo getUserInfo() throws RadioReferenceException
    {
        RequestEnvelope request = GetUserData.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetUserDataResponse)
        {
            return ((GetUserDataResponse)response.getResponseBody()).getUserInfo();
        }

        return null;
    }

    /**
     * List of icecast audio streaming feed configurations that the user is authorized to provide to broadcastify.com
     *
     * @return list of broadcastify.com feed configurations or an empty list
     * @throws RadioReferenceException if there is an error
     */
    public List<UserFeedBroadcast> getUserFeeds() throws RadioReferenceException
    {
        RequestEnvelope request = GetUserFeedBroadcasts.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(request != null && response.getResponseBody() instanceof GetUserFeedBroadcastsResponse)
        {
            return ((GetUserFeedBroadcastsResponse)response.getResponseBody()).getUserFeedBroadcasts();
        }

        return Collections.emptyList();
    }

    /**
     * Submits the request envelope and returns a response envelope.
     *
     * @param requestEnvelope to submit
     * @return response envelope
     * @throws IOException if there are any HTTP or envelope xml parsing errors
     * @throws InterruptedException if there is an error
     */
    private ResponseEnvelope submitSync(RequestEnvelope requestEnvelope) throws RadioReferenceException
    {
        String xml;

        try
        {
            xml = requestEnvelope.toXmlString();
        }
        catch(JsonProcessingException jpe)
        {
            throw new RadioReferenceException("Error serializing request envelope to XML", jpe);
        }

        if(xml != null)
        {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RADIO_REFERENCE_API_URL))
                .header(CONTENT_TYPE, XML_CONTENT_TYPE)
                .header(USER_AGENT, USER_AGENT_RRAPI)
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

            HttpResponse<String> response = null;

            try
            {
                response = mHttpClient.send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch(IOException ioe)
            {
                throw new RadioReferenceException("Error submitting HTML SOAP request to web service", ioe);
            }
            catch(InterruptedException ie)
            {
                throw new RadioReferenceException("Interrupted while submitting HTML SOAP request to web service", ie);
            }

            if(response != null && response.statusCode() == 200)
            {
                try
                {
                    return deserialize(response.body());
                }
                catch(IOException ioe)
                {
                    throw new RadioReferenceException("Error deserializing XML response", ioe);
                }
            }
            else
            {
                throw new RadioReferenceException("Http Error: " + (response != null ? response.statusCode(): "null response"));
            }
        }

        return null;
    }

    /**
     * Deserializes the xml response using Faster Jackson xml annotations.
     * @param xml to deserialize
     * @return deserialized response envelope or null if there is nothing to deserialize
     * @throws IOException if there are any errors
     */
    private static ResponseEnvelope deserialize(String xml) throws IOException
    {
        if(xml != null)
        {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(xml, ResponseEnvelope.class);
        }

        return null;
    }
}
