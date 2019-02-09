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

public class FccFrequency
{
    private int mLocationId;
    private double mFrequency;
    private String mEmission;
    private String mClassDescription;
    private double mPower;
    private int mMobiles;
    private int mPagers;

    public FccFrequency()
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

    @JacksonXmlProperty(localName = "frequency")
    public double getFrequency()
    {
        return mFrequency;
    }

    public void setFrequency(double frequency)
    {
        mFrequency = frequency;
    }

    @JacksonXmlProperty(localName = "emission")
    public String getEmission()
    {
        return mEmission;
    }

    public void setEmission(String emission)
    {
        mEmission = emission;
    }

    @JacksonXmlProperty(localName = "class")
    public String getClassDescription()
    {
        return mClassDescription;
    }

    public void setClassDescription(String classDescription)
    {
        mClassDescription = classDescription;
    }

    @JacksonXmlProperty(localName = "power")
    public double getPower()
    {
        return mPower;
    }

    public void setPower(double power)
    {
        mPower = power;
    }

    @JacksonXmlProperty(localName = "mobiles")
    public int getMobiles()
    {
        return mMobiles;
    }

    public void setMobiles(int mobiles)
    {
        mMobiles = mobiles;
    }

    @JacksonXmlProperty(localName = "pagers")
    public int getPagers()
    {
        return mPagers;
    }

    public void setPagers(int pagers)
    {
        mPagers = pagers;
    }
}
