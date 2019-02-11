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
 * Get FCC callsign information
 */
public class FccGetCallsign extends RequestBody
{
    private String mCallsign;

    /**
     * Constructs the request
     *
     * @param authorizationInformation for the user
     * @param callsign to lookup
     */
    public FccGetCallsign(AuthorizationInformation authorizationInformation, String callsign)
    {
        super(authorizationInformation);
        mCallsign = callsign;
    }

    /**
     * Callsign being queried
     * @return callsign
     */
    @JacksonXmlProperty(localName = "request")
    public String getCallsign()
    {
        return mCallsign;
    }

    /**
     * Sets the callsign being queried
     * @param callsign to query
     */
    public void setCallsign(String callsign)
    {
        mCallsign = callsign;
    }

    /**
     * Creates a request envelope using the auth info and the callsign
     * @param authorizationInformation credentials
     * @param callsign to query
     * @return request envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, String callsign)
    {
        return RequestBody.create(new FccGetCallsign(authorizationInformation, callsign));
    }
}
