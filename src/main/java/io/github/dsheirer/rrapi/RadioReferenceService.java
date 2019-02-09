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

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.dsheirer.rrapi.request.RequestEnvelope;
import io.github.dsheirer.rrapi.response.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RadioReferenceService
{
    private final static Logger mLog = LoggerFactory.getLogger(RadioReferenceService.class);

    public static final String API_VERSION_15 = "15";
    public static final String RADIO_REFERENCE_API_URL = "http://api.radioreference.com/soap2/";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String XML_CONTENT_TYPE = "text/xml;charset=UTF-8";
    public static final String USER_AGENT = "User-Agent";
    public static final String USER_AGENT_RRAPI = "io.github.dsheirer.rrapi";

    private HttpClient mHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public RadioReferenceService()
    {
    }

    public ResponseEnvelope submitSync(RequestEnvelope requestEnvelope) throws IOException, InterruptedException
    {
        String xml;

        xml = requestEnvelope.toXmlString();

        if(xml != null)
        {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RADIO_REFERENCE_API_URL))
                .header(CONTENT_TYPE, XML_CONTENT_TYPE)
                .header(USER_AGENT, USER_AGENT_RRAPI)
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

            HttpResponse<String> response = mHttpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200)
            {
                return deserialize(response.body());
            }
            else
            {
                throw new IOException("Http Error: " + response.statusCode());
            }
        }

        return null;
    }

    public static ResponseEnvelope deserialize(String xml) throws IOException
    {
        if(xml != null)
        {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(xml, ResponseEnvelope.class);
        }

        return null;
    }
}
