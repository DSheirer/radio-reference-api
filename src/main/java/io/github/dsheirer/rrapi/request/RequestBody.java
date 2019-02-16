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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Payload for a Radio Reference SOAP envelope body element
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = FccGetCallsign.class, name = "fccGetCallsign"),
    @JsonSubTypes.Type(value = GetAgencyInfo.class, name = "getAgencyInfo"),
    @JsonSubTypes.Type(value = GetCountryInfo.class, name = "getCountryInfo"),
    @JsonSubTypes.Type(value = GetCountryList.class, name = "getCountryList"),
    @JsonSubTypes.Type(value = GetCountiesByList.class, name = "getCountiesByList"),
    @JsonSubTypes.Type(value = GetCountyInfo.class, name = "getCountyInfo"),
    @JsonSubTypes.Type(value = GetModes.class, name = "getMode"),
    @JsonSubTypes.Type(value = GetStatesByList.class, name = "getStatesByList"),
    @JsonSubTypes.Type(value = GetStateInfo.class, name = "getStateInfo"),
    @JsonSubTypes.Type(value = GetTags.class, name = "getTag"),
    @JsonSubTypes.Type(value = GetApco25Systems.class, name = "getTrsBySysid"),
    @JsonSubTypes.Type(value = GetSystemInformation.class, name = "getTrsDetails"),
    @JsonSubTypes.Type(value = GetSites.class, name = "getTrsSites"),
    @JsonSubTypes.Type(value = GetFlavors.class, name = "getTrsFlavor"),
    @JsonSubTypes.Type(value = GetTalkgroupCategories.class, name = "getTrsTalkgroupCats"),
    @JsonSubTypes.Type(value = GetTalkgroups.class, name = "getTrsTalkgroups"),
    @JsonSubTypes.Type(value = GetTypes.class, name = "getTrsType"),
    @JsonSubTypes.Type(value = GetVoices.class, name = "getTrsVoice"),
    @JsonSubTypes.Type(value = GetUserData.class, name = "getUserData"),
    @JsonSubTypes.Type(value = GetUserFeedBroadcasts.class, name = "getUserFeedBroadcasts"),
    @JsonSubTypes.Type(value = GetZipcodeInfo.class, name = "getZipcodeInfo"),
})
public class RequestBody
{
    private AuthorizationInformation mAuthorizationInformation;

    /**
     * Empty constructor for Jackson
     */
    public RequestBody()
    {
    }

    /**
     * Constructs a request body
     *
     * @param authorizationInformation containing username and password
     */
    public RequestBody(AuthorizationInformation authorizationInformation)
    {
        mAuthorizationInformation = authorizationInformation;
    }

    /**
     * Authorization information
     * @return authorization information
     */
    @JacksonXmlProperty(localName = "authInfo")
    public AuthorizationInformation getAuthorizationInformation()
    {
        return mAuthorizationInformation;
    }

    /**
     * Sets the authorization information for this request
     * @param authorizationInformation containing username and password
     */
    public void setAuthorizationInformation(AuthorizationInformation authorizationInformation)
    {
        mAuthorizationInformation = authorizationInformation;
    }

    /**
     * Creates a request envelope containing the request body
     * @param requestBody to wrap with the envelope
     * @return request envelope
     */
    public static RequestEnvelope create(RequestBody requestBody)
    {
        return RequestEnvelope.create(requestBody);
    }
}
