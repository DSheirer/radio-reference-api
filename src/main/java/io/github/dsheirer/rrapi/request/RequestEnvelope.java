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

package io.github.dsheirer.rrapi.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Base SOAP request envelope
 */
@JacksonXmlRootElement(localName = "Envelope")
public class RequestEnvelope
{
    private RequestHeader mRequestHeader = new RequestHeader();
    private RequestBody mRequestBody;

    /**
     * Constructs an instance
     */
    public RequestEnvelope()
    {
    }

    /**
     * Constructs an instance
     * @param requestBody to stuff in the envelope
     */
    public RequestEnvelope(RequestBody requestBody)
    {
        mRequestBody = requestBody;
    }

    /**
     * Request header
     * @return header
     */
    @JacksonXmlProperty(localName = "Header")
    public RequestHeader getRequestHeader()
    {
        return mRequestHeader;
    }

    /**
     * Sets the request header
     * @param requestHeader to set
     */
    public void setRequestHeader(RequestHeader requestHeader)
    {
        mRequestHeader = requestHeader;
    }

    /**
     * Request body
     * @return body
     */
    @JacksonXmlProperty(localName = "Body")
    public RequestBody getRequestBody()
    {
        return mRequestBody;
    }

    /**
     * Sets the request body
     * @param requestBody to set
     */
    public void setRequestBody(RequestBody requestBody)
    {
        mRequestBody = requestBody;
    }

    /**
     * XML as string
     * @return string xml
     * @throws JsonProcessingException if there is an error
     */
    public String toXmlString() throws JsonProcessingException
    {
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper mapper = new XmlMapper(module);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(this);
    }

    /**
     * Creates an envelope from the request body
     * @param requestBody to stuff in the envelope
     * @return envelope
     */
    public static RequestEnvelope create(RequestBody requestBody)
    {
        return new RequestEnvelope(requestBody);
    }
}
