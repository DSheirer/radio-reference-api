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

    public FccLocation()
    {
    }

    @JacksonXmlProperty(localName = "locationNumber")
    public int getLocationId()
    {
        return mLocationId;
    }

    public void setLocationId(int locationId)
    {
        mLocationId = locationId;
    }

    @JacksonXmlProperty(localName = "towerId")
    public String getTowerId()
    {
        return mTowerId;
    }

    public void setTowerId(String towerId)
    {
        mTowerId = towerId;
    }

    @JacksonXmlProperty(localName = "type")
    public String getType()
    {
        return mType;
    }

    public void setType(String type)
    {
        mType = type;
    }

    @JacksonXmlProperty(localName = "antennaHeight")
    public double getAntennaHeight()
    {
        return mAntennaHeight;
    }

    public void setAntennaHeight(double antennaHeight)
    {
        mAntennaHeight = antennaHeight;
    }

    @JacksonXmlProperty(localName = "structureHeight")
    public double getStructureHeight()
    {
        return mStructureHeight;
    }

    public void setStructureHeight(double structureHeight)
    {
        mStructureHeight = structureHeight;
    }

    @JacksonXmlProperty(localName = "elevation")
    public double getElevation()
    {
        return mElevation;
    }

    public void setElevation(double elevation)
    {
        mElevation = elevation;
    }

    @JacksonXmlProperty(localName = "address")
    public String getAddress()
    {
        return mAddress;
    }

    public void setAddress(String address)
    {
        mAddress = address;
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

    @JacksonXmlProperty(localName = "city")
    public String getCity()
    {
        return mCity;
    }

    public void setCity(String city)
    {
        mCity = city;
    }

    @JacksonXmlProperty(localName = "county")
    public String getCounty()
    {
        return mCounty;
    }

    public void setCounty(String county)
    {
        mCounty = county;
    }

    @JacksonXmlProperty(localName = "state")
    public String getState()
    {
        return mState;
    }

    public void setState(String state)
    {
        mState = state;
    }
}
