/*
 * *****************************************************************************
 * Copyright (C) 2024 Dennis Sheirer
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
 * Rectangle in decimal degrees, from its north west corner to its south east corner.  Rectangles outline the coverage
 * area of counties, agencies, subcategories, trunked systems, sites and talkgroup categories: see {@link Coverage}.
 */
public class Rectangle
{
    private double mNorthWestLatitude;
    private double mNorthWestLongitude;
    private double mSouthEastLatitude;
    private double mSouthEastLongitude;

    /**
     * Constructs an instance
     */
    public Rectangle()
    {
    }

    /**
     * NW latitude
     * @return value in degrees
     */
    @JacksonXmlProperty(localName = "nw_lat")
    public double getNorthWestLatitude()
    {
        return mNorthWestLatitude;
    }

    /**
     * Sets the NW latitude
     * @param degrees value
     */
    public void setNorthWestLatitude(double degrees)
    {
        mNorthWestLatitude = degrees;
    }

    /**
     * NW longitude
     * @return value in degrees
     */
    @JacksonXmlProperty(localName = "nw_lon")
    public double getNorthWestLongitude()
    {
        return mNorthWestLongitude;
    }

    /**
     * Sets the NW longitude
     * @param degrees value
     */
    public void setNorthWestLongitude(double degrees)
    {
        mNorthWestLongitude = degrees;
    }

    /**
     * SE latitude
     * @return value in degrees
     */
    @JacksonXmlProperty(localName = "se_lat")
    public double getSouthEastLatitude()
    {
        return mSouthEastLatitude;
    }

    /**
     * Sets the SE latitude
     * @param degrees value
     */
    public void setSouthEastLatitude(double degrees)
    {
        mSouthEastLatitude = degrees;
    }

    /**
     * SE longitude
     * @return value in degrees
     */
    @JacksonXmlProperty(localName = "se_lon")
    public double getSouthEastLongitude()
    {
        return mSouthEastLongitude;
    }

    /**
     * Sets the SE longitude
     * @param degrees value
     */
    public void setSouthEastLongitude(double degrees)
    {
        mSouthEastLongitude = degrees;
    }

    /**
     * Indicates if the location is inside the rectangle, including its edges.  Handles rectangles that cross the
     * 180 degree meridian.
     * @param latitude in decimal degrees
     * @param longitude in decimal degrees
     * @return true if the location is inside the rectangle
     */
    public boolean contains(double latitude, double longitude)
    {
        if(latitude > mNorthWestLatitude || latitude < mSouthEastLatitude)
        {
            return false;
        }

        if(mNorthWestLongitude <= mSouthEastLongitude)
        {
            return longitude >= mNorthWestLongitude && longitude <= mSouthEastLongitude;
        }

        //Crosses the 180 degree meridian
        return longitude >= mNorthWestLongitude || longitude <= mSouthEastLongitude;
    }
}
