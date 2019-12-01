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

import java.util.Date;
import java.util.Objects;

/**
 * Radio system basic details
 */
public class System implements Comparable<System>
{
    private int mSystemId;
    private String mName;
    private int mTypeId;
    private int mFlavorId;
    private int mVoiceId;
    private String mCity;
    private Date mLastUpdated;

    /**
     * Constructs an instance
     */
    public System()
    {
    }

    /**
     * System identifier
     * @return identifier
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the radio system identifier
     * @param systemId value
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Name
     * @return name
     */
    @JacksonXmlProperty(localName = "sName")
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
     * Protocol type identifier
     *
     * @see Type Type
     * @return type id
     */
    @JacksonXmlProperty(localName = "sType")
    public int getTypeId()
    {
        return mTypeId;
    }

    /**
     * Sets the protocol type identifier
     * @param typeId value
     */
    public void setTypeId(int typeId)
    {
        mTypeId = typeId;
    }

    /**
     * Protocol flavor or variant identifier
     *
     * @see Flavor Flavor
     * @return flavor id
     */
    @JacksonXmlProperty(localName = "sFlavor")
    public int getFlavorId()
    {
        return mFlavorId;
    }

    /**
     * Sets the flavor identifier
     * @param flavorId value
     */
    public void setFlavorId(int flavorId)
    {
        mFlavorId = flavorId;
    }

    /**
     * Protocol voice codec
     *
     * @see Voice Voice
     * @return voice identifier
     */
    @JacksonXmlProperty(localName = "sVoice")
    public int getVoiceId()
    {
        return mVoiceId;
    }

    /**
     * Sets the voice identifier
     * @param voiceId value
     */
    public void setVoiceId(int voiceId)
    {
        mVoiceId = voiceId;
    }

    /**
     * City name
     * @return city
     */
    @JacksonXmlProperty(localName = "sCity")
    public String getCity()
    {
        return mCity;
    }

    /**
     * Sets the city name
     * @param city name
     */
    public void setCity(String city)
    {
        mCity = city;
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
     * Sets the last updated value
     * @param lastUpdated date
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    @Override
    public int compareTo(System o)
    {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        System system = (System)o;
        return getSystemId() == system.getSystemId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getSystemId());
    }
}
