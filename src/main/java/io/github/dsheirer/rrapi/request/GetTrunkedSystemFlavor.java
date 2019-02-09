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
 * Trunked System Flavor information
 */
public class GetTrunkedSystemFlavor extends RequestBody
{
    private Integer mSystemFlavorId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param stateIds to request
     */
    public GetTrunkedSystemFlavor(AuthorizationInformation authorizationInformation, int systemFlavorId)
    {
        super(authorizationInformation);
        mSystemFlavorId = systemFlavorId;
    }

    /**
     * Constructs a get ALL tags request
     * @param authorizationInformation
     */
    public GetTrunkedSystemFlavor(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    @JacksonXmlProperty(localName = "request")
    public Integer getSystemFlavorId()
    {
        return mSystemFlavorId;
    }

    public void setSystemFlavorId(int systemFlavorId)
    {
        mSystemFlavorId = systemFlavorId;
    }

    /**
     * Creates a get specific tag request
     * @param authorizationInformation
     * @param systemFlavorId
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemFlavorId)
    {
        return RequestBody.create(new GetTrunkedSystemFlavor(authorizationInformation, systemFlavorId));
    }

    /**
     * Creates a get ALL tags request
     * @param authorizationInformation
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetTrunkedSystemFlavor(authorizationInformation));
    }
}
