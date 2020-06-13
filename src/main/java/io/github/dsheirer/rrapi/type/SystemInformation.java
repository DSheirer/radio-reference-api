/*
 * *****************************************************************************
 * Copyright (C) 2020 Dennis Sheirer
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
 * Radio system extended information and details
 */
public class SystemInformation
{
    private String mName;
    private int mTypeId;
    private int mFlavorId;
    private int mVoiceId;
    private String mCity;
    private List<County> mCounties = new ArrayList<>();
    private List<State> mStates = new ArrayList<>();
    private String mCountry;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private Date mLastUpdated;
    private List<RadioNetwork> mRadioNetworks = new ArrayList<>();
    private List<Bandplan> mBandplans;
    private Fleetmap mFleetmap;

    /**
     * Constructs an instance
     */
    public SystemInformation()
    {
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
     * @see Type Type for protocol types
     * @return id
     */
    @JacksonXmlProperty(localName = "sType")
    public int getTypeId()
    {
        return mTypeId;
    }

    /**
     * Sets the protocol type id
     * @param typeId value
     */
    public void setTypeId(int typeId)
    {
        mTypeId = typeId;
    }

    /**
     * Protocol flavor identifier
     *
     * @see Flavor Flavor for protocol flavor or variant
     * @return id
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
     * Protocol voice codec identifier
     *
     * @see Voice Voice for codec
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
     * City
     * @return city
     */
    @JacksonXmlProperty(localName = "sCity")
    public String getCity()
    {
        return mCity;
    }

    /**
     * Sets the city
     * @param city value
     */
    public void setCity(String city)
    {
        mCity = city;
    }

    /**
     * List of counties that contain the radio system
     * @return county listing
     */
    @JacksonXmlProperty(localName = "sCounty")
    public List<County> getCounties()
    {
        return mCounties;
    }

    /**
     * Sets the counties
     * @param counties list
     */
    public void setCounties(List<County> counties)
    {
        if(counties != null)
        {
            mCounties = counties;
        }
    }

    /**
     * List of states that contain the radio system
     * @return state listing
     */
    @JacksonXmlProperty(localName = "sState")
    public List<State> getStates()
    {
        return mStates;
    }

    /**
     * Sets the state list
     * @param states list
     */
    public void setStates(List<State> states)
    {
        if(states != null)
        {
            mStates = states;
        }
    }

    /**
     * Country
     * @return country
     */
    @JacksonXmlProperty(localName = "sCountry")
    public String getCountry()
    {
        return mCountry;
    }

    /**
     * Sets the country
     * @param country value
     */
    public void setCountry(String country)
    {
        mCountry = country;
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
     * @param longitude value
     */
    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Range (extent) of the system from the center location
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
     * @param lastUpdated date
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }

    /**
     * List of radio system identifiers
     * @return system id listing
     */
    @JacksonXmlProperty(localName = "sysid")
    public List<RadioNetwork> getRadioNetworks()
    {
        return mRadioNetworks;
    }

    /**
     * Sets the radio system ids
     * @param radioNetworks list
     */
    public void setRadioNetworks(List<RadioNetwork> radioNetworks)
    {
        if(radioNetworks != null)
        {
            mRadioNetworks = radioNetworks;
        }
    }

    /**
     * Bandplans
     * @return bandplans
     */
    @JacksonXmlProperty(localName = "bandplan")
    public List<Bandplan> getBandplans()
    {
        return mBandplans;
    }

    /**
     * Sets the bandplans
     * @param bandplans value
     */
    public void setBandplans(List<Bandplan> bandplans)
    {
        mBandplans = bandplans;
    }

    /**
     * Fleet map
     * @return map
     */
    @JacksonXmlProperty(localName = "fleetmap")
    public Fleetmap getFleetmap()
    {
        return mFleetmap;
    }

    /**
     * Sets the fleet map
     * @param fleetmap value
     */
    public void setFleetmap(Fleetmap fleetmap)
    {
        mFleetmap = fleetmap;
    }
}
