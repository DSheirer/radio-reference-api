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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Frequency subcategory of a {@link Category}, e.g. a group of channels, with its location and coverage and the trunked
 * systems it refers to.  Its frequencies come from {@link
 * io.github.dsheirer.rrapi.RadioReferenceService#getSubCategoryFrequencies(int)}.
 */
public class SubCategory
{
    private int mSubCategoryId;
    private String mName;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private List<Rectangle> mRectangles = new ArrayList<>();
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
     * Latitude of the center of the coverage area, in decimal degrees.  0 together with a 0 longitude means
     * that the location isn't set.
     * @return latitude
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
     * Longitude of the center of the coverage area, in decimal degrees.  0 together with a 0 latitude means
     * that the location isn't set.
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
     * Radius of the coverage area around the center, in miles, or 0 if not set.  See {@link #getCoverage()}.
     * @return range in miles
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
     * Rectangles that outline the coverage area more closely than the range, or an empty list.  See
     * {@link #getCoverage()}.
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

    /**
     * Coverage area of the frequency subcategory, for distance and containment checks
     * @return coverage area
     */
    @JsonIgnore
    public Coverage getCoverage()
    {
        return new Coverage(getLatitude(), getLongitude(), getRange(), getRectangles());
    }
}
