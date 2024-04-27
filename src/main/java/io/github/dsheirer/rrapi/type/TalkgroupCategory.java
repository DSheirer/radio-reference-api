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
 * Talkgroup category or grouping
 */
public class TalkgroupCategory
{
    private int mTalkgroupCategoryId;
    private int mSystemId;
    private String mName;
    private int mSort;
    private int mSortBy;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private List<Rectangle> mRectangles = new ArrayList<>();
    private Date mLastUpdated;

    /**
     * Constructs an instance
     */
    public TalkgroupCategory()
    {
    }

    /**
     * Talkgroup category identfier
     * @return id
     */
    @JacksonXmlProperty(localName = "tgCid")
    public int getTalkgroupCategoryId()
    {
        return mTalkgroupCategoryId;
    }

    /**
     * Sets the talkgroup category identifier
     * @param talkgroupCategoryId value
     */
    public void setTalkgroupCategoryId(int talkgroupCategoryId)
    {
        mTalkgroupCategoryId = talkgroupCategoryId;
    }

    /**
     * System identifier
     * @return system id
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system identifier
     * @param systemId value
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Name or label for the category
     * @return name
     */
    @JacksonXmlProperty(localName = "tgCname")
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
     * Sort order for display
     * @return sort order
     */
    @JacksonXmlProperty(localName = "tgSort")
    public int getSort()
    {
        return mSort;
    }

    /**
     * Sets the sort order
     * @param sort order
     */
    public void setSort(int sort)
    {
        mSort = sort;
    }

    /**
     * Secondary sort ordering
     * @return sort by
     */
    @JacksonXmlProperty(localName = "tgSortBy")
    public int getSortBy()
    {
        return mSortBy;
    }

    /**
     * Sets the sort by
     * @param sortBy value
     */
    public void setSortBy(int sortBy)
    {
        mSortBy = sortBy;
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
     * @return longitude in decimal degrees
     */
    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    /**
     * Sets the longitude
     * @param longitude value in decimal degrees
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
     * Last updated date
     * @return last updated
     */
    @JacksonXmlProperty(localName = "lastUpdated")
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
}
