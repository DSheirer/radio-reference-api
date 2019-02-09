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

public class SubCategory
{
    private int mSubCategoryId;
    private String mName;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private List<SystemIdentifier> mSystemIdentifiers = new ArrayList<>();

    public SubCategory()
    {
    }

    @JacksonXmlProperty(localName = "scid")
    public int getSubCategoryId()
    {
        return mSubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId)
    {
        mSubCategoryId = subCategoryId;
    }

    @JacksonXmlProperty(localName = "scName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
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

    @JacksonXmlProperty(localName = "sids")
    public List<SystemIdentifier> getSystemIdentifiers()
    {
        return mSystemIdentifiers;
    }

    public void setSystemIdentifiers(List<SystemIdentifier> systemIdentifiers)
    {
        mSystemIdentifiers = systemIdentifiers;
    }
}
