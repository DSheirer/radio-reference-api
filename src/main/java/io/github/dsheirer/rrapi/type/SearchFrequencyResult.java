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
import java.util.List;

/**
 * Search frequency result item
 */
public class SearchFrequencyResult
{
    private double mDownlink;
    private double mUplink;
    private String mCallsign;
    private String mDescription;
    private String mAlpha;
    private String mTone;
    private String mColorCode;
    private String mTalkgroup;
    private String mSlot;
    private String mMode;
    private String mClassification;
    private List<Tag> mTags = new ArrayList<>();
    private int mSubCategoryId;
    private int mStateId;
    private int mAgencyId;
    private int mCountyId;

    /**
     * Constructs an instance
     */
    public SearchFrequencyResult()
    {
    }

    /**
     * Downlink (tower to mobile) or output frequency
     * @return frequency in MHz
     */
    @JacksonXmlProperty(localName = "out")
    public double getDownlink()
    {
        return mDownlink;
    }

    /**
     * Sets the downlink frequency
     * @param downlink frequency in MHz
     */
    public void setDownlink(double downlink)
    {
        mDownlink = downlink;
    }

    /**
     * Uplink (mobile to tower) or input frequency
     * @return uplink frequency in MHz
     */
    @JacksonXmlProperty(localName = "in")
    public double getUplink()
    {
        return mUplink;
    }

    /**
     * Sets the uplink frequency
     * @param uplink frequency
     */
    public void setUplink(double uplink)
    {
        mUplink = uplink;
    }

    /**
     * Callsign for the channel
     * @return callsign
     */
    @JacksonXmlProperty(localName = "callsign")
    public String getCallsign()
    {
        return mCallsign;
    }

    /**
     * Sets the callsign for the channel
     * @param callsign value
     */
    public void setCallsign(String callsign)
    {
        mCallsign = callsign;
    }

    /**
     * Description of the channel
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
     * Alpha display tag for the channel
     * @return alpha tag
     */
    @JacksonXmlProperty(localName = "alpha")
    public String getAlpha()
    {
        return mAlpha;
    }

    /**
     * Sets the alpha tag
     * @param alpha value
     */
    public void setAlpha(String alpha)
    {
        mAlpha = alpha;
    }

    /**
     * CTCSS tone frequency for accessing the repeater input
     * @return tone
     */
    @JacksonXmlProperty(localName = "tone")
    public String getTone()
    {
        return mTone;
    }

    /**
     * Sets the tone
     * @param tone value
     */
    public void setTone(String tone)
    {
        mTone = tone;
    }

    /**
     * Color code for the channel
     * @return color code
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
     * Talkgroup for the channel
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
     * Timeslot for a TDMA channel
     * @return timeslot
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
     * Mode or modulation for the channel
     * @return mode
     */
    @JacksonXmlProperty(localName = "mode")
    public String getMode()
    {
        return mMode;
    }

    /**
     * Sets the mode
     * @param mode value
     */
    public void setMode(String mode)
    {
        mMode = mode;
    }

    /**
     * Class for the channel
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
     * Tag list
     * @return tags
     */
    @JacksonXmlProperty(localName = "tags")
    public List<Tag> getTags()
    {
        return mTags;
    }

    /**
     * Sets the tag list
     * @param tags value
     */
    public void setTags(List<Tag> tags)
    {
        mTags = tags;
    }

    /**
     * Sub-Category identifier
     *
     * @see SubCategory SubCategory
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
     * State identifier
     *
     * @see State State
     *
     * @return id
     */
    @JacksonXmlProperty(localName = "sid")
    public int getStateId()
    {
        return mStateId;
    }

    /**
     * Sets the state identifier
     * @param stateId value
     */
    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * Agency identifier
     *
     * @see Agency Agency
     * @return id
     */
    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    /**
     * Sets the agency identifier
     * @param agencyId value
     */
    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    /**
     * County identifier
     *
     * @see County County
     * @return id
     */
    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    /**
     * Sets the county identifier
     * @param countyId value
     */
    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }
}
