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

package io.github.dsheirer.rrapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.dsheirer.rrapi.request.FccGetCallsign;
import io.github.dsheirer.rrapi.request.FccGetProximityCallsigns;
import io.github.dsheirer.rrapi.request.FccGetRadioServiceCode;
import io.github.dsheirer.rrapi.request.GetAgencyInfo;
import io.github.dsheirer.rrapi.request.GetApco25Systems;
import io.github.dsheirer.rrapi.request.GetCountryInfo;
import io.github.dsheirer.rrapi.request.GetCountryList;
import io.github.dsheirer.rrapi.request.GetCountyFrequenciesByTag;
import io.github.dsheirer.rrapi.request.GetCountyInfo;
import io.github.dsheirer.rrapi.request.GetFlavors;
import io.github.dsheirer.rrapi.request.GetMetroInfo;
import io.github.dsheirer.rrapi.request.GetMetros;
import io.github.dsheirer.rrapi.request.GetModes;
import io.github.dsheirer.rrapi.request.GetSites;
import io.github.dsheirer.rrapi.request.GetStateInfo;
import io.github.dsheirer.rrapi.request.GetStatesByList;
import io.github.dsheirer.rrapi.request.GetSubCategoryFrequenciesRequest;
import io.github.dsheirer.rrapi.request.GetSystemInformation;
import io.github.dsheirer.rrapi.request.GetTags;
import io.github.dsheirer.rrapi.request.GetTalkgroupCategories;
import io.github.dsheirer.rrapi.request.GetTalkgroups;
import io.github.dsheirer.rrapi.request.GetTypes;
import io.github.dsheirer.rrapi.request.GetUserData;
import io.github.dsheirer.rrapi.request.GetUserFeedBroadcasts;
import io.github.dsheirer.rrapi.request.GetVoices;
import io.github.dsheirer.rrapi.request.GetZipcodeInfo;
import io.github.dsheirer.rrapi.request.RequestEnvelope;
import io.github.dsheirer.rrapi.request.SearchCountyFrequency;
import io.github.dsheirer.rrapi.request.SearchMetroFrequency;
import io.github.dsheirer.rrapi.request.SearchStateFrequency;
import io.github.dsheirer.rrapi.response.Fault;
import io.github.dsheirer.rrapi.response.FccGetCallsignResponse;
import io.github.dsheirer.rrapi.response.FccGetProximityCallsignResponse;
import io.github.dsheirer.rrapi.response.FccGetRadioServiceCodeResponse;
import io.github.dsheirer.rrapi.response.FrequenciesResponse;
import io.github.dsheirer.rrapi.response.GetAgencyInfoResponse;
import io.github.dsheirer.rrapi.response.GetApco25SystemsResponse;
import io.github.dsheirer.rrapi.response.GetCountryInfoResponse;
import io.github.dsheirer.rrapi.response.GetCountryListResponse;
import io.github.dsheirer.rrapi.response.GetCountyInfoResponse;
import io.github.dsheirer.rrapi.response.GetFlavorsResponse;
import io.github.dsheirer.rrapi.response.GetMetroInfoResponse;
import io.github.dsheirer.rrapi.response.GetMetrosResponse;
import io.github.dsheirer.rrapi.response.GetModesResponse;
import io.github.dsheirer.rrapi.response.GetSitesResponse;
import io.github.dsheirer.rrapi.response.GetStateInfoResponse;
import io.github.dsheirer.rrapi.response.GetStatesByListResponse;
import io.github.dsheirer.rrapi.response.GetSubCategoryFrequenciesResponse;
import io.github.dsheirer.rrapi.response.GetSystemInformationResponse;
import io.github.dsheirer.rrapi.response.GetTagsResponse;
import io.github.dsheirer.rrapi.response.GetTalkgroupCategoriesResponse;
import io.github.dsheirer.rrapi.response.GetTalkgroupsResponse;
import io.github.dsheirer.rrapi.response.GetTypesResponse;
import io.github.dsheirer.rrapi.response.GetUserDataResponse;
import io.github.dsheirer.rrapi.response.GetUserFeedBroadcastsResponse;
import io.github.dsheirer.rrapi.response.GetVoicesResponse;
import io.github.dsheirer.rrapi.response.GetZipcodeInfoResponse;
import io.github.dsheirer.rrapi.response.ResponseEnvelope;
import io.github.dsheirer.rrapi.response.SearchFrequencyResponse;
import io.github.dsheirer.rrapi.type.Agency;
import io.github.dsheirer.rrapi.type.AgencyInfo;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.Country;
import io.github.dsheirer.rrapi.type.CountryInfo;
import io.github.dsheirer.rrapi.type.County;
import io.github.dsheirer.rrapi.type.CountyInfo;
import io.github.dsheirer.rrapi.type.FccCallsignDetails;
import io.github.dsheirer.rrapi.type.FccRadioServiceCode;
import io.github.dsheirer.rrapi.type.Flavor;
import io.github.dsheirer.rrapi.type.Frequency;
import io.github.dsheirer.rrapi.type.Metro;
import io.github.dsheirer.rrapi.type.Mode;
import io.github.dsheirer.rrapi.type.ProximityCallsignResult;
import io.github.dsheirer.rrapi.type.SearchFrequencyResult;
import io.github.dsheirer.rrapi.type.Site;
import io.github.dsheirer.rrapi.type.State;
import io.github.dsheirer.rrapi.type.StateInfo;
import io.github.dsheirer.rrapi.type.System;
import io.github.dsheirer.rrapi.type.SystemInformation;
import io.github.dsheirer.rrapi.type.Tag;
import io.github.dsheirer.rrapi.type.Talkgroup;
import io.github.dsheirer.rrapi.type.TalkgroupCategory;
import io.github.dsheirer.rrapi.type.TalkgroupRequestFilter;
import io.github.dsheirer.rrapi.type.Type;
import io.github.dsheirer.rrapi.type.UserFeedBroadcast;
import io.github.dsheirer.rrapi.type.UserInfo;
import io.github.dsheirer.rrapi.type.Voice;
import io.github.dsheirer.rrapi.type.ZipInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Java client for accessing the radioreference.com web services API
 *
 * Uses the Java 11 HttpClient with no dependencies on legacy Axis 1.4 libraries.
 */
public class RadioReferenceService
{
    private final static Logger mLog = LoggerFactory.getLogger(RadioReferenceService.class);

    public static final String API_VERSION_15 = "15";
    private static final String RADIO_REFERENCE_API_URL = "http://api.radioreference.com/soap2/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String XML_CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_RRAPI = "io.github.dsheirer.rrapi";

    private HttpClient mHttpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private AuthorizationInformation mAuthorizationInformation;
    private Map<Integer,Flavor> mFlavorMap;
    private Map<Integer,Metro> mMetroMap;
    private Map<Integer,List<County>> mMetroCountiesMap;
    private Map<Integer,Mode> mModeMap;
    private Map<Integer,Tag> mTagMap;
    private Map<Integer,Type> mTypeMap;
    private Map<Integer,Voice> mVoiceMap;

    /**
     * Constructs an instance of the service
     *
     * @param authorizationInformation with username and password for accessing the web service
     * @throws RadioReferenceException if there are any errors while accessing the service
     */
    public RadioReferenceService(AuthorizationInformation authorizationInformation) throws RadioReferenceException
    {
        if(authorizationInformation == null)
        {
            throw new RadioReferenceException("Authorization information cannot be null");
        }

        mAuthorizationInformation = authorizationInformation;
    }

    /**
     * User information containing user name and account expiration date
     *
     * @return user information instance or null
     * @throws RadioReferenceException if there is an error
     */
    public UserInfo getUserInfo() throws RadioReferenceException
    {
        RequestEnvelope request = GetUserData.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetUserDataResponse)
        {
            return ((GetUserDataResponse)response.getResponseBody()).getUserInfo();
        }

        return null;
    }

    /**
     * List of icecast audio streaming feed configurations that the user is authorized to provide to broadcastify.com
     *
     * @return list of broadcastify.com feed configurations or an empty list
     * @throws RadioReferenceException if there is an error
     */
    public List<UserFeedBroadcast> getUserFeeds() throws RadioReferenceException
    {
        RequestEnvelope request = GetUserFeedBroadcasts.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetUserFeedBroadcastsResponse)
        {
            return ((GetUserFeedBroadcastsResponse)response.getResponseBody()).getUserFeedBroadcasts();
        }

        return Collections.emptyList();
    }

    /**
     * List of countries supported by the web service.
     * @return list of countries or empty list
     * @throws RadioReferenceException if there is an error
     */
    public List<Country> getCountries() throws RadioReferenceException
    {
        RequestEnvelope request = GetCountryList.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetCountryListResponse)
        {
            return ((GetCountryListResponse)response.getResponseBody()).getCountries();
        }

        return Collections.emptyList();
    }

    /**
     * Country information for a specific country
     * @param country to query
     * @return country information or null
     * @throws RadioReferenceException if there is an error
     */
    public CountryInfo getCountryInfo(Country country) throws RadioReferenceException
    {
        return getCountryInfo(country.getCountryId());
    }

    /**
     * Country information for a specific country
     * @param countryId to query
     * @return country information or null
     * @throws RadioReferenceException if there is an error
     */
    public CountryInfo getCountryInfo(int countryId) throws RadioReferenceException
    {
        RequestEnvelope request = GetCountryInfo.create(mAuthorizationInformation, countryId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetCountryInfoResponse)
        {
            return ((GetCountryInfoResponse)response.getResponseBody()).getCountryInfo();
        }

        return null;
    }

    /**
     * Agency information for a specific agency
     * @param agency to query
     * @return agency information or null
     * @throws RadioReferenceException if there is an error
     */
    public AgencyInfo getAgencyInfo(Agency agency) throws RadioReferenceException
    {
        return getAgencyInfo(agency.getAgencyId());
    }

    /**
     * Agency information for a specific agency
     * @param agencyId to query
     * @return agency information or null
     * @throws RadioReferenceException if there is an error
     */
    public AgencyInfo getAgencyInfo(int agencyId) throws RadioReferenceException
    {
        RequestEnvelope request = GetAgencyInfo.create(mAuthorizationInformation, agencyId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetAgencyInfoResponse)
        {
            return ((GetAgencyInfoResponse)response.getResponseBody()).getAgencyInfo();
        }

        return null;
    }

    /**
     * Get state
     * @param stateId to query
     * @return state or null
     * @throws RadioReferenceException if there is an error
     */
    public State getState(int stateId) throws RadioReferenceException
    {
        RequestEnvelope request = GetStatesByList.create(mAuthorizationInformation, stateId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetStatesByListResponse)
        {
            List<State> states = ((GetStatesByListResponse)response.getResponseBody()).getStates();

            if(states.size() == 1)
            {
                return states.get(0);
            }
        }

        return null;
    }

    /**
     * Get state info
     * @param stateId to query
     * @return state info or null
     * @throws RadioReferenceException if there is an error
     */
    public StateInfo getStateInfo(int stateId) throws RadioReferenceException
    {
        RequestEnvelope request = GetStateInfo.create(mAuthorizationInformation, stateId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetStateInfoResponse)
        {
            return ((GetStateInfoResponse)response.getResponseBody()).getStateInfo();
        }

        return null;
    }

    /**
     * Get a list of states by state id
     * @param stateIds to query
     * @return list of states or empty list
     * @throws RadioReferenceException if there is an error
     */
    public List<State> getStates(List<Integer> stateIds) throws RadioReferenceException
    {
        RequestEnvelope request = GetStatesByList.create(mAuthorizationInformation, stateIds);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetStatesByListResponse)
        {
            return ((GetStatesByListResponse)response.getResponseBody()).getStates();
        }

        return Collections.emptyList();
    }

    /**
     * County information for a specific county
     * @param countyId to query
     * @return county information or null
     * @throws RadioReferenceException if there is an error
     */
    public CountyInfo getCountyInfo(int countyId) throws RadioReferenceException
    {
        RequestEnvelope request = GetCountyInfo.create(mAuthorizationInformation, countyId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetCountyInfoResponse)
        {
            return ((GetCountyInfoResponse)response.getResponseBody()).getCountyInfo();
        }

        return null;
    }

    /**
     * County frequencies by tag
     * @param countyId to query
     * @param tagId to query
     * @return frequencies list
     * @throws RadioReferenceException if there is an error
     */
    public List<Frequency> getCountyFrequenciesByTag(int countyId, int tagId) throws RadioReferenceException
    {
        RequestEnvelope request = GetCountyFrequenciesByTag.create(mAuthorizationInformation, countyId, tagId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FrequenciesResponse)
        {
            return ((FrequenciesResponse)response.getResponseBody()).getFrequencies();
        }

        return Collections.emptyList();
    }

    /**
     * Agency frequencies by tag
     * @param agencyId to query
     * @param tagId to query
     * @return frequencies list
     * @throws RadioReferenceException if there is an error
     */
    public List<Frequency> getAgencyFrequenciesByTag(int agencyId, int tagId) throws RadioReferenceException
    {
        RequestEnvelope request = GetCountyFrequenciesByTag.create(mAuthorizationInformation, agencyId, tagId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FrequenciesResponse)
        {
            return ((FrequenciesResponse)response.getResponseBody()).getFrequencies();
        }

        return Collections.emptyList();
    }

    /**
     * Radio system information and details
     * @param systemId to query
     * @return radio system or null
     * @throws RadioReferenceException if there is an error
     */
    public SystemInformation getSystemInformation(int systemId) throws RadioReferenceException
    {
        RequestEnvelope request = GetSystemInformation.create(mAuthorizationInformation, systemId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetSystemInformationResponse)
        {
            return ((GetSystemInformationResponse)response.getResponseBody()).getSystemInformation();
        }

        return null;
    }

    /**
     * Query radio systems by APCO25 hexadecimal system identifier.
     *
     * @param systemId to query (e.g. '2AE')
     * @return list of radio systems or null
     * @throws RadioReferenceException if there is an error
     */
    public List<System> getApco25Systems(String systemId) throws RadioReferenceException
    {
        RequestEnvelope request = GetApco25Systems.create(mAuthorizationInformation, systemId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetApco25SystemsResponse)
        {
            return ((GetApco25SystemsResponse)response.getResponseBody()).getSystems();
        }

        return null;
    }

    /**
     * Get sites for a radio system
     *
     * @param systemId to query
     * @return list of sites or null
     * @throws RadioReferenceException if there is an error
     */
    public List<Site> getSites(int systemId) throws RadioReferenceException
    {
        RequestEnvelope request = GetSites.create(mAuthorizationInformation, systemId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetSitesResponse)
        {
            return ((GetSitesResponse)response.getResponseBody()).getSites();
        }

        return null;
    }

    /**
     * Get talkgroups for a specific system
     *
     * @param systemId to query
     * @return list of talkgroups or null
     * @throws RadioReferenceException if there is an error
     */
    public List<Talkgroup> getTalkgroups(int systemId) throws RadioReferenceException
    {
        RequestEnvelope request = GetTalkgroups.create(mAuthorizationInformation, systemId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetTalkgroupsResponse)
        {
            return ((GetTalkgroupsResponse)response.getResponseBody()).getTalkgroups();
        }

        return null;
    }

    /**
     * Get talkgroups for a specific system with optional filtering by category, tag, or decimal value
     *
     * @param filter specifying a category id, tag id, or decimal value
     * @return list of talkgroups or null
     * @throws RadioReferenceException if there is an error
     */
    public List<Talkgroup> getTalkgroups(TalkgroupRequestFilter filter) throws RadioReferenceException
    {
        RequestEnvelope request = GetTalkgroups.create(mAuthorizationInformation, filter);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetTalkgroupsResponse)
        {
            return ((GetTalkgroupsResponse)response.getResponseBody()).getTalkgroups();
        }

        return null;
    }

    /**
     * Get talkgroup categories
     *
     * @param systemId to query
     * @return list of talkgroup categories or null
     * @throws RadioReferenceException if there is an error
     */
    public List<TalkgroupCategory> getTalkgroupCategories(int systemId) throws RadioReferenceException
    {
        RequestEnvelope request = GetTalkgroupCategories.create(mAuthorizationInformation, systemId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetTalkgroupCategoriesResponse)
        {
            return ((GetTalkgroupCategoriesResponse)response.getResponseBody()).getTalkgroupCategories();
        }

        return null;
    }

    /**
     * Flavor information
     * @param flavorId to query
     * @return flavor or null
     * @throws RadioReferenceException if there is an error
     */
    public Flavor getFlavor(int flavorId) throws RadioReferenceException
    {
        getFlavorsMap();

        if(mFlavorMap != null)
        {
            return mFlavorMap.get(flavorId);
        }

        return null;
    }

    /**
     * Map of flavors and flavor identifiers.
     *
     * Note: flavor values are cached on the first web service call and reused for subsequent method invocations.
     *
     * @return flavors map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Flavor> getFlavorsMap() throws RadioReferenceException
    {
        if(mFlavorMap == null)
        {
            RequestEnvelope request = GetFlavors.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetFlavorsResponse)
            {
                List<Flavor> flavors = ((GetFlavorsResponse)response.getResponseBody()).getFlavors();

                mFlavorMap = new TreeMap<>();

                for(Flavor flavor: flavors)
                {
                    mFlavorMap.put(flavor.getTypeId(), flavor);
                }
            }
        }

        return mFlavorMap;
    }

    /**
     * Mode information
     * @param modeId to query
     * @return mode or null
     * @throws RadioReferenceException if there is an error
     */
    public Mode getMode(int modeId) throws RadioReferenceException
    {
        getModesMap();

        if(mModeMap != null)
        {
            return mModeMap.get(modeId);
        }

        return null;
    }

    /**
     * Map of modes and mode identifiers.
     *
     * Note: mode values are cached on the first web service call and reused for subsequent method invocations.
     *
     * @return modes map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Mode> getModesMap() throws RadioReferenceException
    {
        if(mModeMap == null)
        {
            RequestEnvelope request = GetModes.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetModesResponse)
            {
                List<Mode> modes = ((GetModesResponse)response.getResponseBody()).getModes();

                mModeMap = new TreeMap<>();

                for(Mode mode: modes)
                {
                    mModeMap.put(mode.getModeId(), mode);
                }
            }
        }

        return mModeMap;
    }

    /**
     * Metro information
     * @param metroId to query
     * @return metro or null
     * @throws RadioReferenceException if there is an error
     */
    public Metro getMetro(int metroId) throws RadioReferenceException
    {
        getMetrosMap();

        if(mMetroMap != null)
        {
            return mMetroMap.get(metroId);
        }

        return null;
    }

    /**
     * Map of metros and metro identifiers.
     *
     * Note: metro values are cached on the first web service call and reused for subsequent method invocations.
     *
     * @return metros map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Metro> getMetrosMap() throws RadioReferenceException
    {
        if(mMetroMap == null)
        {
            RequestEnvelope request = GetMetros.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetMetrosResponse)
            {
                List<Metro> metros = ((GetMetrosResponse)response.getResponseBody()).getMetros();

                mMetroMap = new TreeMap<>();

                for(Metro metro: metros)
                {
                    mMetroMap.put(metro.getMetroId(), metro);
                }
            }
        }

        return mMetroMap;
    }

    /**
     * Map of counties to metro identifiers.
     *
     * Note: this is an expensive service call the first time.  Metro values are cached on the first web service call
     * and reused for subsequent method invocations.
     *
     * @return metro counties map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,List<County>> getMetroCountiesMap() throws RadioReferenceException
    {
        if(mMetroCountiesMap == null)
        {
            Map<Integer,Metro> metrosMap = getMetrosMap();

            if(metrosMap != null)
            {
                mMetroCountiesMap = new TreeMap<>();

                for(Metro metro: metrosMap.values())
                {
                    RequestEnvelope request = GetMetroInfo.create(mAuthorizationInformation, metro.getMetroId());
                    ResponseEnvelope response = submitSync(request);

                    if(response != null && response.getResponseBody() instanceof GetMetroInfoResponse)
                    {
                        List<County> counties = ((GetMetroInfoResponse)response.getResponseBody()).getCounties();
                        mMetroCountiesMap.put(metro.getMetroId(), counties);
                    }
                }
            }
        }

        return mMetroCountiesMap;
    }

    /**
     * Get counties for a metro area
     *
     * @param metroId to query
     * @return list of counties
     * @throws RadioReferenceException if there is an error
     */
    public List<County> getMetroCounties(int metroId) throws RadioReferenceException
    {
        RequestEnvelope request = GetMetroInfo.create(mAuthorizationInformation, metroId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetMetroInfoResponse)
        {
            return ((GetMetroInfoResponse)response.getResponseBody()).getCounties();
        }

        return Collections.emptyList();
    }

    /**
     * List of metro areas for a state
     *
     * WARNING: the first call to this method is expensive because it uses getMetroCountiesMap() which is expensive.
     *
     * Note: this is performed by first querying the state info for the list of counties and finding the matching metro
     * areas from the getMetrosMap() method.
     *
     * @param stateId to query
     * @return list of metros
     * @throws RadioReferenceException if there is an error with the web service
     */
    public List<Metro> getMetrosByState(int stateId) throws RadioReferenceException
    {
        List<Metro> metros = new ArrayList<>();

        StateInfo stateInfo = getStateInfo(stateId);
        Map<Integer,List<County>> metroMap = getMetroCountiesMap();

        if(metroMap != null)
        {
            for(County county: stateInfo.getCounties())
            {
                for(Map.Entry<Integer,List<County>> entry: metroMap.entrySet())
                {
                    for(County metroCounty: entry.getValue())
                    {
                        if(county.getCountyId() == metroCounty.getCountyId())
                        {
                            Metro metro = getMetro(entry.getKey());

                            if(!metros.contains(metro))
                            {
                                metros.add(metro);
                            }
                            continue;
                        }
                    }
                }
            }
        }

        return metros;
    }


    /**
     * Map of tags and tag identifiers
     *
     * Note: map is cached on first call to the web service and reused on subsequent method invocations.
     * @return tags map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Tag> getTagsMap() throws RadioReferenceException
    {
        if(mTagMap == null)
        {
            RequestEnvelope request = GetTags.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetTagsResponse)
            {
                mTagMap = new TreeMap<>();

                List<Tag> tags = ((GetTagsResponse)response.getResponseBody()).getTags();

                for(Tag tag: tags)
                {
                    mTagMap.put(tag.getTagId(), tag);
                }
            }
        }

        return mTagMap;
    }

    /**
     * Tag
     * @param tagId to query
     * @return type or null
     * @throws RadioReferenceException if there is an error
     */
    public Tag getTag(int tagId) throws RadioReferenceException
    {
        getTagsMap();

        if(mTagMap != null)
        {
            return mTagMap.get(tagId);
        }

        return null;
    }


    /**
     * Type (protocol) information
     * @param typeId to query
     * @return type or null
     * @throws RadioReferenceException if there is an error
     */
    public Type getType(int typeId) throws RadioReferenceException
    {
        getTypesMap();

        if(mTypeMap != null)
        {
            return mTypeMap.get(typeId);
        }

        return null;
    }

    /**
     * Map of types (protocols) and type identifiers
     *
     * Note: type map is cached on first call to the web service and reused on subsequent method invocations.
     * @return types map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Type> getTypesMap() throws RadioReferenceException
    {
        if(mTypeMap == null)
        {
            RequestEnvelope request = GetTypes.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetTypesResponse)
            {
                mTypeMap = new TreeMap<>();

                List<Type> types = ((GetTypesResponse)response.getResponseBody()).getTypes();

                for(Type type: types)
                {
                    mTypeMap.put(type.getTypeId(), type);
                }
            }
        }

        return mTypeMap;
    }

    /**
     * Map of voices and voice identifiers
     *
     * Note: map is cached on first call to the web service and reused on subsequent method invocations.
     * @return voices map or null
     * @throws RadioReferenceException if there is an error
     */
    public Map<Integer,Voice> getVoicesMap() throws RadioReferenceException
    {
        if(mVoiceMap == null)
        {
            RequestEnvelope request = GetVoices.create(mAuthorizationInformation);
            ResponseEnvelope response = submitSync(request);

            if(response != null && response.getResponseBody() instanceof GetVoicesResponse)
            {
                mVoiceMap = new TreeMap<>();

                List<Voice> voices = ((GetVoicesResponse)response.getResponseBody()).getVoices();

                for(Voice voice: voices)
                {
                    mVoiceMap.put(voice.getTypeId(), voice);
                }
            }
        }

        return mVoiceMap;
    }

    /**
     * Voice information
     * @param voiceId to query
     * @return voice or null
     * @throws RadioReferenceException if there is an error
     */
    public Voice getVoice(int voiceId) throws RadioReferenceException
    {
        getVoicesMap();

        if(mVoiceMap != null)
        {
            return mVoiceMap.get(voiceId);
        }

        return null;
    }


    /**
     * Zipcode information
     * @param zipcode to query
     * @return zipcode info or null
     * @throws RadioReferenceException if there is an error
     */
    public ZipInfo getZipcodeInfo(int zipcode) throws RadioReferenceException
    {
        RequestEnvelope request = GetZipcodeInfo.create(mAuthorizationInformation, zipcode);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetZipcodeInfoResponse)
        {
            return ((GetZipcodeInfoResponse)response.getResponseBody()).getZipcodeInfo();
        }

        return null;
    }

    /**
     * Sub-Category frequencies
     * @param subCategoryId that identifies a set of frequencies
     * @return list of frequencies
     * @throws RadioReferenceException if there is an error
     */
    public List<Frequency> getSubCategoryFrequencies(int subCategoryId) throws RadioReferenceException
    {
        RequestEnvelope request = GetSubCategoryFrequenciesRequest.create(mAuthorizationInformation, subCategoryId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetSubCategoryFrequenciesResponse)
        {
            return ((GetSubCategoryFrequenciesResponse)response.getResponseBody()).getFrequencies();
        }

        return Collections.emptyList();
    }

    /**
     * Search county frequencies
     * @param countyId that identifies a set of frequencies
     * @param frequency in MHz
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchCountyFrequencies(int countyId, double frequency) throws RadioReferenceException
    {
        return searchCountyFrequencies(countyId, frequency, null);
    }

    /**
     * Search county frequencies
     * @param countyId that identifies a set of frequencies
     * @param frequency in MHz
     * @param tone description (optional - can be null)
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchCountyFrequencies(int countyId, double frequency, String tone) throws RadioReferenceException
    {
        RequestEnvelope request = SearchCountyFrequency.create(mAuthorizationInformation, countyId, frequency, tone);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof SearchFrequencyResponse)
        {
            return ((SearchFrequencyResponse)response.getResponseBody()).getResults();
        }

        return Collections.emptyList();
    }

    /**
     * Search state frequencies
     * @param stateId that identifies a set of frequencies
     * @param frequency in MHz
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchStateFrequencies(int stateId, double frequency) throws RadioReferenceException
    {
        return searchStateFrequencies(stateId, frequency, null);
    }

    /**
     * Search state frequencies
     * @param stateId that identifies a set of frequencies
     * @param frequency in MHz
     * @param tone description (optional - can be null)
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchStateFrequencies(int stateId, double frequency, String tone) throws RadioReferenceException
    {
        RequestEnvelope request = SearchStateFrequency.create(mAuthorizationInformation, stateId, frequency, tone);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof SearchFrequencyResponse)
        {
            return ((SearchFrequencyResponse)response.getResponseBody()).getResults();
        }

        return Collections.emptyList();
    }

    /**
     * Search metro frequencies
     * @param metroId that identifies a set of frequencies
     * @param frequency in MHz
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchMetroFrequencies(int metroId, double frequency) throws RadioReferenceException
    {
        return searchMetroFrequencies(metroId, frequency, null);
    }

    /**
     * Search metro frequencies
     * @param metroId that identifies a set of frequencies
     * @param frequency in MHz
     * @param tone description (optional - can be null)
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<SearchFrequencyResult> searchMetroFrequencies(int metroId, double frequency, String tone) throws RadioReferenceException
    {
        RequestEnvelope request = SearchMetroFrequency.create(mAuthorizationInformation, metroId, frequency, tone);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof SearchFrequencyResponse)
        {
            return ((SearchFrequencyResponse)response.getResponseBody()).getResults();
        }

        return Collections.emptyList();
    }

    /**
     * Get FCC callsign details.
     *
     * @param callsign to query
     * @return callsign details or null
     * @throws RadioReferenceException if there is an error
     */
    public FccCallsignDetails getFccCallsignDetails(String callsign) throws RadioReferenceException
    {
        RequestEnvelope request = FccGetCallsign.create(mAuthorizationInformation, callsign);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FccGetCallsignResponse)
        {
            return ((FccGetCallsignResponse)response.getResponseBody()).getFccCallsignDetails();
        }

        return null;
    }

    /**
     * Get FCC Radio Service Code Information
     * @return list of FCC radio service codes
     * @throws RadioReferenceException if there is an error
     */
    public List<FccRadioServiceCode> getFccRadioServiceCodes() throws RadioReferenceException
    {
        RequestEnvelope request = FccGetRadioServiceCode.create(mAuthorizationInformation);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FccGetRadioServiceCodeResponse)
        {
            return ((FccGetRadioServiceCodeResponse)response.getResponseBody()).getFccRadioServiceCodes();
        }

        return Collections.emptyList();
    }

    /**
     * Get FCC Radio Service Code Information for a specific code
     * @param code to lookup
     * @return FCC radio service code or null
     * @throws RadioReferenceException if there is an error
     */
    public FccRadioServiceCode getFccRadioServiceCode(String code) throws RadioReferenceException
    {
        RequestEnvelope request = FccGetRadioServiceCode.create(mAuthorizationInformation, code);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FccGetRadioServiceCodeResponse)
        {
            List<FccRadioServiceCode> codes = ((FccGetRadioServiceCodeResponse)response.getResponseBody()).getFccRadioServiceCodes();

            if(codes != null && !codes.isEmpty())
            {
                return codes.get(0);
            }
        }

        return null;
    }

    /**
     * Get FCC Callsigns near a location
     * @param latitude of the search location
     * @param longitude of the search location
     * @param range from the search location (0 - 4.82 km)
     * @return list of search results
     * @throws RadioReferenceException if there is an error
     */
    public List<ProximityCallsignResult> getFccCallsignsNearLocation(double latitude, double longitude, double range)
                    throws RadioReferenceException
    {
        RequestEnvelope request = FccGetProximityCallsigns.create(mAuthorizationInformation, latitude, longitude, range);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FccGetProximityCallsignResponse)
        {
            return ((FccGetProximityCallsignResponse)response.getResponseBody()).getProximityCallsignResults();
        }

        return Collections.emptyList();
    }

    /**
     * Submits the request envelope and returns a response envelope.
     *
     * @param requestEnvelope to submit
     * @return response envelope
     * @throws IOException if there are any HTTP or envelope xml parsing errors
     * @throws InterruptedException if there is an error
     */
    private ResponseEnvelope submitSync(RequestEnvelope requestEnvelope) throws RadioReferenceException
    {
        String xml;

        try
        {
            xml = requestEnvelope.toXmlString();
        }
        catch(JsonProcessingException jpe)
        {
            throw new RadioReferenceException("Error serializing request envelope to XML", jpe);
        }

        if(xml != null)
        {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RADIO_REFERENCE_API_URL))
                .header(CONTENT_TYPE, XML_CONTENT_TYPE)
                .header(USER_AGENT, USER_AGENT_RRAPI)
                .POST(HttpRequest.BodyPublishers.ofString(xml))
                .build();

            HttpResponse<String> response = null;

            try
            {
                response = mHttpClient.send(request, HttpResponse.BodyHandlers.ofString());
            }
            catch(IOException ioe)
            {
                throw new RadioReferenceException("Error submitting HTML SOAP request to web service", ioe);
            }
            catch(InterruptedException ie)
            {
                throw new RadioReferenceException("Interrupted while submitting HTML SOAP request to web service", ie);
            }

            if(response != null)
            {
                try
                {
                    if(response.statusCode() == 200)
                    {
                        return deserialize(response.body());
                    }
                    else
                    {
                        ResponseEnvelope responseEnvelope = deserialize(response.body());

                        if(responseEnvelope.getResponseBody() instanceof Fault)
                        {
                            throw new RadioReferenceException("Error", response.statusCode(), (Fault)responseEnvelope.getResponseBody());
                        }
                    }
                }
                catch(IOException ioe)
                {
                    mLog.debug("Response: " + response.body());
                    throw new RadioReferenceException("Error deserializing XML response: " + response.body(), ioe);
                }
            }
        }

        return null;
    }

    /**
     * Deserializes the xml response using Faster Jackson xml annotations.
     * @param xml to deserialize
     * @return deserialized response envelope or null if there is nothing to deserialize
     * @throws IOException if there are any errors
     */
    private static ResponseEnvelope deserialize(String xml) throws IOException
    {
        if(xml != null)
        {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(xml, ResponseEnvelope.class);
        }

        return null;
    }

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(java.lang.System.in);
        java.lang.System.out.print("Password:");
        String password = keyboard.next();
        mLog.debug("Using password: " + password);
        AuthorizationInformation authorizationInformation = new AuthorizationInformation("88969092", "dsheirer", password);

        try
        {
            RadioReferenceService service = new RadioReferenceService(authorizationInformation);
            UserInfo userInfo = service.getUserInfo();
            mLog.info("User: " + userInfo.getUserName() + " Account Expires:" + userInfo.getExpirationDate());
        }
        catch(RadioReferenceException rre)
        {
            if(rre.hasFault())
            {
                mLog.info(rre.getFault().toString());
            }
            else
            {
                mLog.info("RRE", rre);
            }
        }
        catch(Exception e)
        {
            mLog.error("Error", e);
        }

        mLog.debug("Finished");
    }
}
