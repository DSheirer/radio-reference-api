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
 * Get type information
 */
public class GetTypes extends RequestBody
{
    private Integer mSystemTypeId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param systemTypeId to request
     */
    public GetTypes(AuthorizationInformation authorizationInformation, int systemTypeId)
    {
        super(authorizationInformation);
        mSystemTypeId = systemTypeId;
    }

    /**
     * Constructs a get ALL tags request
     * @param authorizationInformation credentials
     */
    public GetTypes(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * System Type id
     * @return id
     */
    @JacksonXmlProperty(localName = "request")
    public Integer getTypeId()
    {
        return mSystemTypeId;
    }

    /**
     * Sets the system type id
     * @param systemTypeId to query
     */
    public void setTypeId(int systemTypeId)
    {
        mSystemTypeId = systemTypeId;
    }

    /**
     * Creates a get specific type request
     * @param authorizationInformation credentials
     * @param typeId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int typeId)
    {
        return RequestBody.create(new GetTypes(authorizationInformation, typeId));
    }

    /**
     * Creates a get ALL types request
     * @param authorizationInformation credentials
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetTypes(authorizationInformation));
    }
}
