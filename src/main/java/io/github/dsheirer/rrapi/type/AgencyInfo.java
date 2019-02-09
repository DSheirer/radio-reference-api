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

public class AgencyInfo
{
    private int mAgencyId;
    private String mName;
    private String mType;
    private int mCountyId;
    private int mStateId;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private Date mLastUpdated;
    private List<Category> mCategories = new ArrayList<>();

    public AgencyInfo()
    {
    }

    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    @JacksonXmlProperty(localName = "agencyName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "agencyType")
    public String getType()
    {
        return mType;
    }

    public void setType(String type)
    {
        mType = type;
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

    @JacksonXmlProperty(localName = "lastUpdated")
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
}
