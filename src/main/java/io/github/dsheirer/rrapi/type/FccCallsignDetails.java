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

import java.util.List;

public class FccCallsignDetails
{
    private String mLicensee;
    private String mCallsign;
    private String mStatus;
    private String mGrantDate;
    private String mRadioService;
    private String mNotes;
    private List<FccLocation> mFccLocations;
    private List<FccFrequency> mFccFrequencies;

    public FccCallsignDetails()
    {
    }

    @JacksonXmlProperty(localName = "licensee")
    public String getLicensee()
    {
        return mLicensee;
    }

    public void setLicensee(String licensee)
    {
        mLicensee = licensee;
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

    @JacksonXmlProperty(localName = "status")
    public String getStatus()
    {
        return mStatus;
    }

    public void setStatus(String status)
    {
        mStatus = status;
    }

    @JacksonXmlProperty(localName = "grantDate")
    public String getGrantDate()
    {
        return mGrantDate;
    }

    public void setGrantDate(String grantDate)
    {
        mGrantDate = grantDate;
    }

    @JacksonXmlProperty(localName = "radioService")
    public String getRadioService()
    {
        return mRadioService;
    }

    public void setRadioService(String radioService)
    {
        mRadioService = radioService;
    }

    @JacksonXmlProperty(localName = "notes")
    public String getNotes()
    {
        return mNotes;
    }

    public void setNotes(String notes)
    {
        mNotes = notes;
    }

    @JacksonXmlProperty(localName = "locations")
    public List<FccLocation> getFccLocations()
    {
        return mFccLocations;
    }

    public void setFccLocations(List<FccLocation> fccLocations)
    {
        mFccLocations = fccLocations;
    }

    @JacksonXmlProperty(localName = "frequencies")
    public List<FccFrequency> getFccFrequencies()
    {
        return mFccFrequencies;
    }

    public void setFccFrequencies(List<FccFrequency> fccFrequencies)
    {
        mFccFrequencies = fccFrequencies;
    }
}
