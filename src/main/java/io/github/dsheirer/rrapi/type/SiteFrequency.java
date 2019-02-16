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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Site frequency or repeater output frequency
 */
public class SiteFrequency
{
    private int mLogicalChannelNumber;
    private double mFrequency;
    private String mUse;
    private String mColorCode;
    private String mChannelId;

    /**
     * Constructs an instance
     */
    public SiteFrequency()
    {
    }

    /**
     * Logical channel number (LCN)
     * @return lcn
     */
    @JacksonXmlProperty(localName = "lcn")
    public int getLogicalChannelNumber()
    {
        return mLogicalChannelNumber;
    }

    /**
     * Sets the LCN
     * @param logicalChannelNumber value
     */
    public void setLogicalChannelNumber(int logicalChannelNumber)
    {
        mLogicalChannelNumber = logicalChannelNumber;
    }

    /**
     * Frequency
     * @return frequency in MHz
     */
    @JacksonXmlProperty(localName = "freq")
    public double getFrequency()
    {
        return mFrequency;
    }

    /**
     * Sets the frequency
     * @param frequency value
     */
    public void setFrequency(double frequency)
    {
        mFrequency = frequency;
    }

    /**
     * Indicates the use for the channel.
     * @return 'd' for primary control channel, 'a' for alternate control channel, or null.
     */
    @JacksonXmlProperty(localName = "use")
    public String getUse()
    {
        return mUse;
    }

    /**
     * Indicates if this is a primary control channel
     * @return true if this is a primary control channel
     */
    @JsonIgnore
    public boolean isPrimaryControlChannel()
    {
        return mUse != null && mUse.equals("d");
    }

    /**
     * Indicates if this is an alternate control channel
     * @return true if this is an alternate control channel
     */
    @JsonIgnore
    public boolean isAlternateControlChannel()
    {
        return mUse != null && mUse.equals("a");
    }

    /**
     * Sets the use.
     * @param use value
     */
    public void setUse(String use)
    {
        mUse = use;
    }

    /**
     * Color code
     * @return color code or null
     */
    @JacksonXmlProperty(localName = "colorCode")
    public String getColorCode()
    {
        return mColorCode;
    }

    /**
     * Sets the color code
     * @param colorCode value
     */
    public void setColorCode(String colorCode)
    {
        mColorCode = colorCode;
    }

    /**
     * Channel ID
     * @return id or null
     */
    @JacksonXmlProperty(localName = "ch_id")
    public String getChannelId()
    {
        return mChannelId;
    }

    /**
     * Sets the channel id
     * @param channelId value
     */
    public void setChannelId(String channelId)
    {
        mChannelId = channelId;
    }
}
