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
 * Proximity callsign search result
 */
public class ProximityCallsignResult
{
    private String mCallsign;
    private String mLicensee;
    private double mLatitude;
    private double mLongitude;
    private double mDistance;

    /**
     * Constructs an instance
     */
    public ProximityCallsignResult()
    {
    }

    /**
     * Callsign for the emitter
     * @return callsign
     */
    @JacksonXmlProperty(localName = "callsign")
    public String getCallsign()
    {
        return mCallsign;
    }

    public void setCallsign(String callsign)
    {
        mCallsign = callsign;
    }

    /**
     * Licensee for the emitter
     * @return licensee
     */
    @JacksonXmlProperty(localName = "licensee")
    public String getLicensee()
    {
        return mLicensee;
    }

    public void setLicensee(String licensee)
    {
        mLicensee = licensee;
    }

    /**
     * Latitude of the emitter
     * @return latitude
     */
    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Longitude of the emitter
     * @return longitude
     */
    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Distance from search location
     * @return distance
     */
    @JacksonXmlProperty(localName = "distance")
    public double getDistance()
    {
        return mDistance;
    }

    public void setDistance(double distance)
    {
        mDistance = distance;
    }
}
