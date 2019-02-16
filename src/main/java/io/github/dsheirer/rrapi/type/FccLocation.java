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

/**
 * Federal Communications Commission (FCC) (transmitter) location information.
 */
public class FccLocation
{
    private int mLocationId;
    private String mTowerId;
    private String mType;
    private double mAntennaHeight;
    private double mStructureHeight;
    private double mElevation;
    private String mAddress;
    private double mLatitude;
    private double mLongitude;
    private String mCity;
    private String mCounty;
    private String mState;

    /**
     * Constructs an instance
     */
    public FccLocation()
    {
    }

    /**
     * Location identifier
     * @return value
     */
    @JacksonXmlProperty(localName = "locationNumber")
    public int getLocationId()
    {
        return mLocationId;
    }

    /**
     * Sets the location identifier
     * @param locationId value
     */
    public void setLocationId(int locationId)
    {
        mLocationId = locationId;
    }

    /**
     * Tower identifier
     * @return tower id
     */
    @JacksonXmlProperty(localName = "towerId")
    public String getTowerId()
    {
        return mTowerId;
    }

    /**
     * Sets the tower id
     * @param towerId value
     */
    public void setTowerId(String towerId)
    {
        mTowerId = towerId;
    }

    /**
     * Transmitter type
     * @return type
     */
    @JacksonXmlProperty(localName = "type")
    public String getType()
    {
        return mType;
    }

    /**
     * Sets the transmitter type
     * @param type of transmitter
     */
    public void setType(String type)
    {
        mType = type;
    }

    /**
     * Antenna height
     * @return height
     */
    @JacksonXmlProperty(localName = "antennaHeight")
    public double getAntennaHeight()
    {
        return mAntennaHeight;
    }

    /**
     * Sets the antenna height
     * @param antennaHeight value
     */
    public void setAntennaHeight(double antennaHeight)
    {
        mAntennaHeight = antennaHeight;
    }

    /**
     * Height of the structure hosting the antenna
     * @return height
     */
    @JacksonXmlProperty(localName = "structureHeight")
    public double getStructureHeight()
    {
        return mStructureHeight;
    }

    /**
     * Sets the structure height
     * @param structureHeight value
     */
    public void setStructureHeight(double structureHeight)
    {
        mStructureHeight = structureHeight;
    }

    /**
     * Elevation of the terrain at the transmitter site.
     * @return elevation
     */
    @JacksonXmlProperty(localName = "elevation")
    public double getElevation()
    {
        return mElevation;
    }

    /**
     * Sets the elevation
     * @param elevation value
     */
    public void setElevation(double elevation)
    {
        mElevation = elevation;
    }

    /**
     * Address for the transmitter site
     * @return address
     */
    @JacksonXmlProperty(localName = "address")
    public String getAddress()
    {
        return mAddress;
    }

    /**
     * Sets the address
     * @param address value
     */
    public void setAddress(String address)
    {
        mAddress = address;
    }

    /**
     * Site latitude
     * @return value in decimal degrees
     */
    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    /**
     * Sets the site latitude
     * @param latitude value
     */
    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Site longitude
     * @return  latitude value in decimal degrees
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
     * City for the transmitter site
     * @return city
     */
    @JacksonXmlProperty(localName = "city")
    public String getCity()
    {
        return mCity;
    }

    /**
     * Sets the city for the transmitter type
     * @param city value
     */
    public void setCity(String city)
    {
        mCity = city;
    }

    /**
     * County for the transmitter type
     * @return county
     */
    @JacksonXmlProperty(localName = "county")
    public String getCounty()
    {
        return mCounty;
    }

    /**
     * Sets the county for the transmitter site
     * @param county name
     */
    public void setCounty(String county)
    {
        mCounty = county;
    }

    /**
     * State for the transmitter site
     * @return state
     */
    @JacksonXmlProperty(localName = "state")
    public String getState()
    {
        return mState;
    }

    /**
     * Sets the state
     * @param state value
     */
    public void setState(String state)
    {
        mState = state;
    }
}
