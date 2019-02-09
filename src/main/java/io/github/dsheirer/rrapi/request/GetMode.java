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
 * Get mode(s)
 */
public class GetMode extends RequestBody
{
    private Integer mModeId;

    /**
     * Constructs the get mode request
     * @param authorizationInformation for the user
     * @param modeId to request
     */
    public GetMode(AuthorizationInformation authorizationInformation, int modeId)
    {
        super(authorizationInformation);
        mModeId = modeId;
    }

    /**
     * Constructs a get ALL modes request
     * @param authorizationInformation
     */
    public GetMode(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    @JacksonXmlProperty(localName = "request")
    public Integer getModeId()
    {
        return mModeId;
    }

    public void setModeId(int modeId)
    {
        mModeId = modeId;
    }

    /**
     * Creates a get specific mode request
     * @param authorizationInformation
     * @param modeId
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int modeId)
    {
        return RequestBody.create(new GetMode(authorizationInformation, modeId));
    }

    /**
     * Creates a get ALL modes request
     * @param authorizationInformation
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetMode(authorizationInformation));
    }
}
