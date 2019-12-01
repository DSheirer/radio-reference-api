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

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Get FCC radio service code information
 */
public class FccGetRadioServiceCode extends RequestBody
{
    private String mCode;

    /**
     * Constructs the request
     *
     * @param authorizationInformation for the user
     * @param code to lookup
     */
    public FccGetRadioServiceCode(AuthorizationInformation authorizationInformation, String code)
    {
        super(authorizationInformation);
        mCode = code;
    }

    /**
     * Constructs the request to get all codes
     *
     * @param authorizationInformation for the user
     */
    public FccGetRadioServiceCode(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Radio service code being queried
     * @return radio service code
     */
    @JacksonXmlProperty(localName = "request")
    public String getCode()
    {
        return mCode;
    }

    /**
     * Sets the callsign being queried
     * @param code to query
     */
    public void setCode(String code)
    {
        mCode = code;
    }

    /**
     * Creates a request envelope using the auth info and the radio service code
     * @param authorizationInformation credentials
     * @param code to query
     * @return request envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, String code)
    {
        return RequestBody.create(new FccGetRadioServiceCode(authorizationInformation, code));
    }

    /**
     * Creates a request envelope using the auth info to get all codes
     * @param authorizationInformation credentials
     * @return request envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new FccGetRadioServiceCode(authorizationInformation));
    }
}
