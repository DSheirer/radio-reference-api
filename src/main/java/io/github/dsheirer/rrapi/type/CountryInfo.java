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

package io.github.dsheirer.rrapi.type;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Country information and details.
 */
public class CountryInfo
{
    private int mCountryId;
    private String mName;
    private String mCountryCode;
    private List<Agency> mAgencies = new ArrayList<>();
    private List<State> mStates = new ArrayList<>();

    public CountryInfo()
    {
    }

    /**
     * Country identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "coid")
    public int getCountryId()
    {
        return mCountryId;
    }

    /**
     * Sets the country id
     * @param countryId value
     */
    public void setCountryId(int countryId)
    {
        mCountryId = countryId;
    }

    /**
     * Country name
     * @return name
     */
    @JacksonXmlProperty(localName = "countryName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the country name
     * @param name of the country
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Country code or digraph
     * @return code
     */
    @JacksonXmlProperty(localName = "countryCode")
    public String getCountryCode()
    {
        return mCountryCode;
    }

    /**
     * Sets the country code or digraph
     * @param countryCode or digraph
     */
    public void setCountryCode(String countryCode)
    {
        mCountryCode = countryCode;
    }

    /**
     * List of agencies for the country
     * @return agency listing
     */
    @JacksonXmlProperty(localName = "agencyList")
    public List<Agency> getAgencies()
    {
        return mAgencies;
    }

    /**
     * Sets the list of agencies for the country
     * @param agencies for the country
     */
    public void setAgencies(List<Agency> agencies)
    {
        mAgencies = agencies;
    }

    /**
     * List of states for the country.
     * @return state listing
     */
    @JacksonXmlProperty(localName = "stateList")
    public List<State> getStates()
    {
        return mStates;
    }

    /**
     * Sets the states.
     * @param states for the country
     */
    public void setStates(List<State> states)
    {
        mStates = states;
    }
}
