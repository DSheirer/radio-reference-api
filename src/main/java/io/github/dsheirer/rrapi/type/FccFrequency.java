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
 * Federal Communications Commission (FCC) frequency information.
 */
public class FccFrequency
{
    private int mLocationId;
    private double mFrequency;
    private String mEmission;
    private String mClassDescription;
    private double mPower;
    private int mMobiles;
    private int mPagers;

    /**
     * Constructs an instance
     */
    public FccFrequency()
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
     * Frequency
     * @return value in megahertz/megacycles
     */
    @JacksonXmlProperty(localName = "frequency")
    public double getFrequency()
    {
        return mFrequency;
    }

    /**
     * Sets the frequency
     * @param frequency in MHz
     */
    public void setFrequency(double frequency)
    {
        mFrequency = frequency;
    }

    /**
     * Emission identifier
     * @return value
     */
    @JacksonXmlProperty(localName = "emission")
    public String getEmission()
    {
        return mEmission;
    }

    /**
     * Sets the emission
     * @param emission value
     */
    public void setEmission(String emission)
    {
        mEmission = emission;
    }

    /**
     * Frequency class description
     * @return description
     */
    @JacksonXmlProperty(localName = "class")
    public String getClassDescription()
    {
        return mClassDescription;
    }

    /**
     * Sets the frequency class description
     * @param classDescription value
     */
    public void setClassDescription(String classDescription)
    {
        mClassDescription = classDescription;
    }

    /**
     * Maximum authorized output power
     * @return power in decibels
     */
    @JacksonXmlProperty(localName = "power")
    public double getPower()
    {
        return mPower;
    }

    /**
     * Sets the maximum output power
     * @param power in decibels
     */
    public void setPower(double power)
    {
        mPower = power;
    }

    /**
     * Number of mobile radio units authorized for the frequency or channel.
     * @return mobiles authorized
     */
    @JacksonXmlProperty(localName = "mobiles")
    public int getMobiles()
    {
        return mMobiles;
    }

    /**
     * Sets the number of mobiles authorized
     * @param mobiles count
     */
    public void setMobiles(int mobiles)
    {
        mMobiles = mobiles;
    }

    /**
     * Number of pagers authorized for the channel.
     * @return pager count
     */
    @JacksonXmlProperty(localName = "pagers")
    public int getPagers()
    {
        return mPagers;
    }

    /**
     * Sets the number of pagers authorized for the channel
     * @param pagers count
     */
    public void setPagers(int pagers)
    {
        mPagers = pagers;
    }
}
