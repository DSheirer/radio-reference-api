/*
 * *****************************************************************************
 * Copyright (C) 2022 Dennis Sheirer
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
import io.github.dsheirer.rrapi.type.Type;
import io.github.dsheirer.rrapi.type.UserInfo;
import io.github.dsheirer.rrapi.type.Voice;
import io.github.dsheirer.rrapi.type.ZipInfo;
import io.github.dsheirer.rrapi.type.Category;
import io.github.dsheirer.rrapi.type.SubCategory;
import io.github.dsheirer.rrapi.type.TalkgroupCategory;
import io.github.dsheirer.rrapi.type.TalkgroupRequestFilter;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assumptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit testing for RR service
 */
/**
 * Calls the live radioreference.com web service.  Runs only with ./gradlew test -PliveTests, using credentials stored
 * in your gradle.properties file as radio.reference.user and radio.reference.password
 */
@org.junit.jupiter.api.Tag("live")
public class RadioReferenceServiceLiveTest
{
    private static final String SDRTRUNK_APPLICATION_KEY = "88969092";
    private static final Logger mLog = LoggerFactory.getLogger(RadioReferenceServiceLiveTest.class);
    private static RadioReferenceService sRADIO_REFERENCE_SERVICE;
    private static State sSTATE_NEW_YORK;
    private static County sCOUNTY_OSWEGO;
    private static Metro sMETRO_SYRACUSE;
    private static System sSYSTEM_CNYICC;
    private static long mCounter = 0;

    public RadioReferenceServiceLiveTest()
    {
    }

    /**
     * Constructs an instance of the radio reference service using user-supplied credentials
     *
     * @return instance or null.
     */
    public RadioReferenceService getService()
    {
        if(sRADIO_REFERENCE_SERVICE == null)
        {
            String name = java.lang.System.getenv("RADIO_REFERENCE_USER");
            String password = java.lang.System.getenv("RADIO_REFERENCE_PASSWORD");
            //            mLog.info("Found username [" + name + "] and password [" + password + "]");

            if(name == null || password == null)
            {
                throw new IllegalArgumentException("Radio Reference service user name and password are required for testing");
            }

            AuthorizationInformation auth = new AuthorizationInformation(SDRTRUNK_APPLICATION_KEY, name, password);

            try
            {
                sRADIO_REFERENCE_SERVICE = new RadioReferenceService(auth);
            }
            catch(Exception e)
            {
                mLog.error("Error creating radio reference service", e);
            }
        }

        return sRADIO_REFERENCE_SERVICE;
    }

    /**
     * New York state instance
     * @return new york state
     * @throws RadioReferenceException
     */
    public State getNewYork() throws RadioReferenceException
    {
        if(sSTATE_NEW_YORK == null)
        {
            RadioReferenceService service = getService();
            CountryInfo usa = service.getCountryInfo(1);
            Assertions.assertNotNull(usa);

            for(State state : usa.getStates())
            {
                if(state.getStateId() == 36 || state.getName().toLowerCase().contains("new york"))
                {
                    sSTATE_NEW_YORK = state;
                    return sSTATE_NEW_YORK;
                }
            }
        }

        Assertions.assertNotNull(sSTATE_NEW_YORK);
        return sSTATE_NEW_YORK;
    }

    /**
     * Oswego county NY
     * @return county or null.
     * @throws RadioReferenceException
     */
    public County getOswego() throws RadioReferenceException
    {
        if(sCOUNTY_OSWEGO == null)
        {
            RadioReferenceService service = getService();
            State newYork = getNewYork();
            StateInfo stateInfo = service.getStateInfo(newYork.getStateId());
            Assertions.assertNotNull(stateInfo);
            for(County county : stateInfo.getCounties())
            {
                if(county.getName().toLowerCase().equals("oswego"))
                {
                    sCOUNTY_OSWEGO = county;
                    return sCOUNTY_OSWEGO;
                }
            }
        }

        Assertions.assertNotNull(sCOUNTY_OSWEGO);
        return sCOUNTY_OSWEGO;
    }

    /**
     * Syracuse metro area
     * @return syracuse or null.
     * @throws RadioReferenceException
     */
    public Metro getSyracuse() throws RadioReferenceException
    {
        if(sMETRO_SYRACUSE == null)
        {
            RadioReferenceService service = getService();
            State newYork = getNewYork();
            List<Metro> metros = service.getMetrosByState(newYork.getStateId());
            Assertions.assertNotNull(metros);
            for(Metro metro : metros)
            {
                if(metro.getName().toLowerCase().equals("syracuse"))
                {
                    sMETRO_SYRACUSE = metro;
                    return sMETRO_SYRACUSE;
                }
            }
        }

        Assertions.assertNotNull(sMETRO_SYRACUSE);
        return sMETRO_SYRACUSE;
    }

    /**
     * CNYICC system instance
     * @return system
     * @throws RadioReferenceException
     */
    public System getCNYICC() throws RadioReferenceException
    {
        if(sSYSTEM_CNYICC == null)
        {
            RadioReferenceService service = getService();
            State newYork = getNewYork();
            StateInfo newYorkStateInfo = service.getStateInfo(newYork.getStateId());
            Assertions.assertNotNull(newYorkStateInfo);

            for(County county: newYorkStateInfo.getCounties())
            {
                if(county.getName().toLowerCase().contains("onondaga"))
                {
                    CountyInfo countyInfo = service.getCountyInfo(county.getCountyId());
                    Assertions.assertNotNull(countyInfo);

                    for(System system: countyInfo.getSystems())
                    {
                        if(system.getSystemId() == 5298 || system.getName().toLowerCase().contains("cnyicc"))
                        {
                            sSYSTEM_CNYICC = system;
                            return sSYSTEM_CNYICC;
                        }
                    }
                }
            }
        }

        return sSYSTEM_CNYICC;
    }

    /**
     * User information
     *
     * @throws RadioReferenceException
     */
    @Test
    void getUserInfo() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        UserInfo userInfo = service.getUserInfo();
        Assertions.assertNotNull(userInfo);
    }

    /**
     * List of countries
     *
     * @throws RadioReferenceException
     */
    @Test
    void getCountries() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<Country> countries = service.getCountries();
        Assertions.assertTrue(countries != null && countries.size() > 0);
    }

    /**
     * Get country where country ID 1 (USA)
     *
     * @throws RadioReferenceException
     */
    @Test
    void getCountryById() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Country usa = service.getCountry(1);
        Assertions.assertNotNull(usa);
        Assertions.assertEquals(1, usa.getCountryId());
        Assertions.assertEquals("United States", usa.getName());
    }

    /**
     * Get CountryInfo where country ID 1 (USA)
     *
     * @throws RadioReferenceException
     */
    @Test
    void getCountryInfoById() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        CountryInfo usa = service.getCountryInfo(1);
        Assertions.assertNotNull(usa);
        Assertions.assertEquals(1, usa.getCountryId());
        Assertions.assertTrue(usa.getStates().size() >= 50, "USA states");
    }

    /**
     * Get states by ID
     *
     * @throws RadioReferenceException
     */
    @Test
    void getStates() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<Integer> stateIds = new ArrayList<>();
        stateIds.add(1);
        stateIds.add(2);
        stateIds.add(3);
        List<State> states = service.getStates(stateIds);
        Assertions.assertNotNull(states);
        Assertions.assertEquals(Set.copyOf(stateIds), states.stream().map(State::getStateId).collect(Collectors.toSet()),
            "requested state ids");
    }

    /**
     * Get counties by county ID, using the first counties of New York state
     * @throws RadioReferenceException
     */
    @Test
    void getCounties() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);
        List<Integer> countyIds = service.getStateInfo(newYork.getStateId()).getCounties().stream()
            .limit(3).map(County::getCountyId).toList();
        Assertions.assertFalse(countyIds.isEmpty());
        List<County> counties = service.getCounties(countyIds);
        Assertions.assertEquals(Set.copyOf(countyIds), counties.stream().map(County::getCountyId).collect(Collectors.toSet()),
            "requested county ids");
    }

    /**
     * Get NY systems and sites
     * @throws RadioReferenceException
     */
    @Test
    void getNewYorkSystemsAndSites() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);

        StateInfo newYorkStateInfo = service.getStateInfo(newYork.getStateId());
        Assertions.assertNotNull(newYorkStateInfo);

        County onondaga = null;

        for(County county : newYorkStateInfo.getCounties())
        {
            if(county.getCountyId() == 1858 || county.getName().toLowerCase().contains("onondaga"))
            {
                onondaga = county;
            }
        }

        Assertions.assertNotNull(onondaga);

        CountyInfo countyInfo = service.getCountyInfo(onondaga.getCountyId());
        Assertions.assertNotNull(countyInfo);

        if(countyInfo != null && countyInfo.getSystems().size() > 0)
        {
            System system = countyInfo.getSystems().get(0);
            Assertions.assertNotNull(system);

            SystemInformation systemInformation = service.getSystemInformation(system.getSystemId());
            Assertions.assertNotNull(systemInformation);

            List<Site> sites = service.getSites(system.getSystemId());
            Assertions.assertTrue(sites != null && sites.size() > 0);
        }
    }

    /**
     * Get NY agencies
     * @throws RadioReferenceException
     */
    @Test
    void getNewYorkAgencies() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);
        StateInfo newYorkStateInfo = service.getStateInfo(newYork.getStateId());
        Assertions.assertNotNull(newYorkStateInfo);

        if(newYorkStateInfo.getAgencies().size() > 0)
        {
            Agency agency = newYorkStateInfo.getAgencies().get(0);
            AgencyInfo agencyInfo = service.getAgencyInfo(agency);
            Assertions.assertNotNull(agencyInfo);
            agencyInfo = service.getAgencyInfo(agency.getAgencyId());
            Assertions.assertNotNull(agencyInfo);
        }
    }

    /**
     * Get agencies by tag
     * @throws RadioReferenceException
     */
    @Test
    void getAgencyFrequenciesByTag() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);
        StateInfo newYorkStateInfo = service.getStateInfo(newYork.getStateId());
        Assertions.assertNotNull(newYorkStateInfo);

        if(newYorkStateInfo.getAgencies().size() > 0)
        {
            Agency agency = newYorkStateInfo.getAgencies().get(0);
            AgencyInfo agencyInfo = service.getAgencyInfo(agency);
            Assertions.assertNotNull(agencyInfo);
            agencyInfo = service.getAgencyInfo(agency.getAgencyId());
            Assertions.assertNotNull(agencyInfo);

            Map<Integer, Tag> tagMap = service.getTagsMap();
            Assertions.assertNotNull(tagMap);

            boolean found = false;

            Set<Integer> agencySubCategories = subCategoryIds(agencyInfo.getCategories());

            for(Tag tag: tagMap.values())
            {
                List<Frequency> frequencies = service.getAgencyFrequenciesByTag(agency.getAgencyId(), tag.getTagId());

                if(frequencies.size() > 0)
                {
                    for(Frequency frequency: frequencies)
                    {
                        Assertions.assertTrue(agencySubCategories.contains(frequency.getSubCategoryId()),
                            "frequency subcategory " + frequency.getSubCategoryId() + " belongs to agency " +
                                agency.getAgencyId());
                    }
                    found = true;
                    break;
                }
            }

            Assertions.assertTrue(found);
        }
    }

    /**
     * Get CNYICC system by system ID.
     * @throws RadioReferenceException
     */
    @Test
    void getApco25System() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<System> systems = service.getApco25Systems("2AE"); //CNYICC in Central NY
        Assertions.assertNotNull(systems);
        Assertions.assertTrue(systems.size() > 0);
    }

    /**
     * County frequencies by tag.
     * @throws RadioReferenceException
     */
    @Test
    void getCountyFrequenciesByTag() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);
        StateInfo newYorkStateInfo = service.getStateInfo(newYork.getStateId());
        Assertions.assertNotNull(newYorkStateInfo);

        boolean found = false;

        if(newYorkStateInfo.getCounties().size() > 0)
        {
            for(County county: newYorkStateInfo.getCounties())
            {
                CountyInfo countyInfo = service.getCountyInfo(county.getCountyId());
                Assertions.assertNotNull(countyInfo);
                Map<Integer, Tag> tagMap = service.getTagsMap();
                Assertions.assertNotNull(tagMap);

                Set<Integer> countySubCategories = subCategoryIds(countyInfo.getCategories());

                for(Tag tag: tagMap.values())
                {
                    List<Frequency> frequencies = service.getCountyFrequenciesByTag(county.getCountyId(), tag.getTagId());

                    if(frequencies.size() > 0)
                    {
                        //County results include the frequencies of the agencies in the county (e.g. Albany county
                        //returns its Military agency frequencies)
                        for(Frequency frequency: frequencies)
                        {
                            if(!countySubCategories.contains(frequency.getSubCategoryId()))
                            {
                                for(Agency agency: countyInfo.getAgencies())
                                {
                                    countySubCategories.addAll(subCategoryIds(service.getAgencyInfo(agency).getCategories()));

                                    if(countySubCategories.contains(frequency.getSubCategoryId()))
                                    {
                                        break;
                                    }
                                }
                            }

                            Assertions.assertTrue(countySubCategories.contains(frequency.getSubCategoryId()),
                                "frequency subcategory " + frequency.getSubCategoryId() + " belongs to county " +
                                    county.getCountyId() + " or one of its agencies");
                        }
                        found = true;
                        break;
                    }
                }

                if(found)
                {
                    break;
                }
            }

            Assertions.assertTrue(found);
        }
    }

    /**
     * FCC callsign details for "WQDY448".
     * @throws RadioReferenceException
     */
    @Test
    void getFccCallsignDetails() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        FccCallsignDetails details = service.getFccCallsignDetails("WQDY448");
        Assertions.assertNotNull(details);
        Assertions.assertEquals("WQDY448", details.getCallsign());
    }

    /**
     * Get callsigns within 3 km of Syracuse New York geo area.
     * @throws RadioReferenceException
     */
    @Test
    void getFccCallsignsNearLocation() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<ProximityCallsignResult> results = service.getFccCallsignsNearLocation(42.94833, -76.02389, 3.0); //Syracuse
//        List<ProximityCallsignResult> results = service.getFccCallsignsNearLocation(29.42475, -98.49476, 15.0); //San Antonio
        Assertions.assertNotNull(results);
        Assertions.assertFalse(results.isEmpty(), "callsigns within 3 km of Syracuse");
        for(ProximityCallsignResult result: results)
        {
            Assertions.assertTrue(result.getDistance() <= 3.0, "distance " + result.getDistance() + " within range");
        }
    }

    /**
     * Radio service code 'AB'
     * @throws RadioReferenceException
     */
    @Test
    void getFccRadioServiceCode() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        FccRadioServiceCode code = service.getFccRadioServiceCode("AB");
        Assertions.assertNotNull(code);
        Assertions.assertEquals("AB", code.getCode());
    }

    /**
     * FCC radio service codes list
     * @throws RadioReferenceException
     */
    @Test
    void getFccRadioServiceCodes() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<FccRadioServiceCode> codes = service.getFccRadioServiceCodes();
        Assertions.assertTrue(codes != null && codes.size() > 0);
    }

    /**
     * Get flavors map
     * @throws RadioReferenceException
     */
    @Test
    void getFlavors() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Flavor> flavorMap = service.getFlavorsMap();
        Assertions.assertTrue(flavorMap != null && flavorMap.size() > 0);
    }

    /**
     * Get metro area for San Antonio.
     * @throws RadioReferenceException
     */
    @Test
    void getMetro() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Metro metro = service.getMetro(1); //San Antonio is number 1?
        Assertions.assertNotNull(metro);
    }

    /**
     * Get counties for San Antonio.
     * @throws RadioReferenceException
     */
    @Test
    void getMetroCounties() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        List<County> metroCounties = service.getMetroCounties(1); //San Antonio is number 1?
        Assertions.assertNotNull(metroCounties);
    }

    /**
     * Get metro areas for New York state
     * @throws RadioReferenceException
     */
    @Test
    void getMetrosByState() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        List<Metro> metros = service.getMetrosByState(newYork.getStateId());
        Assertions.assertNotNull(metros);
    }

    /**
     * Get metro areas mapping.
     * @throws RadioReferenceException
     */
    @Test
    void getMetros() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Metro> metrosMap = service.getMetrosMap();
        Assertions.assertTrue(metrosMap != null && metrosMap.size() > 0);
    }

    /**
     * Get modes map
     * @throws RadioReferenceException
     */

    @Test
    void getModes() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Mode> modesMap = service.getModesMap();
        Assertions.assertTrue(modesMap != null && modesMap.size() > 0);
    }

    /**
     * Get tags map
     * @throws RadioReferenceException
     */
    @Test
    void getTags() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Tag> tagsMap = service.getTagsMap();
        Assertions.assertTrue(tagsMap != null && tagsMap.size() > 0);
    }

    /**
     * Get talkgroups for CNYICC system.
     * @throws RadioReferenceException
     */
    @Test
    void getTalkgroups() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        System system = getCNYICC();
        Assertions.assertNotNull(system);
        List<Talkgroup> talkgroups = service.getTalkgroups(system.getSystemId());
        Assertions.assertTrue(talkgroups != null && talkgroups.size() > 0);
    }

    /**
     * Get types map
     * @throws RadioReferenceException
     */
    @Test
    void getTypes() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Type> typesMap = service.getTypesMap();
        Assertions.assertTrue(typesMap != null && typesMap.size() > 0);
    }

    /**
     * Get voices map
     * @throws RadioReferenceException
     */
    @Test
    void getVoices() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Map<Integer, Voice> voicesMap = service.getVoicesMap();
        Assertions.assertTrue(voicesMap != null && voicesMap.size() > 0);
    }

    /**
     * Zip code information for 13069, Fulton, NY
     * @throws RadioReferenceException
     */
    @Test
    void getZipcodeInfo() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        ZipInfo zipInfo = service.getZipcodeInfo(13069); //Fulton, NY
        Assertions.assertNotNull(zipInfo);
        Assertions.assertEquals(13069, zipInfo.getZipcode());
        Assertions.assertEquals("Fulton", zipInfo.getCity());
    }

    /**
     * Searches for frequency results for 460.0375 MHz in Oswego County, New York state.
     * @throws RadioReferenceException
     */
    @Test
    void searchCountyFrequencies() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        County oswego = getOswego();
        Assertions.assertNotNull(oswego);
        List<SearchFrequencyResult> results = service.searchCountyFrequencies(oswego.getCountyId(), 460.0375);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    /**
     * Searches for frequency results for 460.0375 MHz in New York state.
     * @throws RadioReferenceException
     */
    @Test
    void searchStateFrequencies() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        State newYork = getNewYork();
        Assertions.assertNotNull(newYork);
        List<SearchFrequencyResult> results = service.searchStateFrequencies(newYork.getStateId(), 460.0375);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    /**
     * Searches for frequency results for 460.0375 MHz in Syracuse metro area.
     * @throws RadioReferenceException
     */
    @Test
    void searchMetroFrequencies() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        Metro syracuse = getSyracuse();
        Assertions.assertNotNull(syracuse);
        List<SearchFrequencyResult> results = service.searchMetroFrequencies(syracuse.getMetroId(), 460.0375);
        Assertions.assertNotNull(results);
        Assertions.assertTrue(results.size() > 0);
    }

    /**
     * Collects the subcategory ids of the categories
     */
    private static Set<Integer> subCategoryIds(List<Category> categories)
    {
        Set<Integer> ids = new HashSet<>();
        for(Category category: categories)
        {
            for(SubCategory subCategory: category.getSubCategories())
            {
                ids.add(subCategory.getSubCategoryId());
            }
        }
        return ids;
    }

    @Test
    void getUserFeeds() throws RadioReferenceException
    {
        //Feed broadcast details include stream passwords, so only check that the call succeeds
        Assertions.assertNotNull(getService().getUserFeeds());
    }

    @Test
    void getStateById() throws RadioReferenceException
    {
        State newYork = getNewYork();
        State state = getService().getState(newYork.getStateId());
        Assertions.assertNotNull(state);
        Assertions.assertEquals(newYork.getStateId(), state.getStateId());
        Assertions.assertEquals(newYork.getName(), state.getName());
    }

    @Test
    void getCountyInfo() throws RadioReferenceException
    {
        County oswego = getOswego();
        CountyInfo countyInfo = getService().getCountyInfo(oswego.getCountyId());
        Assertions.assertNotNull(countyInfo);
        Assertions.assertEquals(oswego.getCountyId(), countyInfo.getCountyId());
        Assertions.assertEquals(getNewYork().getStateId(), countyInfo.getStateId());
        Assertions.assertFalse(countyInfo.getCategories().isEmpty(), "Oswego county frequency categories");
    }

    @Test
    void getSubCategoryFrequencies() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        CountyInfo countyInfo = service.getCountyInfo(getOswego().getCountyId());

        for(Category category: countyInfo.getCategories())
        {
            for(SubCategory subCategory: category.getSubCategories())
            {
                List<Frequency> frequencies = service.getSubCategoryFrequencies(subCategory.getSubCategoryId());

                if(!frequencies.isEmpty())
                {
                    for(Frequency frequency: frequencies)
                    {
                        Assertions.assertEquals(subCategory.getSubCategoryId(), frequency.getSubCategoryId());
                        Assertions.assertTrue(frequency.getDownlink() > 0, "downlink frequency");
                    }
                    return;
                }
            }
        }

        Assertions.fail("No Oswego county subcategory has frequencies");
    }

    @Test
    void getSystemInformationAndSites() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        System cnyicc = getCNYICC();
        Assertions.assertNotNull(cnyicc);

        SystemInformation systemInformation = service.getSystemInformation(cnyicc.getSystemId());
        Assertions.assertNotNull(systemInformation);
        Assertions.assertFalse(systemInformation.getName().isBlank(), "system name");

        List<Site> sites = service.getSites(cnyicc.getSystemId());
        Assertions.assertFalse(sites.isEmpty(), "CNYICC sites");
        for(Site site: sites)
        {
            Assertions.assertEquals(cnyicc.getSystemId(), site.getSystemId());
        }
        Assertions.assertTrue(sites.stream().anyMatch(site -> !site.getSiteFrequencies().isEmpty()), "site frequencies");
    }

    @Test
    void getTalkgroupCategoriesAndFilteredTalkgroups() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        System cnyicc = getCNYICC();
        Assertions.assertNotNull(cnyicc);

        List<TalkgroupCategory> categories = service.getTalkgroupCategories(cnyicc.getSystemId());
        Assertions.assertFalse(categories.isEmpty(), "CNYICC talkgroup categories");

        TalkgroupCategory category = categories.get(0);
        TalkgroupRequestFilter filter = new TalkgroupRequestFilter(cnyicc.getSystemId());
        filter.setCategoryId(category.getTalkgroupCategoryId());
        List<Talkgroup> talkgroups = service.getTalkgroups(filter);
        Assertions.assertFalse(talkgroups.isEmpty(), "talkgroups in category " + category.getName());

        for(Talkgroup talkgroup: talkgroups)
        {
            Assertions.assertEquals(category.getTalkgroupCategoryId(), talkgroup.getTalkgroupCategoryId());
        }

        Talkgroup first = talkgroups.get(0);
        TalkgroupRequestFilter decimalFilter = new TalkgroupRequestFilter(cnyicc.getSystemId());
        decimalFilter.setDecimalValue(first.getDecimalValue());
        List<Talkgroup> byDecimal = service.getTalkgroups(decimalFilter);
        Assertions.assertFalse(byDecimal.isEmpty(), "talkgroup " + first.getDecimalValue());
        for(Talkgroup talkgroup: byDecimal)
        {
            Assertions.assertEquals(first.getDecimalValue(), talkgroup.getDecimalValue());
        }
    }

    @Test
    void singleItemLookups() throws RadioReferenceException
    {
        RadioReferenceService service = getService();

        int flavorId = service.getFlavorsMap().keySet().iterator().next();
        Assertions.assertEquals(flavorId, service.getFlavor(flavorId).getFlavorId());

        int modeId = service.getModesMap().keySet().iterator().next();
        Assertions.assertEquals(modeId, service.getMode(modeId).getModeId());

        int typeId = service.getTypesMap().keySet().iterator().next();
        Assertions.assertEquals(typeId, service.getType(typeId).getTypeId());

        int voiceId = service.getVoicesMap().keySet().iterator().next();
        Assertions.assertEquals(voiceId, service.getVoice(voiceId).getVoiceId());

        int tagId = service.getTagsMap().keySet().iterator().next();
        Assertions.assertEquals(tagId, service.getTag(tagId).getTagId());
    }

    @Test
    void searchCountyFrequenciesWithTone() throws RadioReferenceException
    {
        RadioReferenceService service = getService();
        County oswego = getOswego();
        List<SearchFrequencyResult> results = service.searchCountyFrequencies(oswego.getCountyId(), 460.0375);
        String tone = results.stream().map(SearchFrequencyResult::getTone).filter(t -> t != null && !t.isBlank())
            .findFirst().orElse(null);
        Assumptions.assumeTrue(tone != null, "no 460.0375 result in Oswego county has a tone");

        List<SearchFrequencyResult> toneResults = service.searchCountyFrequencies(oswego.getCountyId(), 460.0375, tone);
        Assertions.assertFalse(toneResults.isEmpty(), "results with tone " + tone);
        for(SearchFrequencyResult result: toneResults)
        {
            Assertions.assertEquals(tone, result.getTone());
        }
    }

    @Test
    void invalidIdIsReportedAsInvalidRequest()
    {
        RadioReferenceException exception = Assertions.assertThrows(RadioReferenceException.class,
            () -> getService().getCountyInfo(999999999));

        Assertions.assertEquals(RadioReferenceException.ErrorType.INVALID_REQUEST, exception.getErrorType());
        Assertions.assertFalse(exception.isRetryable());
        Assertions.assertEquals("SERVER", exception.getFault().getFaultCode());
    }

    @Test
    void wrongApplicationKeyIsReported() throws RadioReferenceException
    {
        String name = java.lang.System.getenv("RADIO_REFERENCE_USER");
        String password = java.lang.System.getenv("RADIO_REFERENCE_PASSWORD");
        RadioReferenceService service = new RadioReferenceService(new AuthorizationInformation("00000000", name, password));

        RadioReferenceException exception = Assertions.assertThrows(RadioReferenceException.class,
            () -> service.getCountyInfo(getOswego().getCountyId()));

        Assertions.assertEquals(RadioReferenceException.ErrorType.APPLICATION_KEY, exception.getErrorType());
    }
}
