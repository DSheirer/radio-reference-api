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
 * Sub Category
 */
public class SubCategory
{
    private int mSubCategoryId;
    private String mName;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private List<SystemReference> mSystemReferences = new ArrayList<>();

    /**
     * Constructs an instance
     */
    public SubCategory()
    {
    }

    /**
     * Sub Category identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "scid")
    public int getSubCategoryId()
    {
        return mSubCategoryId;
    }

    /**
     * Sets the sub category id
     * @param subCategoryId value
     */
    public void setSubCategoryId(int subCategoryId)
    {
        mSubCategoryId = subCategoryId;
    }

    /**
     * Name
     * @return name
     */
    @JacksonXmlProperty(localName = "scName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the name
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Center location latitude
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
     * Center location longitude
     * @return longitude
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
     * Range or extent from the center location
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
     * List of system identifier references.  These can be used to query the systems that use this sub category.
     * @return list
     */
    @JacksonXmlProperty(localName = "sids")
    public List<SystemReference> getSystemReferences()
    {
        return mSystemReferences;
    }

    /**
     * Sets the system references
     * @param systemReferences values
     */
    public void setSystemReferences(List<SystemReference> systemReferences)
    {
        if(systemReferences != null)
        {
            mSystemReferences = systemReferences;
        }
    }
}
