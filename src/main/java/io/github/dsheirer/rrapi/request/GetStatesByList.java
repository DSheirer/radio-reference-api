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
import io.github.dsheirer.rrapi.type.StateRequestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * List of states supported by the service
 */
//RPC parameters are matched by position, so they are sent in WSDL order
@JsonPropertyOrder({"request", "authInfo"})
public class GetStatesByList extends RequestBody
{
    private List<StateRequestItem> mStateIdItems = new ArrayList<>();

    /**
     * Constructs the request
     * @param authorizationInformation for the user
     */
    public GetStatesByList(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Request parameter, sent as a SOAP encoded array of stid items
     */
    @JacksonXmlProperty(localName = "request")
    public RequestArray<StateRequestItem> getRequest()
    {
        return new RequestArray<>("tns:stid", mStateIdItems);
    }

    /**
     * State request item list
     * @return list
     */
    @JsonIgnore
    public List<StateRequestItem> getStateIdItems()
    {
        return mStateIdItems;
    }

    /**
     * Sets the state id items
     * @param stateIdItems to set
     */
    public void setStateIdItems(List<StateRequestItem> stateIdItems)
    {
        mStateIdItems = stateIdItems;
    }

    /**
     * Adds a state ID
     * @param stateId to add
     */
    public void addState(int stateId)
    {
        mStateIdItems.add(StateRequestItem.create(stateId));
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation for the requester
     * @param stateIds to request
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int ... stateIds)
    {
        GetStatesByList body = new GetStatesByList(authorizationInformation);
        for(int stateId: stateIds)
        {
            body.addState(stateId);
        }

        return RequestEnvelope.create(body);
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation for the requester
     * @param stateIds to request
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, List<Integer> stateIds)
    {
        GetStatesByList body = new GetStatesByList(authorizationInformation);
        for(int stateId: stateIds)
        {
            body.addState(stateId);
        }

        return RequestEnvelope.create(body);
    }
}
