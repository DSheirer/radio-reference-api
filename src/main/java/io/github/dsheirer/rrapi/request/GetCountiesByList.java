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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.CountyRequestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * List of counties supported by the service
 */
//RPC parameters are matched by position, so they are sent in WSDL order
@JsonPropertyOrder({"request", "authInfo"})
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
     * Request parameter, sent as a SOAP encoded array of ctid items
     */
    @JacksonXmlProperty(localName = "request")
    public RequestArray<CountyRequestItem> getRequest()
    {
        return new RequestArray<>("tns:ctid", mCountyRequestItems);
    }

    /**
     * Requested counties
     * @return counties
     */
    @JsonIgnore
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

    /**
     * Creates the request envelope
     * @param authorizationInformation of the requester
     * @param countyIds to request
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, List<Integer> countyIds)
    {
        GetCountiesByList body = new GetCountiesByList(authorizationInformation);
        for(int countyId: countyIds)
        {
            body.addCounty(countyId);
        }

        return RequestBody.create(body);
    }
}
