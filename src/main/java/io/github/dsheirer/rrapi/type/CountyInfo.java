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

/**
 * County information
 */
public class CountyInfo
{
    private int mCountyId;
    private String mName;
    private String mHeader;
    private int mStateId;
    private String mFips;
    private Date mLastUpdated;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private List<Rectangle> mRectangles = new ArrayList<>();
    private List<Category> mCategories = new ArrayList<>();
    private List<System> mSystems = new ArrayList<>();
    private List<Agency> mAgencies = new ArrayList<>();

    /**
     * Constructs an instance
     */
    public CountyInfo()
    {
    }

    /**
     * County ID
     * @return county id
     */
    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    /**
     * Sets the county id
     * @param countyId value
     */
    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }

    /**
     * Name
     * @return name
     */
    @JacksonXmlProperty(localName = "countyName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the county name.
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * County header, indicating the geo-political classifier that the object represents.
     * @return header value
     */
    @JacksonXmlProperty(localName = "countyHeader")
    public String getHeader()
    {
        return mHeader;
    }

    /**
     * Sets the county header.
     * @param header value
     */
    public void setHeader(String header)
    {
        mHeader = header;
    }

    /**
     * State ID for the county
     * @return state ID
     */
    @JacksonXmlProperty(localName = "stid")
    public int getStateId()
    {
        return mStateId;
    }

    /**
     * Sets the state id
     * @param stateId value
     */
    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * Location latitude
     * @return latitude in decimal degrees
     */
    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    /**
     * Sets the latitude
     * @param latitude value
     */
    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Location longitude
     * @return longitude in decimal degrees
     */
    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    /**
     * Sets the longitude
     * @param longitude value
     */
    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Range of the county
     * @return range
     */
    @JacksonXmlProperty(localName = "range")
    public double getRange()
    {
        return mRange;
    }

    /**
     * Sets the range
     * @param range value
     */
    public void setRange(double range)
    {
        mRange = range;
    }

    /**
     * Rectangle geo-bounds.
     * @return rectangles
     */
    @JacksonXmlProperty(localName = "rectangles")
    public List<Rectangle> getRectangles()
    {
        return mRectangles;
    }

    /**
     * Sets the rectangle geo-bounds
     * @param rectangles values.
     */
    public void setRectangles(List<Rectangle> rectangles)
    {
        mRectangles = rectangles;
    }

    /**
     * FIPS county code
     * @return fips code
     */
    @JacksonXmlProperty(localName = "fips")
    public String getFips()
    {
        return mFips;
    }

    /**
     * Sets the FIPS county code
     * @param fips code
     */
    public void setFips(String fips)
    {
        mFips = fips;
    }

    /**
     * Last Updated
     * @return last updated date
     */
    @JacksonXmlProperty(localName = "lastUpdated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    /**
     * Sets the last updated date
     * @param lastUpdated date
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    /**
     * Categories list
     * @return list
     */
    @JacksonXmlProperty(localName = "cats")
    public List<Category> getCategories()
    {
        return mCategories;
    }

    /**
     * Sets the list of categories
     * @param categories list
     */
    public void setCategories(List<Category> categories)
    {
        if(categories != null)
        {
            mCategories = categories;
        }
    }

    /**
     * Radio systems list
     * @return list
     */
    @JacksonXmlProperty(localName = "trsList")
    public List<System> getSystems()
    {
        return mSystems;
    }

    /**
     * Sets the list of radio system items
     * @param systems items
     */
    public void setSystems(List<System> systems)
    {
        if(systems != null)
        {
            mSystems = systems;
        }
    }

    /**
     * Agencies list
     * @return list
     */
    @JacksonXmlProperty(localName = "agencyList")
    public List<Agency> getAgencies()
    {
        return mAgencies;
    }

    /**
     * Sets the list of agencies
     * @param agencies list
     */
    public void setAgencies(List<Agency> agencies)
    {
        if(agencies != null)
        {
            mAgencies = agencies;
        }
    }
}
