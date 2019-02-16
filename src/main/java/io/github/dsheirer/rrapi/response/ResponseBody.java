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

package io.github.dsheirer.rrapi.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = FccGetCallsignResponse.class, name = "fccGetCallsignResponse"),
    @JsonSubTypes.Type(value = GetAgencyInfoResponse.class, name = "getAgencyInfoResponse"),
    @JsonSubTypes.Type(value = GetCountryInfoResponse.class, name = "getCountryInfoResponse"),
    @JsonSubTypes.Type(value = GetCountryListResponse.class, name = "getCountryListResponse"),
    @JsonSubTypes.Type(value = GetCountiesByListResponse.class, name = "getCountiesByListResponse"),
    @JsonSubTypes.Type(value = GetCountyInfoResponse.class, name = "getCountyInfoResponse"),
    @JsonSubTypes.Type(value = GetModesResponse.class, name = "getModeResponse"),
    @JsonSubTypes.Type(value = GetStateInfoResponse.class, name = "getStateInfoResponse"),
    @JsonSubTypes.Type(value = GetStatesByListResponse.class, name = "getStatesByListResponse"),
    @JsonSubTypes.Type(value = GetApco25SystemsResponse.class, name = "getTrsBySysidResponse"),
    @JsonSubTypes.Type(value = GetSystemInformationResponse.class, name = "getTrsDetailsResponse"),
    @JsonSubTypes.Type(value = GetSitesResponse.class, name = "getTrsSitesResponse"),
    @JsonSubTypes.Type(value = GetTagsResponse.class, name = "getTagResponse"),
    @JsonSubTypes.Type(value = GetFlavorsResponse.class, name = "getTrsFlavorResponse"),
    @JsonSubTypes.Type(value = GetTalkgroupsResponse.class, name = "getTrsTalkgroupsResponse"),
    @JsonSubTypes.Type(value = GetTalkgroupCategoriesResponse.class, name = "getTrsTalkgroupCatsResponse"),
    @JsonSubTypes.Type(value = GetTypesResponse.class, name = "getTrsTypeResponse"),
    @JsonSubTypes.Type(value = GetVoicesResponse.class, name = "getTrsVoiceResponse"),
    @JsonSubTypes.Type(value = GetUserDataResponse.class, name = "getUserDataResponse"),
    @JsonSubTypes.Type(value = GetUserFeedBroadcastsResponse.class, name = "getUserFeedBroadcastsResponse"),
    @JsonSubTypes.Type(value = GetZipcodeInfoResponse.class, name = "getZipcodeInfoResponse"),
})
public class ResponseBody
{
    public ResponseBody()
    {
    }
}
