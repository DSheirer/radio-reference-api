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

/**
 * Federal Communications Commission (FCC) callsign details
 */
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

    /**
     * Constructs an instance
     */
    public FccCallsignDetails()
    {
    }

    /**
     * Name of the licensee
     * @return name
     */
    @JacksonXmlProperty(localName = "licensee")
    public String getLicensee()
    {
        return mLicensee;
    }

    /**
     * Sets the licensee name
     * @param licensee name
     */
    public void setLicensee(String licensee)
    {
        mLicensee = licensee;
    }

    /**
     * Callsign
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
     * Status of the licence (e.g. active or expired)
     * @return status
     */
    @JacksonXmlProperty(localName = "status")
    public String getStatus()
    {
        return mStatus;
    }

    /**
     * Sets the status of the licence
     * @param status of the license
     */
    public void setStatus(String status)
    {
        mStatus = status;
    }

    /**
     * License grant date
     * @return date
     */
    @JacksonXmlProperty(localName = "grantDate")
    public String getGrantDate()
    {
        return mGrantDate;
    }

    public void setGrantDate(String grantDate)
    {
        mGrantDate = grantDate;
    }

    /**
     * Radio service authorized for the license
     * @return radio service
     */
    @JacksonXmlProperty(localName = "radioService")
    public String getRadioService()
    {
        return mRadioService;
    }

    /**
     * Sets the radio service
     * @param radioService value
     */
    public void setRadioService(String radioService)
    {
        mRadioService = radioService;
    }

    /**
     * Notes
     * @return notes
     */
    @JacksonXmlProperty(localName = "notes")
    public String getNotes()
    {
        return mNotes;
    }

    /**
     * Sets the notes
     * @param notes value
     */
    public void setNotes(String notes)
    {
        mNotes = notes;
    }

    /**
     * List of locations authorized for the license
     * @return location list
     */
    @JacksonXmlProperty(localName = "locations")
    public List<FccLocation> getFccLocations()
    {
        return mFccLocations;
    }

    /**
     * Sets the list of locations
     * @param fccLocations listing
     */
    public void setFccLocations(List<FccLocation> fccLocations)
    {
        mFccLocations = fccLocations;
    }

    /**
     * List of frequencies authorized for the license
     * @return frequency listing
     */
    @JacksonXmlProperty(localName = "frequencies")
    public List<FccFrequency> getFccFrequencies()
    {
        return mFccFrequencies;
    }

    /**
     * Sets the list of frequencies
     * @param fccFrequencies listing
     */
    public void setFccFrequencies(List<FccFrequency> fccFrequencies)
    {
        mFccFrequencies = fccFrequencies;
    }
}
