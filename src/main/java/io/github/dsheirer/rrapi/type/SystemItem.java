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

import java.util.Date;

public class SystemItem
{
    private int mSystemId;
    private String mName;
    private int mType;
    private int mFlavor;
    private int mVoice;
    private String mCity;
    private Date mLastUpdated;

    public SystemItem()
    {
    }

    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
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
}
