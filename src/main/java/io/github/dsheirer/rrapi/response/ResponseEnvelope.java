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

package io.github.dsheirer.rrapi.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Base response envelope
 */
@JacksonXmlRootElement(localName = "Envelope")
public class ResponseEnvelope
{
    private ResponseBody mResponseBody;
    private String mEncodingStyle;

    /**
     * Constructs an instance
     */
    public ResponseEnvelope()
    {
        //Empty deserializer constructor
    }

    /**
     * Response body/payload
     * @return body
     */
    @JacksonXmlProperty(localName = "Body")
    public ResponseBody getResponseBody()
    {
        return mResponseBody;
    }

    /**
     * Sets the response body
     * @param responseBody to set
     */
    public void setResponseBody(ResponseBody responseBody)
    {
        mResponseBody = responseBody;
    }

    /**
     * Encoding style
     * @return style
     */
    @JacksonXmlProperty(localName = "encodingStyle")
    public String getEncodingStyle()
    {
        return mEncodingStyle;
    }

    /**
     * Sets the encoding style
     * @param style to set
     */
    public void setEncodingStyle(String style)
    {
        mEncodingStyle = style;
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
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.writeValueAsString(this);
    }
}
