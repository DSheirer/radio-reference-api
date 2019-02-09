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

public class StateInfo
{
    private int mStateId;
    private String mName;
    private String mStateEntityType;
    private List<SystemItem> mSystemItems;
    private List<Agency> mAgencies = new ArrayList<>();
    private List<County> mCounties = new ArrayList<>();

    public StateInfo()
    {
    }

    @JacksonXmlProperty(localName = "stid")
    public int getStateId()
    {
        return mStateId;
    }

    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    @JacksonXmlProperty(localName = "stateName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "stateEntityType")
    public String getStateEntityType()
    {
        return mStateEntityType;
    }

    public void setStateEntityType(String stateEntityType)
    {
        mStateEntityType = stateEntityType;
    }

    @JacksonXmlProperty(localName = "trsList")
    public List<SystemItem> getSystemItems()
    {
        return mSystemItems;
    }

    public void setSystemItems(List<SystemItem> systemItems)
    {
        mSystemItems = systemItems;
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

    @JacksonXmlProperty(localName = "countyList")
    public List<County> getCounties()
    {
        return mCounties;
    }

    public void setCounties(List<County> counties)
    {
        mCounties = counties;
    }
}
