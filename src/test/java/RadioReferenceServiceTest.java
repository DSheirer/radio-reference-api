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

import io.github.dsheirer.rrapi.RadioReferenceException;
import io.github.dsheirer.rrapi.RadioReferenceService;
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
public class RadioReferenceServiceTest
{
    private static final String SDRTRUNK_APPLICATION_KEY = "88969092";
    private static final Logger mLog = LoggerFactory.getLogger(RadioReferenceServiceTest.class);
    private static RadioReferenceService sRADIO_REFERENCE_SERVICE;
    private static State sSTATE_NEW_YORK;
    private static County sCOUNTY_OSWEGO;
    private static Metro sMETRO_SYRACUSE;
    private static System sSYSTEM_CNYICC;
    private static long mCounter = 0;

    public RadioReferenceServiceTest()
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
        Assertions.assertTrue(states != null && states.size() > 0);
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

            for(Tag tag: tagMap.values())
            {
                List<Frequency> frequencies = service.getAgencyFrequenciesByTag(agency.getAgencyId(), tag.getTagId());

                if(frequencies.size() > 0)
                {
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

                for(Tag tag: tagMap.values())
                {
                    List<Frequency> frequencies = service.getCountyFrequenciesByTag(county.getCountyId(), tag.getTagId());

                    if(frequencies.size() > 0)
                    {
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
        //Note: this doesn't seem to produce any results from the API.
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
}
