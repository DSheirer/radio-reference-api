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

import java.util.Date;

/**
 * Talkgroup
 */
public class Talkgroup
{
    private int mTalkgroupId;
    private int mDecimalValue;
    private String mSubFleet;
    private boolean mIsLtr;
    private String mTimeslot;
    private String mDescription;
    private String mAlphaTag;
    private String mMode;
    private int mEncryptionState;
    private Tag[] mTags;
    private int mTalkgroupCategoryId;
    private int mSortOrder;
    private Date mLastUpdated;

    /**
     * Creates an instance
     */
    public Talkgroup()
    {
    }

    /**
     * Talkgroup identifier
     *
     * @return id
     */
    @JacksonXmlProperty(localName = "tgId")
    public int getTalkgroupId()
    {
        return mTalkgroupId;
    }

    /**
     * Sets the talkgroup identifier
     * @param talkgroupId value
     */
    public void setTalkgroupId(int talkgroupId)
    {
        mTalkgroupId = talkgroupId;
    }

    /**
     * Decimal value for the talkgroup used by the radio network
     * @return decimal value
     */
    @JacksonXmlProperty(localName = "tgDec")
    public int getDecimalValue()
    {
        return mDecimalValue;
    }

    /**
     * Sets the decimal value
     * @param value decimalValue
     */
    public void setDecimalValue(int value)
    {
        mDecimalValue = value;
    }

    /**
     * Sub Fleet
     * @return sub fleet
     */
    @JacksonXmlProperty(localName = "tgSubfleet")
    public String getSubFleet()
    {
        return mSubFleet;
    }

    /**
     * Sets the sub fleet
     * @param subFleet value
     */
    public void setSubFleet(String subFleet)
    {
        mSubFleet = subFleet;
    }

    /**
     * Indicates if this is an LTR talkgroup
     * @return true if LTR
     */
    @JacksonXmlProperty(localName = "tgLtr")
    public boolean isLtr()
    {
        return mIsLtr;
    }

    /**
     * Sets the LTR talkgroup indicator
     * @param ltr indicator
     */
    public void setLtr(boolean ltr)
    {
        mIsLtr = ltr;
    }

    /**
     * Timeslot
     * @return timeslot
     */
    @JacksonXmlProperty(localName = "tgSlot")
    public String getTimeslot()
    {
        return mTimeslot;
    }

    /**
     * Sets the timeslot
     * @param timeslot value
     */
    public void setTimeslot(String timeslot)
    {
        mTimeslot = timeslot;
    }

    /**
     * Description of the talkgroup
     * @return description
     */
    @JacksonXmlProperty(localName = "tgDescr")
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
     * Alpha display tag or label
     * @return alpha tag
     */
    @JacksonXmlProperty(localName = "tgAlpha")
    public String getAlphaTag()
    {
        return mAlphaTag;
    }

    /**
     * Sets the alpha tag
     * @param alphaTag or label
     */
    public void setAlphaTag(String alphaTag)
    {
        mAlphaTag = alphaTag;
    }

    /**
     * Mode of operation for the talkgroup
     *
     * A - Analog
     * D - Digital
     * M - Mixed analog and digital
     * T - TDMA
     *
     * Encryption is indicated with a trailing E or e suffic value in addition to the A/D/M/T values
     *
     * xE - Full encryption
     * xe - Partial encryption
     *
     * @return mode of operation
     */
    @JacksonXmlProperty(localName = "tgMode")
    public String getMode()
    {
        return mMode;
    }

    /**
     * Indicates if this is an analog talkgroup
     * @return true or false
     */
    @JsonIgnore
    public boolean isAnalog()
    {
        return mMode != null && mMode.startsWith("A");
    }

    /**
     * Indicates if this is a digital talkgroup
     * @return true or false
     */
    @JsonIgnore
    public boolean isDigital()
    {
        return mMode != null && mMode.startsWith("D");
    }

    /**
     * Indicates if this is a mixed analog and digital talkgroup
     * @return true or false
     */
    @JsonIgnore
    public boolean isMixedMode()
    {
        return mMode != null && mMode.startsWith("M");
    }

    /**
     * Indicates if this is a TDMA talkgroup
     * @return true or false
     */
    @JsonIgnore
    public boolean isTDMA()
    {
        return mMode != null && mMode.startsWith("T");
    }


    /**
     * Indicates if this is talkgroup is always encrypted
     * @return true or false
     */
    @JsonIgnore
    public boolean isFullyEncrypted()
    {
        return mEncryptionState == 2;
    }

    /**
     * Indicates if this is talkgroup is sometimes encrypted
     * @return true or false
     */
    @JsonIgnore
    public boolean isPartiallyEncrypted()
    {
        return mEncryptionState == 1;
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
     * Encryption state
     *
     * 0 - Unencrypted
     * 1 - Mixed encryption
     * 2 = Encrypted
     *
     * @return encryption
     */
    @JacksonXmlProperty(localName = "enc")
    public int getEncryptionState()
    {
        return mEncryptionState;
    }

    /**
     * Sets the encryption state
     * @param encryptionState value
     */
    public void setEncryptionState(int encryptionState)
    {
        mEncryptionState = encryptionState;
    }

    /**
     * List of talkgroup tags
     * @return tags listing
     */
    @JacksonXmlProperty(localName = "tags")
    public Tag[] getTags()
    {
        return mTags;
    }

    /**
     * Sets the talkgroup tag list
     * @param tags list
     */
    public void setTags(Tag[] tags)
    {
        mTags = tags;
    }

    /**
     * Talkgroup category identifier
     * @return category ID
     */
    @JacksonXmlProperty(localName = "tgCid")
    public int getTalkgroupCategoryId()
    {
        return mTalkgroupCategoryId;
    }

    /**
     * Sets the category identifier
     * @param talkgroupCategoryId id
     */
    public void setTalkgroupCategoryId(int talkgroupCategoryId)
    {
        mTalkgroupCategoryId = talkgroupCategoryId;
    }

    /**
     * Display sort order
     * @return order
     */
    @JacksonXmlProperty(localName = "tgSort")
    public int getSortOrder()
    {
        return mSortOrder;
    }

    /**
     * Sets the sort order
     * @param sortOrder value
     */
    public void setSortOrder(int sortOrder)
    {
        mSortOrder = sortOrder;
    }

    /**
     * Last updated date
     * @return date
     */
    @JacksonXmlProperty(localName = "tgDate")
    public Date getLastUpdated()
    {
        return mLastUpdated;
    }

    /**
     * Sets the last updated lastUpdated
     * @param lastUpdated value
     */
    public void setLastUpdated(Date lastUpdated)
    {
        mLastUpdated = lastUpdated;
    }
}
