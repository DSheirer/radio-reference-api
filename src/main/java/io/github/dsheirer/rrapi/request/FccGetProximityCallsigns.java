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

package io.github.dsheirer.rrapi.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Get FCC callsigns in proximity to a reference point and distance
 *
 * Note: the web service interface supports range units of measure: m=miles, k=kilometers and n=nautical miles.  This
 * class uses kilometers by default.
 *
 * The ICD requests that a max range of 3 miles (4.82 km) be used.  The constructor enforces this value.
 */
public class FccGetProximityCallsigns extends RequestBody
{
    private static final double MAX_RANGE_KILOMETERS = 4.82803;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private String mUnit = "k";

    /**
     * Constructs the request
     *
     * @param authorizationInformation for the user
     * @param latitude for the search location
     * @param longitude for the search location
     * @param range for the search distance in kilometers (0 - 4.82803 km)
     */
    public FccGetProximityCallsigns(AuthorizationInformation authorizationInformation, double latitude, double longitude, double range)
    {
        super(authorizationInformation);
        mLatitude = latitude;
        mLongitude = longitude;
        mRange = range;

        if(mRange > MAX_RANGE_KILOMETERS)
        {
            mRange = MAX_RANGE_KILOMETERS;
        }
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

    @JacksonXmlProperty(localName = "unit")
    public String getUnit()
    {
        return mUnit;
    }

    public void setUnit(String unit)
    {
        mUnit = unit;
    }

    /**
     * Creates a request envelope using the auth info and the radio service code
     * @param authorizationInformation credentials
     * @param latitude of search location
     * @param longitude of search location
     * @param range from search location
     * @return request envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, double latitude,
                                         double longitude, double range)
    {
        return RequestBody.create(new FccGetProximityCallsigns(authorizationInformation, latitude, longitude, range));
    }
}
