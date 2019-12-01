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
 * Get metro(s)
 */
public class GetMetros extends RequestBody
{
    private Integer mMetroId;

    /**
     * Constructs the get mode request
     * @param authorizationInformation for the user
     * @param metroId to request
     */
    public GetMetros(AuthorizationInformation authorizationInformation, int metroId)
    {
        super(authorizationInformation);
        mMetroId = metroId;
    }

    /**
     * Constructs a get ALL modes request
     * @param authorizationInformation credentials
     */
    public GetMetros(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Metro id
     * @return id
     */
    @JacksonXmlProperty(localName = "request")
    public Integer getMetroId()
    {
        return mMetroId;
    }

    /**
     * Sets the metro id to query
     * @param metroId to query
     */
    public void setMetroId(int metroId)
    {
        mMetroId = metroId;
    }

    /**
     * Creates a get specific metro request
     * @param authorizationInformation credentials
     * @param modeId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int modeId)
    {
        return RequestBody.create(new GetMetros(authorizationInformation, modeId));
    }

    /**
     * Creates a get ALL metros request
     * @param authorizationInformation credentials
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetMetros(authorizationInformation));
    }
}
