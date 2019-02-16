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

/**
 * Talkgroup request filter to use in querying talkgroups with optional filtering by category, tag, or decimal value
 */
public class TalkgroupRequestFilter
{
    private int mSystemId;
    private Integer mCategoryId;
    private Integer mTagId;
    private Integer mDecimalValue;

    /**
     * Constructs an instance of a talkgroup request filter for the specified system.  Use the category, decimal value
     * and/or tag methods to set additional filter options.
     *
     * @param systemId to query
     */
    public TalkgroupRequestFilter(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * System identifier
     *
     * @return system id
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system identifier
     *
     * @param systemId to use
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Category identifier filter value
     *
     * @return category id
     */
    public Integer getCategoryId()
    {
        return mCategoryId;
    }

    /**
     * Indicates if this request has a category filter specified
     *
     * @return true if available
     */
    @JsonIgnore
    public boolean hasCategoryFilter()
    {
        return mCategoryId != null;
    }

    /**
     * Sets the category identifier filter value
     *
     * @param categoryId value
     */
    public void setCategoryId(Integer categoryId)
    {
        mCategoryId = categoryId;
    }

    /**
     * Tag identifier filter value
     *
     * @return tag identifier
     */
    public Integer getTagId()
    {
        return mTagId;
    }

    /**
     * Indicates if this request has a tag filter specified
     *
     * @return true if available
     */
    @JsonIgnore
    public boolean hasTagFilter()
    {
        return mTagId != null;
    }

    /**
     * Sets the tag identifier filter value
     *
     * @param tagId to filter
     */
    public void setTagId(Integer tagId)
    {
        mTagId = tagId;
    }

    /**
     * Decimal value filter identifier
     *
     * @return decimal value
     */
    public Integer getDecimalValue()
    {
        return mDecimalValue;
    }

    /**
     * Indicates if this request has a decimal value filter specified
     *
     * @return true if available
     */
    @JsonIgnore
    public boolean hasDecimalValueFilter()
    {
        return mDecimalValue != null;
    }

    /**
     * Sets the decimal value filter
     *
     * @param decimalValue value
     */
    public void setDecimalValue(Integer decimalValue)
    {
        mDecimalValue = decimalValue;
    }

    /**
     * Creates a talkgroup filter for the specified category identifier
     *
     * @param systemId value
     * @param categoryId filter value
     * @return filter
     */
    public static TalkgroupRequestFilter createCategoryFilter(int systemId, int categoryId)
    {
        TalkgroupRequestFilter filter = new TalkgroupRequestFilter(systemId);
        filter.setCategoryId(categoryId);
        return filter;
    }

    /**
     * Creates a talkgroup filter for the specified tag identifier
     *
     * @param systemId value
     * @param tagId filter value
     * @return filter
     */
    public static TalkgroupRequestFilter createTagFilter(int systemId, int tagId)
    {
        TalkgroupRequestFilter filter = new TalkgroupRequestFilter(systemId);
        filter.setTagId(tagId);
        return filter;
    }

    /**
     * Creates a talkgroup filter for the specified talkgroup decimal value
     *
     * @param systemId value
     * @param decimalValue filter value
     * @return filter
     */
    public static TalkgroupRequestFilter createDecimalValueFilter(int systemId, int decimalValue)
    {
        TalkgroupRequestFilter filter = new TalkgroupRequestFilter(systemId);
        filter.setDecimalValue(decimalValue);
        return filter;
    }
}
