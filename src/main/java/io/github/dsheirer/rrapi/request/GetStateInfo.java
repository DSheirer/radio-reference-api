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
public class GetStateInfo extends RequestBody
{
    private int mStateId;

    /**
     * Constructs the request
     * @param authorizationInformation for the user
     * @param stateIds to request
     */
    public GetStateInfo(AuthorizationInformation authorizationInformation, int stateId)
    {
        super(authorizationInformation);
        mStateId = stateId;
    }

    @JacksonXmlProperty(localName = "request")
    public int getStateId()
    {
        return mStateId;
    }

    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int stateId)
    {
        return RequestBody.create(new GetStateInfo(authorizationInformation, stateId));
    }
}
