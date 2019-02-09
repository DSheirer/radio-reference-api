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
 * State information
 */
public class GetAgencyInfo extends RequestBody
{
    private int mAgencyId;

    /**
     * Constructs the request
     * @param authorizationInformation for the user
     * @param stateIds to request
     */
    public GetAgencyInfo(AuthorizationInformation authorizationInformation, int agencyId)
    {
        super(authorizationInformation);
        mAgencyId = agencyId;
    }

    @JacksonXmlProperty(localName = "request")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int agencyId)
    {
        return RequestBody.create(new GetAgencyInfo(authorizationInformation, agencyId));
    }
}
