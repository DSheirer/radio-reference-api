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
 * Radio site information
 */
public class Site
{
    private int mSiteId;
    private int mSystemId;
    private int mSiteNumber;
    private String mDescription;
    private int mZoneNumber;
    private String mZoneDescription;
    private int mRfss;
    private String mNac;
    private int mRan;
    private String mNeighbors;
    private String mLocation;
    private int mCountyId;
    private String mCountyName;
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

    /**
     * Constructs an instance
     */
    public Site()
    {
    }

    /**
     * Site identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "siteId")
    public int getSiteId()
    {
        return mSiteId;
    }

    /**
     * Sets the site identifier
     * @param siteId value
     */
    public void setSiteId(int siteId)
    {
        mSiteId = siteId;
    }

    /**
     * System identifier
     * @return system id
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system identifier
     * @param systemId value
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Site number
     * @return number
     */
    @JacksonXmlProperty(localName = "siteNumber")
    public int getSiteNumber()
    {
        return mSiteNumber;
    }

    /**
     * Sets the site number
     * @param siteNumber value
     */
    public void setSiteNumber(int siteNumber)
    {
        mSiteNumber = siteNumber;
    }

    /**
     * Site description
     * @return description
     */
    @JacksonXmlProperty(localName = "siteDescr")
    public String getDescription()
    {
        return mDescription;
    }

    /**
     * Sets the site description
     * @param description value
     */
    public void setDescription(String description)
    {
        mDescription = description;
    }

    /**
     * Zone number
     * @return zone
     */
    @JacksonXmlProperty(localName = "zoneNumber")
    public int getZoneNumber()
    {
        return mZoneNumber;
    }

    /**
     * Sets the zone number
     * @param  zoneNumber value
     */
    public void setZoneNumber(int zoneNumber)
    {
        mZoneNumber = zoneNumber;
    }

    /**
     * Zone description
     * @return description
     */
    @JacksonXmlProperty(localName = "zoneDescr")
    public String getZoneDescription()
    {
        return mZoneDescription;
    }

    /**
     * Sets the zone description
     * @param zoneDescription value
     */
    public void setZoneDescription(String zoneDescription)
    {
        mZoneDescription = zoneDescription;
    }

    /**
     * Radio Frequency Sub-System (RFSS)
     * @return rfss
     */
    @JacksonXmlProperty(localName = "rfss")
    public int getRfss()
    {
        return mRfss;
    }

    /**
     * Sets the RFSS
     * @param rfss value
     */
    public void setRfss(int rfss)
    {
        mRfss = rfss;
    }

    /**
     * NAC code
     * @return nac
     */
    @JacksonXmlProperty(localName = "nac")
    public String getNac()
    {
        return mNac;
    }

    /**
     * Sets the NAC
     * @param nac value
     */
    public void setNac(String nac)
    {
        mNac = nac;
    }

    /**
     * Radio Access Network (RAN)
     * @return ran
     */
    @JacksonXmlProperty(localName = "ran")
    public int getRan()
    {
        return mRan;
    }

    /**
     * Sets the ran
     * @param ran value
     */
    public void setRan(int ran)
    {
        mRan = ran;
    }

    /**
     * Neighboring sites
     * @return neighbors
     */
    @JacksonXmlProperty(localName = "siteNeighbors")
    public String getNeighbors()
    {
        return mNeighbors;
    }

    /**
     * Sets the neighbors
     * @param neighbors value
     */
    public void setNeighbors(String neighbors)
    {
        mNeighbors = neighbors;
    }

    /**
     * Location
     * @return location
     */
    @JacksonXmlProperty(localName = "siteLocation")
    public String getLocation()
    {
        return mLocation;
    }

    /**
     * Sets the location
     * @param location value
     */
    public void setLocation(String location)
    {
        mLocation = location;
    }

    /**
     * County identifier
     *
     * @see County County
     * @return id
     */
    @JacksonXmlProperty(localName = "siteCtid")
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

    /**
     * County name.
     * @return name or null
     */
    @JacksonXmlProperty(localName = "siteCt")
    public String getCountyName()
    {
        return mCountyName;
    }

    /**
     * Sets the county name
     * @param countyName value
     */
    public void setCountyName(String countyName)
    {
        mCountyName = countyName;
    }

    /**
     * Modulation employed at the site
     * @return modulation
     */
    @JacksonXmlProperty(localName = "siteModulation")
    public String getModulation()
    {
        return mModulation;
    }

    /**
     * Sets the modulation
     * @param modulation value
     */
    public void setModulation(String modulation)
    {
        mModulation = modulation;
    }

    /**
     * Notes
     * @return notes
     */
    @JacksonXmlProperty(localName = "siteNotes")
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
     * Location latitude
     * @return latitude in decimal degrees
     */
    @JacksonXmlProperty(localName = "lat")
    public double getLatitude()
    {
        return mLatitude;
    }

    /**
     * Sets the latitude
     * @param latitude value
     */
    public void setLatitude(double latitude)
    {
        mLatitude = latitude;
    }

    /**
     * Location longitude
     * @return longitude in decimal degrees
     */
    @JacksonXmlProperty(localName = "lon")
    public double getLongitude()
    {
        return mLongitude;
    }

    /**
     * Sets the longitude
     * @param longitude value
     */
    public void setLongitude(double longitude)
    {
        mLongitude = longitude;
    }

    /**
     * Range or extent of the site
     * @return range
     */
    @JacksonXmlProperty(localName = "range")
    public double getRange()
    {
        return mRange;
    }

    /**
     * Sets the range
     * @param range value
     */
    public void setRange(double range)
    {
        mRange = range;
    }

    /**
     * Frequency splinter
     * @return splinter, 0 = false, 1 = true
     */
    @JacksonXmlProperty(localName = "splinter")
    public int getSplinter()
    {
        return mSplinter;
    }

    /**
     * Sets the splinter
     * @param splinter value
     */
    public void setSplinter(int splinter)
    {
        mSplinter = splinter;
    }

    /**
     * Rebanding indicator
     * @return 0 = false, 1 = true for rebanded
     */
    @JacksonXmlProperty(localName = "rebanded")
    public int getRebanded()
    {
        return mRebanded;
    }

    /**
     * Sets rebanded
     * @param rebanded value
     */
    public void setRebanded(int rebanded)
    {
        mRebanded = rebanded;
    }

    /**
     * List of site licenses
     * @return license listing
     */
    @JacksonXmlProperty(localName = "siteLicenses")
    public List<SiteLicense> getSiteLicenses()
    {
        return mSiteLicenses;
    }

    /**
     * Sets site licences
     * @param siteLicenses list
     */
    public void setSiteLicenses(List<SiteLicense> siteLicenses)
    {
        mSiteLicenses = siteLicenses;
    }

    /**
     * List of site frequencies
     * @return list
     */
    @JacksonXmlProperty(localName = "siteFreqs")
    public List<SiteFrequency> getSiteFrequencies()
    {
        return mSiteFrequencies;
    }

    /**
     * Sets site frequencies
     * @param siteFrequencies list
     */
    public void setSiteFrequencies(List<SiteFrequency> siteFrequencies)
    {
        mSiteFrequencies = siteFrequencies;
    }

    /**
     * Bandplan for the site
     * @return bandplan
     */
    @JacksonXmlProperty(localName = "bandplan")
    public Bandplan getBandplan()
    {
        return mBandplan;
    }

    /**
     * Sets the bandplan
     * @param bandplan value
     */
    public void setBandplan(Bandplan bandplan)
    {
        mBandplan = bandplan;
    }
}
