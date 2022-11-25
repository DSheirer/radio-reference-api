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

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.CountyRequestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * List of states supported by the service
 */
public class GetCountiesByList extends RequestBody
{
    private List<CountyRequestItem> mCountyRequestItems = new ArrayList<>();

    /**
     * Constructs the request
     * @param authorizationInformation for the user
     */
    public GetCountiesByList(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Requested counties
     * @return counties
     */
    @JacksonXmlElementWrapper(localName = "request")
    @JacksonXmlProperty(localName = "item")
    public List<CountyRequestItem> getCountyRequestItems()
    {
        return mCountyRequestItems;
    }

    /**
     * Sets the request items
     * @param countyRequestItems to request
     */
    public void setCountyRequestItems(List<CountyRequestItem> countyRequestItems)
    {
        mCountyRequestItems = countyRequestItems;
    }

    /**
     * Adds a county to the request list
     * @param countyId to add
     */
    public void addCounty(int countyId)
    {
        mCountyRequestItems.add(CountyRequestItem.create(countyId));
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation of the requester
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetCountiesByList(authorizationInformation));
    }
}
