/*
 * *****************************************************************************
 * Copyright (C) 2020 Dennis Sheirer
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
import io.github.dsheirer.rrapi.RadioReferenceException.ErrorType;
import io.github.dsheirer.rrapi.request.FccGetCallsign;
import io.github.dsheirer.rrapi.request.FccGetProximityCallsigns;
import io.github.dsheirer.rrapi.request.FccGetRadioServiceCode;
import io.github.dsheirer.rrapi.request.GetAgencyFrequenciesByTag;
import io.github.dsheirer.rrapi.request.GetAgencyInfo;
import io.github.dsheirer.rrapi.request.GetApco25Systems;
import io.github.dsheirer.rrapi.request.GetCountiesByList;
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
import io.github.dsheirer.rrapi.response.GetCountiesByListResponse;
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
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.ToIntFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java client for accessing the radioreference.com web services API
 *
 * Uses the Java HttpClient with no dependencies on legacy Axis 1.4 libraries.
 *
 * Thread safe: methods can be called concurrently.  Each request is limited by the connect and request timeouts, and
 * {@link #async(ServiceCall)} runs any call in the background with cancellation.
 */
public class RadioReferenceService
{
    private final static Logger mLog = LoggerFactory.getLogger(RadioReferenceService.class);

    /**
     * Radio Reference API version supported by this service.
     */
    public static final String API_VERSION = "18";
    private static final String RADIO_REFERENCE_API_URL = "https://api.radioreference.com/soap2/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String XML_CONTENT_TYPE = "text/xml;charset=UTF-8";
    private static final String USER_AGENT = "User-Agent";
    private static final String USER_AGENT_RRAPI = "io.github.dsheirer.rrapi";

    /**
     * Default limit for establishing a connection to the web service
     */
    public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);

    /**
     * Default limit for receiving the response to a request, after the request is sent
     */
    public static final Duration DEFAULT_REQUEST_TIMEOUT = Duration.ofSeconds(30);

    private final SoapTransport mTransport;
    private final AuthorizationInformation mAuthorizationInformation;
    private final CachedValue<Map<Integer,Flavor>> mFlavors = new CachedValue<>();
    private final CachedValue<Map<Integer,Metro>> mMetros = new CachedValue<>();
    private final CachedValue<Map<Integer,List<County>>> mMetroCounties = new CachedValue<>();
    private final CachedValue<Map<Integer,Mode>> mModes = new CachedValue<>();
    private final CachedValue<Map<Integer,Tag>> mTags = new CachedValue<>();
    private final CachedValue<Map<Integer,Type>> mTypes = new CachedValue<>();
    private final CachedValue<Map<Integer,Voice>> mVoices = new CachedValue<>();

    /**
     * Constructs an instance of the service
     *
     * @param authorizationInformation with username and password for accessing the web service
     * @throws RadioReferenceException if there are any errors while accessing the service
     */
    public RadioReferenceService(AuthorizationInformation authorizationInformation) throws RadioReferenceException
    {
        this(authorizationInformation, DEFAULT_CONNECT_TIMEOUT, DEFAULT_REQUEST_TIMEOUT);
    }

    /**
     * Constructs an instance of the service with custom timeouts
     *
     * @param authorizationInformation with username and password for accessing the web service
     * @param connectTimeout limit for establishing a connection to the web service
     * @param requestTimeout limit for receiving the response to each request
     * @throws RadioReferenceException if the authorization information is null
     * @throws IllegalArgumentException if a timeout is null, zero or negative
     */
    public RadioReferenceService(AuthorizationInformation authorizationInformation, Duration connectTimeout,
                                 Duration requestTimeout) throws RadioReferenceException
    {
        this(authorizationInformation, httpTransport(URI.create(RADIO_REFERENCE_API_URL), connectTimeout, requestTimeout));
    }

    /**
     * Constructs an instance of the service that uses the transport to submit requests.  Package-private for testing.
     *
     * @param authorizationInformation with username and password for accessing the web service
     * @param transport to submit requests
     * @throws RadioReferenceException if the authorization information is null
     */
    RadioReferenceService(AuthorizationInformation authorizationInformation, SoapTransport transport) throws RadioReferenceException
    {
        if(authorizationInformation == null)
        {
            throw new RadioReferenceException(RadioReferenceException.ErrorType.INVALID_REQUEST,
                "Authorization information cannot be null");
        }

        mAuthorizationInformation = authorizationInformation;
        mTransport = transport;
    }

    /**
     * Transport that posts requests to the web service with the HTTP client.  Package-private for testing against a
     * local server.
     *
     * @param uri of the web service
     * @param connectTimeout limit for establishing a connection
     * @param requestTimeout limit for receiving the response to each request
     */
    static SoapTransport httpTransport(URI uri, Duration connectTimeout, Duration requestTimeout)
    {
        if(connectTimeout == null || connectTimeout.isZero() || connectTimeout.isNegative() ||
            requestTimeout == null || requestTimeout.isZero() || requestTimeout.isNegative())
        {
            throw new IllegalArgumentException("Timeouts must be positive durations");
        }

        HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(connectTimeout)
            .build();

        return requestXml -> {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(requestTimeout)
                .header(CONTENT_TYPE, XML_CONTENT_TYPE)
                .header(USER_AGENT, USER_AGENT_RRAPI)
                .POST(HttpRequest.BodyPublishers.ofString(requestXml))
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Duration retryAfter = response.headers().firstValue("Retry-After")
                .map(RadioReferenceService::parseRetryAfter).orElse(null);
            return new SoapTransport.Response(response.statusCode(), response.body(), retryAfter);
        };
    }

    /**
     * Account details of the authenticated user: user name and premium subscription expiration date (operation
     * {@code getUserData}).  Useful for checking the credentials and the subscription status.
     *
     * @return user information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails, with error type AUTHENTICATION for invalid credentials
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
     * Broadcastify audio feeds that the user provides, with their stream connection details (operation
     * {@code getUserFeedBroadcasts}).  The details include the stream passwords.
     *
     * @return feeds, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Country with the id, from the list of countries (operation {@code getCountryList}).  Each call requests the list.
     *
     * @param id of the country, e.g. 1 for the United States
     * @return country
     * @throws RadioReferenceException with error type INVALID_REQUEST if no country has the id, or if the request fails
     */
    public Country getCountry(int id) throws RadioReferenceException
    {
        List<Country> countries = getCountries();

        for(Country country : countries)
        {
            if(country.getCountryId() == id)
            {
                return country;
            }
        }

        throw new RadioReferenceException(RadioReferenceException.ErrorType.INVALID_REQUEST, "Country with id " + id + " not found");
    }

    /**
     * Countries in the database (operation {@code getCountryList}).  The service answers this request without checking
     * the credentials.
     *
     * @return countries, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Details of the country: its states and national agencies (operation {@code getCountryInfo}).
     *
     * @param country to query, by its id
     * @return country information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public CountryInfo getCountryInfo(Country country) throws RadioReferenceException
    {
        return getCountryInfo(country.getCountryId());
    }

    /**
     * Details of the country: its states and national agencies (operation {@code getCountryInfo}).
     *
     * @param countryId of the country, e.g. 1 for the United States
     * @return country information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Details of the agency: location, coverage, and frequency categories with their subcategories (operation
     * {@code getAgencyInfo}).  The frequencies are listed by subcategory: see {@link #getSubCategoryFrequencies(int)}.
     *
     * @param agency to query, by its id
     * @return agency information, or null if the response doesn't include it
     * @throws RadioReferenceException with error type INVALID_REQUEST if the agency doesn't exist, or if the request
     *     fails
     */
    public AgencyInfo getAgencyInfo(Agency agency) throws RadioReferenceException
    {
        return getAgencyInfo(agency.getAgencyId());
    }

    /**
     * Details of the agency: location, coverage, and frequency categories with their subcategories (operation
     * {@code getAgencyInfo}).  The frequencies are listed by subcategory: see {@link #getSubCategoryFrequencies(int)}.
     *
     * @param agencyId of the agency
     * @return agency information, or null if the response doesn't include it
     * @throws RadioReferenceException with error type INVALID_REQUEST if the agency doesn't exist, or if the request
     *     fails
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
     * State with the id (operation {@code getStatesByList} with one id).
     *
     * @param stateId of the state, e.g. 36 for New York
     * @return state, or null if the service doesn't return exactly one state
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Details of the state: its counties, statewide agencies and trunked radio systems (operation
     * {@code getStateInfo}).
     *
     * @param stateId of the state, e.g. 36 for New York
     * @return state information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * States with the ids, in one request (operation {@code getStatesByList}).
     *
     * @param stateIds of the states
     * @return states, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Counties with the ids, in one request (operation {@code getCountiesByList}).
     *
     * @param countyIds of the counties
     * @return counties, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<County> getCounties(List<Integer> countyIds) throws RadioReferenceException
    {
        RequestEnvelope request = GetCountiesByList.create(mAuthorizationInformation, countyIds);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof GetCountiesByListResponse)
        {
            return ((GetCountiesByListResponse)response.getResponseBody()).getCounties();
        }

        return Collections.emptyList();
    }

    /**
     * Details of the county: location, coverage, frequency categories with their subcategories, and the agencies and
     * trunked radio systems in the county (operation {@code getCountyInfo}).  The frequencies are listed by
     * subcategory: see
     * {@link #getSubCategoryFrequencies(int)}.
     *
     * @param countyId of the county
     * @return county information, or null if the response doesn't include it
     * @throws RadioReferenceException with error type INVALID_REQUEST if the county doesn't exist, or if the request
     *     fails
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
     * Frequencies in the county with the function tag (operation {@code getCountyFreqsByTag}).  The results include
     * the frequencies of the agencies in the county, not only the county's own categories.
     *
     * @param countyId of the county
     * @param tagId of the function tag: see {@link #getTagsMap()}
     * @return frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Frequencies of the agency with the function tag (operation {@code getAgencyFreqsByTag}).
     *
     * @param agencyId of the agency
     * @param tagId of the function tag: see {@link #getTagsMap()}
     * @return frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<Frequency> getAgencyFrequenciesByTag(int agencyId, int tagId) throws RadioReferenceException
    {
        RequestEnvelope request = GetAgencyFrequenciesByTag.create(mAuthorizationInformation, agencyId, tagId);
        ResponseEnvelope response = submitSync(request);

        if(response != null && response.getResponseBody() instanceof FrequenciesResponse)
        {
            return ((FrequenciesResponse)response.getResponseBody()).getFrequencies();
        }

        return Collections.emptyList();
    }

    /**
     * Details of the trunked radio system: type, flavor and voice, location and coverage, radio networks (e.g. APCO-25
     * system identifiers), band plans and fleetmap (operation {@code getTrsDetails}).  Sites and talkgroups have their
     * own
     * requests: {@link #getSites(int)} and {@link #getTalkgroups(int)}.
     *
     * @param systemId of the trunked radio system
     * @return system information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Trunked radio systems that use the APCO-25 system identifier (operation {@code getTrsBySysid}).
     *
     * @param systemId hexadecimal APCO-25 system identifier, e.g. "2AE"
     * @return systems, or null if the response doesn't include a list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Sites of the trunked radio system, with their frequencies, location and coverage (operation {@code getTrsSites}).
     *
     * @param systemId of the trunked radio system
     * @return sites, or null if the response doesn't include a list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Talkgroups of the trunked radio system (operation {@code getTrsTalkgroups}).
     *
     * @param systemId of the trunked radio system
     * @return talkgroups, or null if the response doesn't include a list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Talkgroups of the trunked radio system, limited to a talkgroup category, a function tag or a decimal talkgroup
     * value (operation {@code getTrsTalkgroups}).
     *
     * @param filter with the system and the optional category, tag and decimal value
     * @return talkgroups, or null if the response doesn't include a list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Talkgroup categories of the trunked radio system (operation {@code getTrsTalkgroupCats}).
     *
     * @param systemId of the trunked radio system
     * @return categories, or null if the response doesn't include a list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Trunked system flavor with the id, from {@link #getFlavorsMap()}.
     *
     * @param flavorId of the flavor, e.g. from {@link SystemInformation#getFlavorId()}
     * @return flavor, or null if there is no flavor with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Flavor getFlavor(int flavorId) throws RadioReferenceException
    {
        Map<Integer,Flavor> flavors = getFlavorsMap();
        return flavors != null ? flavors.get(flavorId) : null;
    }

    /**
     * Trunked system flavors by id: the variants of each system type, e.g. a phase or a vendor variant of a protocol
     * (operation {@code getTrsFlavor}).
     *
     * Loaded on the first call and cached.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the flavors
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Flavor> getFlavorsMap() throws RadioReferenceException
    {
        return mFlavors.get(() -> {
            ResponseEnvelope response = submitSync(GetFlavors.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetFlavorsResponse flavorsResponse)
            {
                return toMap(flavorsResponse.getFlavors(), Flavor::getFlavorId);
            }

            return null;
        });
    }

    /**
     * Operating mode with the id, from {@link #getModesMap()}.
     *
     * @param modeId of the mode, e.g. from {@link Frequency#getMode()}
     * @return mode, or null if there is no mode with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Mode getMode(int modeId) throws RadioReferenceException
    {
        Map<Integer,Mode> modes = getModesMap();
        return modes != null ? modes.get(modeId) : null;
    }

    /**
     * Operating modes by id: the analog or digital modulation of a conventional channel (operation {@code getMode}).
     * {@link Frequency#getMode()} holds the mode id.
     *
     * Loaded on the first call and cached.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the modes
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Mode> getModesMap() throws RadioReferenceException
    {
        return mModes.get(() -> {
            ResponseEnvelope response = submitSync(GetModes.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetModesResponse modesResponse)
            {
                return toMap(modesResponse.getModes(), Mode::getModeId);
            }

            return null;
        });
    }

    /**
     * Metro area with the id, from {@link #getMetrosMap()}.
     *
     * @param metroId of the metro area
     * @return metro area, or null if there is no metro area with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Metro getMetro(int metroId) throws RadioReferenceException
    {
        Map<Integer,Metro> metros = getMetrosMap();
        return metros != null ? metros.get(metroId) : null;
    }

    /**
     * Metro areas by id (operation {@code getMetroArea}).
     *
     * Loaded on the first call and cached.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the metro areas
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Metro> getMetrosMap() throws RadioReferenceException
    {
        return mMetros.get(() -> {
            ResponseEnvelope response = submitSync(GetMetros.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetMetrosResponse metrosResponse)
            {
                return toMap(metrosResponse.getMetros(), Metro::getMetroId);
            }

            return null;
        });
    }

    /**
     * Counties of every metro area, by metro area id.
     *
     * The first call is expensive: it requests the metro areas ({@code getMetroArea}) and then the counties of each
     * metro
     * area ({@code getMetroAreaInfo}), one request per metro area.  The map is cached after that.
     *
     * @return read-only map, or null if the metro areas aren't available
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,List<County>> getMetroCountiesMap() throws RadioReferenceException
    {
        return mMetroCounties.get(() -> {
            Map<Integer,Metro> metrosMap = getMetrosMap();

            if(metrosMap == null)
            {
                return null;
            }

            Map<Integer,List<County>> metroCounties = new TreeMap<>();

            for(Metro metro: metrosMap.values())
            {
                ResponseEnvelope response = submitSync(GetMetroInfo.create(mAuthorizationInformation, metro.getMetroId()));

                if(response != null && response.getResponseBody() instanceof GetMetroInfoResponse metroInfoResponse)
                {
                    List<County> counties = metroInfoResponse.getCounties();
                    metroCounties.put(metro.getMetroId(),
                        counties != null ? Collections.unmodifiableList(counties) : Collections.emptyList());
                }
            }

            return Collections.unmodifiableMap(metroCounties);
        });
    }

    /**
     * Counties of the metro area (operation {@code getMetroAreaInfo}).
     *
     * @param metroId of the metro area
     * @return counties, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Metro areas that include counties of the state.
     *
     * Uses {@link #getStateInfo(int)} and {@link #getMetroCountiesMap()}, so the first call is expensive.
     *
     * @param stateId of the state, e.g. 36 for New York
     * @return metro areas, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<Metro> getMetrosByState(int stateId) throws RadioReferenceException
    {
        List<Metro> metros = new ArrayList<>();

        StateInfo stateInfo = getStateInfo(stateId);
        Map<Integer,List<County>> metroMap = getMetroCountiesMap();

        if(stateInfo != null && metroMap != null)
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

                            if(metro != null && !metros.contains(metro))
                            {
                                metros.add(metro);
                            }
                        }
                    }
                }
            }
        }

        return metros;
    }

    /**
     * Function tags by id: the uses that classify frequencies and talkgroups (operation {@code getTag}).
     *
     * Loaded on the first call and cached until {@link #clearTagMap()}.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the tags
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Tag> getTagsMap() throws RadioReferenceException
    {
        return mTags.get(() -> {
            ResponseEnvelope response = submitSync(GetTags.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetTagsResponse tagsResponse)
            {
                return toMap(tagsResponse.getTags(), Tag::getTagId);
            }

            return null;
        });
    }

    /**
     * Clears the cached tags so that the next {@link #getTagsMap()} call requests them again
     */
    public void clearTagMap()
    {
        mTags.clear();
    }

    /**
     * Function tag with the id, from {@link #getTagsMap()}.
     *
     * @param tagId of the tag
     * @return tag, or null if there is no tag with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Tag getTag(int tagId) throws RadioReferenceException
    {
        Map<Integer,Tag> tags = getTagsMap();
        return tags != null ? tags.get(tagId) : null;
    }

    /**
     * Trunked system type (protocol) with the id, from {@link #getTypesMap()}.
     *
     * @param typeId of the type, e.g. from {@link SystemInformation#getTypeId()}
     * @return type, or null if there is no type with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Type getType(int typeId) throws RadioReferenceException
    {
        Map<Integer,Type> types = getTypesMap();
        return types != null ? types.get(typeId) : null;
    }

    /**
     * Trunked system types (protocols) by id (operation {@code getTrsType}).  {@link SystemInformation#getTypeId()} and
     * {@link io.github.dsheirer.rrapi.type.System#getTypeId()} hold the type id.
     *
     * Loaded on the first call and cached.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the types
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Type> getTypesMap() throws RadioReferenceException
    {
        return mTypes.get(() -> {
            ResponseEnvelope response = submitSync(GetTypes.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetTypesResponse typesResponse)
            {
                return toMap(typesResponse.getTypes(), Type::getTypeId);
            }

            return null;
        });
    }

    /**
     * Voice codecs by id, for the voice channels of trunked systems (operation {@code getTrsVoice}).
     * {@link io.github.dsheirer.rrapi.type.System#getVoiceId()} holds the voice id.
     *
     * Loaded on the first call and cached.  Concurrent callers share one request.
     *
     * @return read-only map, or null if the response doesn't include the voices
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Map<Integer,Voice> getVoicesMap() throws RadioReferenceException
    {
        return mVoices.get(() -> {
            ResponseEnvelope response = submitSync(GetVoices.create(mAuthorizationInformation));

            if(response != null && response.getResponseBody() instanceof GetVoicesResponse voicesResponse)
            {
                return toMap(voicesResponse.getVoices(), Voice::getVoiceId);
            }

            return null;
        });
    }

    /**
     * Voice codec with the id, from {@link #getVoicesMap()}.
     *
     * @param voiceId of the voice codec
     * @return voice codec, or null if there is no voice codec with the id
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public Voice getVoice(int voiceId) throws RadioReferenceException
    {
        Map<Integer,Voice> voices = getVoicesMap();
        return voices != null ? voices.get(voiceId) : null;
    }

    /**
     * Location of the United States zip code: city, state, county and coordinates (operation {@code getZipcodeInfo}).
     *
     * @param zipcode five digit zip code
     * @return zip code information, or null if the response doesn't include it
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Frequencies of the subcategory (operation {@code getSubcatFreqs}).  The subcategories are listed in the
     * categories of {@link CountyInfo} and {@link AgencyInfo}.
     *
     * @param subCategoryId of the subcategory
     * @return frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Frequencies in the county that match the frequency (operation {@code searchCountyFreq}).  The frequency is sent
     * rounded to the nearest Hz.
     *
     * @param countyId of the county
     * @param frequency in MHz, e.g. 460.0375
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<SearchFrequencyResult> searchCountyFrequencies(int countyId, double frequency) throws RadioReferenceException
    {
        return searchCountyFrequencies(countyId, frequency, null);
    }

    /**
     * Frequencies in the county that match the frequency and the tone (operation {@code searchCountyFreq}).  The
     * frequency is sent
     * rounded to the nearest Hz.
     *
     * @param countyId of the county
     * @param frequency in MHz, e.g. 460.0375
     * @param tone to match as the service lists it in {@link SearchFrequencyResult#getTone()}, or null for any tone
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Frequencies in the state that match the frequency (operation {@code searchStateFreq}).  The frequency is sent
     * rounded to the nearest Hz.
     *
     * @param stateId of the state
     * @param frequency in MHz, e.g. 460.0375
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<SearchFrequencyResult> searchStateFrequencies(int stateId, double frequency) throws RadioReferenceException
    {
        return searchStateFrequencies(stateId, frequency, null);
    }

    /**
     * Frequencies in the state that match the frequency and the tone (operation {@code searchStateFreq}).  The
     * frequency is sent
     * rounded to the nearest Hz.
     *
     * @param stateId of the state
     * @param frequency in MHz, e.g. 460.0375
     * @param tone to match as the service lists it in {@link SearchFrequencyResult#getTone()}, or null for any tone
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * Frequencies in the metro area that match the frequency (operation {@code searchMetroFreq}).  The frequency is
     * sent
     * rounded to the nearest Hz.
     *
     * @param metroId of the metro area
     * @param frequency in MHz, e.g. 460.0375
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
     */
    public List<SearchFrequencyResult> searchMetroFrequencies(int metroId, double frequency) throws RadioReferenceException
    {
        return searchMetroFrequencies(metroId, frequency, null);
    }

    /**
     * Frequencies in the metro area that match the frequency and the tone (operation {@code searchMetroFreq}).  The
     * frequency is sent
     * rounded to the nearest Hz.
     *
     * @param metroId of the metro area
     * @param frequency in MHz, e.g. 460.0375
     * @param tone to match as the service lists it in {@link SearchFrequencyResult#getTone()}, or null for any tone
     * @return matching frequencies, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * FCC license details of the callsign: licensee, status, radio service, and the licensed locations and
     * frequencies (operation {@code fccGetCallsign}).
     *
     * @param callsign FCC callsign, e.g. "WQDY448"
     * @return license details, or null if the response doesn't include them
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * All FCC radio service codes with their descriptions (operation {@code fccGetRadioServiceCode} without a code).
     *
     * @return radio service codes, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * FCC radio service code and its description (operation {@code fccGetRadioServiceCode}).
     *
     * @param code two letter FCC radio service code, e.g. "AB"
     * @return radio service code, or null if the response doesn't include it
     * @throws RadioReferenceException with error type INVALID_REQUEST if the code isn't two characters, or if the
     *     request fails
     */
    public FccRadioServiceCode getFccRadioServiceCode(String code) throws RadioReferenceException
    {
        if(code == null || code.length() != 2)
        {
            throw new RadioReferenceException(RadioReferenceException.ErrorType.INVALID_REQUEST,
                "Radio service code invalid [" + code + "] must be non-null and 2-digits");
        }

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
     * FCC callsigns licensed near the location (operation {@code fccGetProxCallsigns}).
     *
     * @param latitude of the search location in decimal degrees
     * @param longitude of the search location in decimal degrees
     * @param range search radius in kilometers, at most 4.82803 km (3 miles); larger values are reduced to the maximum
     * @return callsigns with their distance from the location, or an empty list
     * @throws RadioReferenceException if the request fails; see {@link RadioReferenceException#getErrorType()}
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
     * A call of one or more service methods, for running with {@link #async(ServiceCall)}
     * @param <T> result type
     */
    @FunctionalInterface
    public interface ServiceCall<T>
    {
        /**
         * Calls the service
         * @param service to call
         * @return result
         * @throws RadioReferenceException if there is an error
         */
        T call(RadioReferenceService service) throws RadioReferenceException;
    }

    /**
     * Runs the call on a new virtual thread and returns its result as a future, for example:
     *
     * <pre>{@code
     * CompletableFuture<List<SearchFrequencyResult>> results =
     *     service.async(s -> s.searchCountyFrequencies(countyId, 460.0375));
     * }</pre>
     *
     * Cancelling the returned future interrupts the call, which aborts the HTTP request in progress.  Cancel the
     * returned future itself: cancelling a future derived from it, e.g. with thenApply(), doesn't interrupt the call.
     *
     * @param call of one or more service methods
     * @param <T> result type
     * @return future that completes with the result, or exceptionally with a RadioReferenceException
     */
    public <T> CompletableFuture<T> async(ServiceCall<T> call)
    {
        InterruptibleFuture<T> future = new InterruptibleFuture<>();

        Thread.ofVirtual().name("rrapi-async").start(() -> {
            if(!future.started(Thread.currentThread()))
            {
                return; //Cancelled before the call started
            }

            try
            {
                future.complete(call.call(this));
            }
            catch(Throwable t)
            {
                future.completeExceptionally(t);
            }
            finally
            {
                future.finished();
            }
        });

        return future;
    }

    /**
     * Future that interrupts the thread running its call when it is cancelled
     */
    private static final class InterruptibleFuture<T> extends CompletableFuture<T>
    {
        private Thread mThread;
        private boolean mFinished;

        /**
         * Records the thread running the call
         * @return false if the future was already cancelled
         */
        private synchronized boolean started(Thread thread)
        {
            mThread = thread;
            return !isCancelled();
        }

        private synchronized void finished()
        {
            mFinished = true;
            mThread = null;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning)
        {
            boolean cancelled = super.cancel(mayInterruptIfRunning);

            if(cancelled)
            {
                synchronized(this)
                {
                    if(mThread != null && !mFinished)
                    {
                        mThread.interrupt();
                    }
                }
            }

            return cancelled;
        }
    }

    /**
     * Value loaded on first use and cached.  Concurrent callers wait for a single load instead of each calling the web
     * service.  A null load result isn't cached, so the next call tries again.
     */
    private static final class CachedValue<T>
    {
        private volatile T mValue;

        T get(Loader<T> loader) throws RadioReferenceException
        {
            T value = mValue;

            if(value == null)
            {
                synchronized(this)
                {
                    value = mValue;

                    if(value == null)
                    {
                        value = loader.load();
                        mValue = value;
                    }
                }
            }

            return value;
        }

        void clear()
        {
            mValue = null;
        }
    }

    @FunctionalInterface
    private interface Loader<T>
    {
        T load() throws RadioReferenceException;
    }

    /**
     * Read-only map of the items by id
     */
    private static <T> Map<Integer,T> toMap(List<T> items, ToIntFunction<T> id)
    {
        Map<Integer,T> map = new TreeMap<>();

        if(items != null)
        {
            for(T item: items)
            {
                if(item != null)
                {
                    map.put(id.applyAsInt(item), item);
                }
            }
        }

        return Collections.unmodifiableMap(map);
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
            throw new RadioReferenceException(ErrorType.UNKNOWN, "Error serializing request envelope to XML", jpe);
        }

        SoapTransport.Response response;

        try
        {
            response = mTransport.post(xml);
        }
        catch(HttpTimeoutException hte)
        {
            throw new RadioReferenceException(ErrorType.TIMEOUT, "Timed out waiting for the radioreference.com web service", hte);
        }
        catch(IOException ioe)
        {
            throw new RadioReferenceException(ErrorType.NETWORK, "Unable to reach the radioreference.com web service: " +
                ioe.getMessage(), ioe);
        }
        catch(InterruptedException ie)
        {
            //Preserve the interrupt for the caller, e.g. a cancelled async call
            Thread.currentThread().interrupt();
            throw new RadioReferenceException(ErrorType.INTERRUPTED,
                "Interrupted while waiting for the radioreference.com web service", ie);
        }

        if(response == null)
        {
            throw new RadioReferenceException(ErrorType.SERVICE_ERROR, "No response from the radioreference.com web service");
        }

        String body = response.body();
        ResponseEnvelope envelope = null;
        IOException parseError = null;

        if(body != null && !body.isBlank())
        {
            try
            {
                envelope = deserialize(body);
            }
            catch(IOException ioe)
            {
                parseError = ioe;
            }
        }

        //Faults are errors whatever the HTTP status.  The service sends them with HTTP 500.
        if(envelope != null && envelope.getResponseBody() instanceof Fault fault)
        {
            throw new RadioReferenceException(RadioReferenceException.typeOf(fault, response.statusCode()),
                fault.getFaultCode() + ": " + fault.getFaultString(), response.statusCode(), fault)
                .withRetryAfter(response.retryAfter());
        }

        if(response.statusCode() != 200 || envelope == null)
        {
            ErrorType errorType;
            String description;

            if(body == null || body.isBlank())
            {
                errorType = response.statusCode() == 200 ? ErrorType.SERVICE_ERROR :
                    RadioReferenceException.typeOf(null, response.statusCode());
                description = "an empty response";
            }
            else if(parseError != null && body.contains("Envelope"))
            {
                //A SOAP response that doesn't match the library's classes: the API may have changed
                errorType = response.statusCode() == 200 ? ErrorType.UNKNOWN :
                    RadioReferenceException.typeOf(null, response.statusCode());
                description = "a SOAP response that can't be parsed (" + parseError.getMessage() + ")";
            }
            else
            {
                errorType = response.statusCode() == 200 ? ErrorType.SERVICE_ERROR :
                    RadioReferenceException.typeOf(null, response.statusCode());
                description = "an unexpected response: " + excerpt(body);
            }

            mLog.debug("Unexpected response with HTTP status " + response.statusCode() + ": " + excerpt(body));

            RadioReferenceException exception = new RadioReferenceException(errorType,
                "The radioreference.com web service returned HTTP " + response.statusCode() + " with " + description,
                response.statusCode(), null);

            if(parseError != null)
            {
                exception.initCause(parseError);
            }

            throw exception.withRetryAfter(response.retryAfter());
        }

        return envelope;
    }

    /**
     * Start of the text, with whitespace collapsed, for error messages
     */
    private static String excerpt(String text)
    {
        if(text == null)
        {
            return "";
        }

        String collapsed = text.replaceAll("\\s+", " ").trim();
        return collapsed.length() > 200 ? collapsed.substring(0, 200) + "..." : collapsed;
    }

    /**
     * Parses an HTTP Retry-After header value: a number of seconds or an HTTP date.  Package-private for testing.
     * @param value of the header
     * @return delay, or null if the value can't be parsed
     */
    static Duration parseRetryAfter(String value)
    {
        if(value == null || value.isBlank())
        {
            return null;
        }

        String trimmed = value.trim();

        try
        {
            if(trimmed.chars().allMatch(Character::isDigit))
            {
                return Duration.ofSeconds(Long.parseLong(trimmed));
            }

            Duration delay = Duration.between(Instant.now(),
                ZonedDateTime.parse(trimmed, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant());
            return delay.isNegative() ? Duration.ZERO : delay;
        }
        catch(RuntimeException e)
        {
            return null;
        }
    }

    /**
     * Deserializes the xml response using Faster Jackson xml annotations.
     * @param xml to deserialize
     * @return deserialized response envelope or null if there is nothing to deserialize
     * @throws IOException if there are any errors
     */
    static ResponseEnvelope deserialize(String xml) throws IOException
    {
        if(xml != null)
        {
            XmlMapper mapper = new XmlMapper();
            return mapper.readValue(xml, ResponseEnvelope.class);
        }

        return null;
    }
}
