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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CountyInfo
{
    private int mCountyId;
    private String mName;
    private String mHeader;
    private int mStateId;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private String mFips;
    private Date mLastUpdated;
    private List<Category> mCategories = new ArrayList<>();
    private List<SystemItem> mSystemItems = new ArrayList<>();
    private List<Agency> mAgencies = new ArrayList<>();

    public CountyInfo()
    {
    }

    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }

    @JacksonXmlProperty(localName = "countyName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "countyHeader")
    public String getHeader()
    {
        return mHeader;
    }

    public void setHeader(String header)
    {
        mHeader = header;
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

    @JacksonXmlProperty(localName = "fips")
    public String getFips()
    {
        return mFips;
    }

    public void setFips(String fips)
    {
        mFips = fips;
    }

    @JacksonXmlProperty(localName = "lastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    @JacksonXmlProperty(localName = "cats")
    public List<Category> getCategories()
    {
        return mCategories;
    }

    public void setCategories(List<Category> categories)
    {
        mCategories = categories;
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
}
