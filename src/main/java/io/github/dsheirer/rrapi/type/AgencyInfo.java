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

/**
 * Agency information including frequency categories, subcategories and radio systems.
 */
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

    /**
     * Constructs an instance
     */
    public AgencyInfo()
    {
    }

    /**
     * Agency ID
     * @return id
     */
    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    /**
     * Sets the agency ID
     * @param agencyId to set
     */
    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    /**
     * Agency name
     * @return name
     */
    @JacksonXmlProperty(localName = "agencyName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the name
     * @param name to set
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Type of agency
     * @return type
     */
    @JacksonXmlProperty(localName = "agencyType")
    public String getType()
    {
        return mType;
    }

    /**
     * Sets the type
     * @param type to set
     */
    public void setType(String type)
    {
        mType = type;
    }

    /**
     * County ID
     * @return id
     */
    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    /**
     * Sets the county ID
     * @param countyId to set
     */
    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }

    /**
     * State ID
     * @return id
     */
    @JacksonXmlProperty(localName = "stid")
    public int getStateId()
    {
        return mStateId;
    }

    /**
     * Sets the state ID
     * @param stateId to set
     */
    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * Agency location - latitude
     * @return latitude
     */
    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    /**
     * Sets the latitude
     * @param latitude to set
     */
    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Agency location - longitude
     * @return longitude
     */
    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    /**
     * Sets the longitude
     * @param longitude to set
     */
    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Agency location - range (extent)
     * @return range
     */
    @JacksonXmlProperty(localName = "range")
    public double getRange()
    {
        return mRange;
    }

    /**
     * Sets the range
     * @param range to set
     */
    public void setRange(double range)
    {
        mRange = range;
    }

    /**
     * Last updated date
     * @return date
     */
    @JacksonXmlProperty(localName = "lastUpdated")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    /**
     * Sets the last updated date
     * @param lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    /**
     * Frequency categories
     * @return categorites
     */
    @JacksonXmlProperty(localName = "cats")
    public List<Category> getCategories()
    {
        return mCategories;
    }

    /**
     * Sets the categories
     * @param categories to set
     */
    public void setCategories(List<Category> categories)
    {
        mCategories = categories;
    }
}
