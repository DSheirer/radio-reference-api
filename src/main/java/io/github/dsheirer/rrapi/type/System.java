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
import java.util.Date;
import java.util.List;

public class System
{
    private String mName;
    private int mType;
    private int mFlavor;
    private int mVoice;
    private String mCity;
    private List<County> mCounties = new ArrayList<>();
    private List<State> mStates = new ArrayList<>();
    private String mCountry;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private Date mLastUpdated;
    private List<SystemId> mSystemIds;
    private Bandplan mBandplan;
    private Fleetmap mFleetmap;

    public System()
    {
    }

    @JacksonXmlProperty(localName = "sName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "sType")
    public int getType()
    {
        return mType;
    }

    public void setType(int type)
    {
        mType = type;
    }

    @JacksonXmlProperty(localName = "sFlavor")
    public int getFlavor()
    {
        return mFlavor;
    }

    public void setFlavor(int flavor)
    {
        mFlavor = flavor;
    }

    @JacksonXmlProperty(localName = "sVoice")
    public int getVoice()
    {
        return mVoice;
    }

    public void setVoice(int voice)
    {
        mVoice = voice;
    }

    @JacksonXmlProperty(localName = "sCity")
    public String getCity()
    {
        return mCity;
    }

    public void setCity(String city)
    {
        mCity = city;
    }

    @JacksonXmlProperty(localName = "sCounty")
    public List<County> getCounties()
    {
        return mCounties;
    }

    public void setCounties(List<County> counties)
    {
        mCounties = counties;
    }

    @JacksonXmlProperty(localName = "sState")
    public List<State> getStates()
    {
        return mStates;
    }

    public void setStates(List<State> states)
    {
        mStates = states;
    }

    @JacksonXmlProperty(localName = "sCountry")
    public String getCountry()
    {
        return mCountry;
    }

    public void setCountry(String country)
    {
        mCountry = country;
    }

    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    @JacksonXmlProperty(localName = "range")
    public double getRange()
    {
        return mRange;
    }

    public void setRange(double range)
    {
        mRange = range;
    }

    @JacksonXmlProperty(localName = "lastUpdated")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    @JacksonXmlProperty(localName = "sysid")
    public List<SystemId> getSystemIds()
    {
        return mSystemIds;
    }

    public void setSystemIds(List<SystemId> systemIds)
    {
        mSystemIds = systemIds;
    }

    @JacksonXmlProperty(localName = "bandplan")
    public Bandplan getBandplan()
    {
        return mBandplan;
    }

    public void setBandplan(Bandplan bandplan)
    {
        mBandplan = bandplan;
    }

    @JacksonXmlProperty(localName = "fleetmap")
    public Fleetmap getFleetmap()
    {
        return mFleetmap;
    }

    public void setFleetmap(Fleetmap fleetmap)
    {
        mFleetmap = fleetmap;
    }
}
