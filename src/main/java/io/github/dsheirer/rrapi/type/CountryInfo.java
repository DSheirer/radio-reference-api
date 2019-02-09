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

    @JacksonXmlProperty(localName = "coid")
    public int getCountryId()
    {
        return mCountryId;
    }

    public void setCountryId(int countryId)
    {
        mCountryId = countryId;
    }

    @JacksonXmlProperty(localName = "countryName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "countryCode")
    public String getCountryCode()
    {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode)
    {
        mCountryCode = countryCode;
    }

    @JacksonXmlProperty(localName = "agencyList")
    public List<Agency> getAgencies()
    {
        return mAgencies;
    }

    public void setAgencies(List<Agency> agencies)
    {
        mAgencies = agencies;
    }

    @JacksonXmlProperty(localName = "stateList")
    public List<State> getStates()
    {
        return mStates;
    }

    public void setStates(List<State> states)
    {
        mStates = states;
    }
}
