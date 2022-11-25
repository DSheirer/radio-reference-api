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
 * Get APCO25 systems request
 */
public class GetApco25Systems extends RequestBody
{
    private String mSystemId;

    /**
     * Constructs an instance
     * @param authorizationInformation of the requester
     * @param systemId to request
     */
    public GetApco25Systems(AuthorizationInformation authorizationInformation, String systemId)
    {
        super(authorizationInformation);
        mSystemId = systemId;
    }

    /**
     * System ID
     * @return system ID
     */
    @JacksonXmlProperty(localName = "sysid")
    public String getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system ID
     * @param systemId to set
     */
    public void setSystemId(String systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation of the requester
     * @param systemId to request
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, String systemId)
    {
        return RequestBody.create(new GetApco25Systems(authorizationInformation, systemId));
    }
}
