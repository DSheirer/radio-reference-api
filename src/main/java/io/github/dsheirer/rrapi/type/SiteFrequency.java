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

public class SiteFrequency
{
    private int mLogicalChannelNumber;
    private double mFrequency;
    private String mUse;
    private String mColorCode;
    private String mChannelId;

    public SiteFrequency()
    {
    }

    @JacksonXmlProperty(localName = "lcn")
    public int getLogicalChannelNumber()
    {
        return mLogicalChannelNumber;
    }

    public void setLogicalChannelNumber(int logicalChannelNumber)
    {
        mLogicalChannelNumber = logicalChannelNumber;
    }

    @JacksonXmlProperty(localName = "freq")
    public double getFrequency()
    {
        return mFrequency;
    }

    public void setFrequency(double frequency)
    {
        mFrequency = frequency;
    }

    @JacksonXmlProperty(localName = "use")
    public String getUse()
    {
        return mUse;
    }

    public void setUse(String use)
    {
        mUse = use;
    }

    @JacksonXmlProperty(localName = "colorCode")
    public String getColorCode()
    {
        return mColorCode;
    }

    public void setColorCode(String colorCode)
    {
        mColorCode = colorCode;
    }

    @JacksonXmlProperty(localName = "ch_id")
    public String getChannelId()
    {
        return mChannelId;
    }

    public void setChannelId(String channelId)
    {
        mChannelId = channelId;
    }
}
