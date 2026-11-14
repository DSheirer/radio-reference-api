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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Request for the {@code getTrsDetails} operation: details of a trunked radio system.
 *
 * <p>Parameters, in the order the service expects them:</p>
 * <ul>
 *     <li>{@code sid} - trunked radio system id</li>
 *     <li>{@code authInfo} - credentials, from {@link RequestBody}</li>
 * </ul>
 *
 * <p>Sent by {@link io.github.dsheirer.rrapi.RadioReferenceService#getSystemInformation(int)}.</p>
 */
//RPC parameters are matched by position, so they are sent in WSDL order
@JsonPropertyOrder({"sid", "authInfo"})
public class GetSystemInformation extends RequestBody
{
    private int mSystemId;

    /**
     * Constructs an instance
     * @param authorizationInformation for the requester
     * @param systemId to request
     */
    public GetSystemInformation(AuthorizationInformation authorizationInformation, int systemId)
    {
        super(authorizationInformation);
        mSystemId = systemId;
    }

    /**
     * SystemID
     * @return system ID
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system ID
     * @param systemId to set
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation for the requeseter
     * @param systemId to request
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemId)
    {
        return RequestBody.create(new GetSystemInformation(authorizationInformation, systemId));
    }
}
