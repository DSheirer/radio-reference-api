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

import java.util.Date;

public class Talkgroup
{
    private int mTalkgroupId;
    private int mValue;
    private String mSubFleet;
    private boolean mIsLtr;
    private String mTimeslot;
    private String mDescription;
    private String mAlphaTag;
    private String mMode;
    private int mEncrypted;
    private Tag[] mTags;
    private int mCid;
    private int mSort;
    private Date mDate;

    public Talkgroup()
    {
    }

    @JacksonXmlProperty(localName = "tgId")
    public int getTalkgroupId()
    {
        return mTalkgroupId;
    }

    public void setTalkgroupId(int talkgroupId)
    {
        mTalkgroupId = talkgroupId;
    }

    @JacksonXmlProperty(localName = "tgDec")
    public int getValue()
    {
        return mValue;
    }

    public void setValue(int value)
    {
        mValue = value;
    }

    @JacksonXmlProperty(localName = "tgSubfleet")
    public String getSubFleet()
    {
        return mSubFleet;
    }

    public void setSubFleet(String subFleet)
    {
        mSubFleet = subFleet;
    }

    @JacksonXmlProperty(localName = "tgLtr")
    public boolean isLtr()
    {
        return mIsLtr;
    }

    public void setLtr(boolean ltr)
    {
        mIsLtr = ltr;
    }

    @JacksonXmlProperty(localName = "tgSlot")
    public String getTimeslot()
    {
        return mTimeslot;
    }

    public void setTimeslot(String timeslot)
    {
        mTimeslot = timeslot;
    }

    @JacksonXmlProperty(localName = "tgDescr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    @JacksonXmlProperty(localName = "tgAlpha")
    public String getAlphaTag()
    {
        return mAlphaTag;
    }

    public void setAlphaTag(String alphaTag)
    {
        mAlphaTag = alphaTag;
    }

    @JacksonXmlProperty(localName = "tgMode")
    public String getMode()
    {
        return mMode;
    }

    public void setMode(String mode)
    {
        mMode = mode;
    }

    @JacksonXmlProperty(localName = "enc")
    public int getEncrypted()
    {
        return mEncrypted;
    }

    public void setEncrypted(int encrypted)
    {
        mEncrypted = encrypted;
    }

    @JacksonXmlProperty(localName = "tags")
    public Tag[] getTags()
    {
        return mTags;
    }

    public void setTags(Tag[] tags)
    {
        mTags = tags;
    }

    @JacksonXmlProperty(localName = "tgCid")
    public int getCid()
    {
        return mCid;
    }

    public void setCid(int cid)
    {
        mCid = cid;
    }

    @JacksonXmlProperty(localName = "tgSort")
    public int getSort()
    {
        return mSort;
    }

    public void setSort(int sort)
    {
        mSort = sort;
    }

    @JacksonXmlProperty(localName = "tgDate")
    public Date getDate()
    {
        return mDate;
    }

    public void setDate(Date date)
    {
        mDate = date;
    }
}
