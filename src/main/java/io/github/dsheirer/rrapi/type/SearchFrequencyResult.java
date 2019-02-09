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

    public SearchFrequencyResult()
    {
    }

    @JacksonXmlProperty(localName = "out")
    public double getDownlink()
    {
        return mDownlink;
    }

    public void setDownlink(double downlink)
    {
        mDownlink = downlink;
    }

    @JacksonXmlProperty(localName = "in")
    public double getUplink()
    {
        return mUplink;
    }

    public void setUplink(double uplink)
    {
        mUplink = uplink;
    }

    @JacksonXmlProperty(localName = "callsign")
    public String getCallsign()
    {
        return mCallsign;
    }

    public void setCallsign(String callsign)
    {
        mCallsign = callsign;
    }

    @JacksonXmlProperty(localName = "descr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    @JacksonXmlProperty(localName = "alpha")
    public String getAlpha()
    {
        return mAlpha;
    }

    public void setAlpha(String alpha)
    {
        mAlpha = alpha;
    }

    @JacksonXmlProperty(localName = "tone")
    public String getTone()
    {
        return mTone;
    }

    public void setTone(String tone)
    {
        mTone = tone;
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

    @JacksonXmlProperty(localName = "tg")
    public String getTalkgroup()
    {
        return mTalkgroup;
    }

    public void setTalkgroup(String talkgroup)
    {
        mTalkgroup = talkgroup;
    }

    @JacksonXmlProperty(localName = "slot")
    public String getSlot()
    {
        return mSlot;
    }

    public void setSlot(String slot)
    {
        mSlot = slot;
    }

    @JacksonXmlProperty(localName = "mode")
    public String getMode()
    {
        return mMode;
    }

    public void setMode(String mode)
    {
        mMode = mode;
    }

    @JacksonXmlProperty(localName = "class")
    public String getClassification()
    {
        return mClassification;
    }

    public void setClassification(String classification)
    {
        mClassification = classification;
    }

    @JacksonXmlProperty(localName = "tags")
    public List<Tag> getTags()
    {
        return mTags;
    }

    public void setTags(List<Tag> tags)
    {
        mTags = tags;
    }

    @JacksonXmlProperty(localName = "scid")
    public int getSubCategoryId()
    {
        return mSubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId)
    {
        mSubCategoryId = subCategoryId;
    }

    @JacksonXmlProperty(localName = "sid")
    public int getStateId()
    {
        return mStateId;
    }

    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }
}
