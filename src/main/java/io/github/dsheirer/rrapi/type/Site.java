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

public class Site
{
    private int mSiteId;
    private int mSid;
    private int mSiteNumber;
    private String mDescription;
    private int mZoneNumber;
    private String mZoneDescription;
    private int mRfss;
    private String mNac;
    private int mRan;
    private String mNeighbors;
    private String mLocation;
    private int mCtid;
    private String mCt;
    private String mModulation;
    private String mNotes;
    private double mLatitude;
    private double mLongitude;
    private double mRange;
    private int mSplinter;
    private int mRebanded;
    private List<SiteLicense> mSiteLicenses = new ArrayList<>();
    private List<SiteFrequency> mSiteFrequencies = new ArrayList<>();
    private Bandplan mBandplan;

    public Site()
    {
    }

    @JacksonXmlProperty(localName = "siteId")
    public int getSiteId()
    {
        return mSiteId;
    }

    public void setSiteId(int siteId)
    {
        mSiteId = siteId;
    }

    @JacksonXmlProperty(localName = "sid")
    public int getSid()
    {
        return mSid;
    }

    public void setSid(int sid)
    {
        mSid = sid;
    }

    @JacksonXmlProperty(localName = "siteNumber")
    public int getSiteNumber()
    {
        return mSiteNumber;
    }

    public void setSiteNumber(int siteNumber)
    {
        mSiteNumber = siteNumber;
    }

    @JacksonXmlProperty(localName = "siteDescr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    @JacksonXmlProperty(localName = "zoneNumber")
    public int getZoneNumber()
    {
        return mZoneNumber;
    }

    public void setZoneNumber(int zoneNumber)
    {
        mZoneNumber = zoneNumber;
    }

    @JacksonXmlProperty(localName = "zoneDescr")
    public String getZoneDescription()
    {
        return mZoneDescription;
    }

    public void setZoneDescription(String zoneDescription)
    {
        mZoneDescription = zoneDescription;
    }

    @JacksonXmlProperty(localName = "rfss")
    public int getRfss()
    {
        return mRfss;
    }

    public void setRfss(int rfss)
    {
        mRfss = rfss;
    }

    @JacksonXmlProperty(localName = "nac")
    public String getNac()
    {
        return mNac;
    }

    public void setNac(String nac)
    {
        mNac = nac;
    }

    @JacksonXmlProperty(localName = "ran")
    public int getRan()
    {
        return mRan;
    }

    public void setRan(int ran)
    {
        mRan = ran;
    }

    @JacksonXmlProperty(localName = "siteNeighbors")
    public String getNeighbors()
    {
        return mNeighbors;
    }

    public void setNeighbors(String neighbors)
    {
        mNeighbors = neighbors;
    }

    @JacksonXmlProperty(localName = "siteLocation")
    public String getLocation()
    {
        return mLocation;
    }

    public void setLocation(String location)
    {
        mLocation = location;
    }

    @JacksonXmlProperty(localName = "siteCtid")
    public int getCtid()
    {
        return mCtid;
    }

    public void setCtid(int ctid)
    {
        mCtid = ctid;
    }

    @JacksonXmlProperty(localName = "siteCt")
    public String getCt()
    {
        return mCt;
    }

    public void setCt(String ct)
    {
        mCt = ct;
    }

    @JacksonXmlProperty(localName = "siteModulation")
    public String getModulation()
    {
        return mModulation;
    }

    public void setModulation(String modulation)
    {
        mModulation = modulation;
    }

    @JacksonXmlProperty(localName = "siteNotes")
    public String getNotes()
    {
        return mNotes;
    }

    public void setNotes(String notes)
    {
        mNotes = notes;
    }

    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    @JacksonXmlProperty(localName = "range")
    public double getRange()
    {
        return mRange;
    }

    public void setRange(double range)
    {
        mRange = range;
    }

    @JacksonXmlProperty(localName = "splinter")
    public int getSplinter()
    {
        return mSplinter;
    }

    public void setSplinter(int splinter)
    {
        mSplinter = splinter;
    }

    @JacksonXmlProperty(localName = "rebanded")
    public int getRebanded()
    {
        return mRebanded;
    }

    public void setRebanded(int rebanded)
    {
        mRebanded = rebanded;
    }

    @JacksonXmlProperty(localName = "siteLicenses")
    public List<SiteLicense> getSiteLicenses()
    {
        return mSiteLicenses;
    }

    public void setSiteLicenses(List<SiteLicense> siteLicenses)
    {
        mSiteLicenses = siteLicenses;
    }

    @JacksonXmlProperty(localName = "siteFreqs")
    public List<SiteFrequency> getSiteFrequencies()
    {
        return mSiteFrequencies;
    }

    public void setSiteFrequencies(List<SiteFrequency> siteFrequencies)
    {
        mSiteFrequencies = siteFrequencies;
    }

    @JacksonXmlProperty(localName = "bandplan")
    public Bandplan getBandplan()
    {
        return mBandplan;
    }

    public void setBandplan(Bandplan bandplan)
    {
        mBandplan = bandplan;
    }
}
