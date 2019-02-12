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
 * Zipcode Information
 */
public class ZipInfo
{
    private int mZipcode;
    private String mLatitude;
    private String mLongitude;
    private String mCity;
    private int mStateId;
    private int mCountyId;

    /**
     * Constructs an instance of zipcode information
     */
    public ZipInfo()
    {
    }

    /**
     * Zipcode value
     * @return zipcode value
     */
    @JacksonXmlProperty(localName = "zipCode")
    public int getZipcode()
    {
        return mZipcode;
    }

    public void setZipcode(int zipcode)
    {
        mZipcode = zipcode;
    }

    /**
     * Location latitude value in decimal degrees
     * @return latitude
     */
    @JacksonXmlProperty(localName = "lat")
    public String getLatitude()
    {
        return mLatitude;
    }

    public void setLatitude(String latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Location longitude value in decimal degrees
     * @return longitude
     */
    @JacksonXmlProperty(localName = "lon")
    public String getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(String longitude)
    {
        mLongitude = longitude;
    }

    /**
     * City or area name
     * @return city name
     */
    @JacksonXmlProperty(localName = "city")
    public String getCity()
    {
        return mCity;
    }

    public void setCity(String city)
    {
        mCity = city;
    }

    /**
     * State id
     *
     * @see State
     *
     * @return state identifier value
     */
    @JacksonXmlProperty(localName = "stid")
    public int getStateId()
    {
        return mStateId;
    }

    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * County id
     *
     * @see County
     *
     * @return county identifier value
     */
    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }
}
