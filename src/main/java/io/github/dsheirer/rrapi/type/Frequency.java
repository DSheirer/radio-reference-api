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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Frequency or channel details
 */
public class Frequency
{
    private int mFrequencyId;
    private double mDownlink;
    private double mUplink;
    private String mCallsign;
    private String mDescription;
    private String mAlphaTag;
    private String mTone;
    private String mColorCode;
    private String mTalkgroup;
    private String mSlot;
    private String mMode;
    private int mEncryption;
    private String mClassification;
    private List<Tag> mTags = new ArrayList<>();
    private int mSubCategoryId;
    private int mSort;
    private Date mLastUpdated;

    /**
     * Constructs an instance
     */
    public Frequency()
    {
    }

    /**
     * Frequency identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "fid")
    public int getFrequencyId()
    {
        return mFrequencyId;
    }

    /**
     * Sets the frequency identifier
     * @param frequencyId value
     */
    public void setFrequencyId(int frequencyId)
    {
        mFrequencyId = frequencyId;
    }

    /**
     * Downlink (tower to mobile) or output frequency of the repeater channel
     * @return frequency in megahertz (MHz)
     */
    @JacksonXmlProperty(localName = "out")
    public double getDownlink()
    {
        return mDownlink;
    }

    /**
     * Sets the downlink frequency
     * @param downlink frequency in megahertz
     */
    public void setDownlink(double downlink)
    {
        mDownlink = downlink;
    }

    /**
     * Uplink (mobile to tower) or input frequency of the repeater channel
     * @return frequency in megahertz (MHz)
     */
    @JacksonXmlProperty(localName = "in")
    public double getUplink()
    {
        return mUplink;
    }

    /**
     * Sets the uplink frequency
     * @param uplink frequency in megahertz
     */
    public void setUplink(double uplink)
    {
        mUplink = uplink;
    }

    /**
     * FCC callsign for the frequency channel
     * @return callsign
     */
    @JacksonXmlProperty(localName = "callsign")
    public String getCallsign()
    {
        return mCallsign;
    }

    /**
     * Sets the callsign
     * @param callsign value
     */
    public void setCallsign(String callsign)
    {
        mCallsign = callsign;
    }

    /**
     * Description
     * @return description
     */
    @JacksonXmlProperty(localName = "descr")
    public String getDescription()
    {
        return mDescription;
    }

    /**
     * Sets the description
     * @param description value
     */
    public void setDescription(String description)
    {
        mDescription = description;
    }

    /**
     * Alpha tag or display label for the frequency
     * @return tag
     */
    @JacksonXmlProperty(localName = "alpha")
    public String getAlphaTag()
    {
        return mAlphaTag;
    }

    /**
     * Sets the alpha tag
     * @param alphaTag value
     */
    public void setAlphaTag(String alphaTag)
    {
        mAlphaTag = alphaTag;
    }

    /**
     * CTCSS tone used for the repeater
     * @return tone
     */
    @JacksonXmlProperty(localName = "tone")
    public String getTone()
    {
        return mTone;
    }

    /**
     * Sets the CTCSS tone
     * @param tone value
     */
    public void setTone(String tone)
    {
        mTone = tone;
    }

    /**
     * Color code for the channel.  This value is normally associated with digital protocols like DMR or Tetra.
     * @return color code.
     */
    @JacksonXmlProperty(localName = "colorCode")
    public String getColorCode()
    {
        return mColorCode;
    }

    /**
     * Sets the color code.
     * @param colorCode value
     */
    public void setColorCode(String colorCode)
    {
        mColorCode = colorCode;
    }

    /**
     * Talkgroup.
     * @return talkgroup
     */
    @JacksonXmlProperty(localName = "tg")
    public String getTalkgroup()
    {
        return mTalkgroup;
    }

    /**
     * Sets the talkgroup
     * @param talkgroup value
     */
    public void setTalkgroup(String talkgroup)
    {
        mTalkgroup = talkgroup;
    }

    /**
     * Timeslot for TDMA systems.
     * @return time slot
     */
    @JacksonXmlProperty(localName = "slot")
    public String getSlot()
    {
        return mSlot;
    }

    /**
     * Sets the timeslot
     * @param slot value
     */
    public void setSlot(String slot)
    {
        mSlot = slot;
    }

    /**
     * Mode for the channel
     * @return mode
     */
    @JacksonXmlProperty(localName = "mode")
    public String getMode()
    {
        return mMode;
    }

    /**
     * Sets the mode for the channel
     * @param mode value
     */
    public void setMode(String mode)
    {
        mMode = mode;
    }

    /**
     * Encryption identifier
     * @return encryption id
     */
    @JacksonXmlProperty(localName = "enc")
    public int getEncryption()
    {
        return mEncryption;
    }

    /**
     * Sets the encryption identifier
     * @param encryption identifier
     */
    public void setEncryption(int encryption)
    {
        mEncryption = encryption;
    }

    /**
     * Class
     * @return class
     */
    @JacksonXmlProperty(localName = "class")
    public String getClassification()
    {
        return mClassification;
    }

    /**
     * Sets the class
     * @param classification value
     */
    public void setClassification(String classification)
    {
        mClassification = classification;
    }

    /**
     * List of tags for the frequency channel
     * @return tags listing
     */
    @JacksonXmlProperty(localName = "tags")
    public List<Tag> getTags()
    {
        return mTags;
    }

    /**
     * Sets the list of tags for the channel
     * @param tags listing
     */
    public void setTags(List<Tag> tags)
    {
        mTags = tags;
    }

    /**
     * Sub-Category identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "scid")
    public int getSubCategoryId()
    {
        return mSubCategoryId;
    }

    /**
     * Sets the sub-category id
     * @param subCategoryId value
     */
    public void setSubCategoryId(int subCategoryId)
    {
        mSubCategoryId = subCategoryId;
    }

    /**
     * Display sort order.
     * @return order
     */
    @JacksonXmlProperty(localName = "sort")
    public int getSort()
    {
        return mSort;
    }

    /**
     * Sets the display sort order
     * @param sort order
     */
    public void setSort(int sort)
    {
        mSort = sort;
    }

    /**
     * Last updated date
     * @return date
     */
    @JacksonXmlProperty(localName = "lastUpdated")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    /**
     * Sets the last updated date
     * @param lastUpdated date
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }
}
