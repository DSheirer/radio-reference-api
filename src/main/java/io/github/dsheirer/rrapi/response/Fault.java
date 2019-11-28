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

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * SOAP Fault response body
 */
public class Fault extends ResponseBody
{
    private String mFaultCode;
    private String mFaultActor;
    private String mFaultString;
    private String mDetail;

    /**
     * Default constructor
     */
    public Fault()
    {
        //Needed by Jackson deserializer
    }

    /**
     * Fault code
     * @return code
     */
    @JacksonXmlProperty(localName = "faultcode")
    public String getFaultCode()
    {
        return mFaultCode;
    }

    public void setFaultCode(String faultCode)
    {
        mFaultCode = faultCode;
    }

    /**
     * Fault actor
     * @return actor
     */
    @JacksonXmlProperty(localName = "faultactor")
    public String getFaultActor()
    {
        return mFaultActor;
    }

    public void setFaultActor(String faultActor)
    {
        mFaultActor = faultActor;
    }

    /**
     * Fault string (ie message)
     * @return message
     */
    @JacksonXmlProperty(localName = "faultstring")
    public String getFaultString()
    {
        return mFaultString;
    }

    public void setFaultString(String faultString)
    {
        mFaultString = faultString;
    }

    /**
     * Fault Detail
     * @return detail
     */
    @JacksonXmlProperty(localName = "detail")
    public String getDetail()
    {
        return mDetail;
    }

    public void setDetail(String detail)
    {
        mDetail = detail;
    }

    @Override
    public String toString()
    {
        return "Fault - Code:" + mFaultCode + " Actor:" + mFaultActor + " String:" + mFaultString + " Detail:" + mDetail;
    }
}
