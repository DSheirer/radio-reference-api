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

package io.github.dsheirer.rrapi.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Search metro frequency
 */
public class SearchMetroFrequency extends RequestBody
{
    private int mMetroId;
    private double mFrequency;
    private String mTone;

    /**
     * Constructs the request
     * @param authorizationInformation for the request
     * @param metroId to search for
     * @param frequency to search for in MHz
     * @param tone PL tone to search for
     */
    public SearchMetroFrequency(AuthorizationInformation authorizationInformation, int metroId, double frequency, String tone)
    {
        super(authorizationInformation);
        mMetroId = metroId;
        mFrequency = frequency;
        mTone = tone;
    }

    /**
     * Constructs a request
     * @param authorizationInformation for the request
     * @param metroId to search for
     * @param frequency to search for in MHz
     */
    public SearchMetroFrequency(AuthorizationInformation authorizationInformation, int metroId, double frequency)
    {
        this(authorizationInformation, metroId, frequency, null);
    }

    /**
     * Metro id
     * @return id
     */
    @JacksonXmlProperty(localName = "mid")
    public int getMetroId()
    {
        return mMetroId;
    }

    /**
     * Sets the metro id
     * @param metroId to query
     */
    public void setMetroId(int metroId)
    {
        mMetroId = metroId;
    }

    /**
     * Frequency to search for
     * @return frequency in MHz
     */
    @JacksonXmlProperty(localName = "freq")
    public double getFrequency()
    {
        return mFrequency;
    }

    /**
     * Search frequency
     * @param frequency in MHz
     */
    public void setFrequency(double frequency)
    {
        mFrequency = frequency;
    }

    /**
     * Tone (PL) on the channel
     * @return search tone
     */
    @JacksonXmlProperty(localName = "tone")
    public String getTone()
    {
        return mTone;
    }

    /**
     * Specifies a tone to search for
     * @param tone to search for
     */
    public void setTone(String tone)
    {
        mTone = tone;
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation credentials
     * @param metroId to query
     * @param frequency in MHz
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int metroId, double frequency)
    {
        return RequestBody.create(new SearchMetroFrequency(authorizationInformation, metroId, frequency));
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation credentials
     * @param metroId to query
     * @param frequency in MHz
     * @param tone PL
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int metroId, double frequency, String tone)
    {
        return RequestBody.create(new SearchMetroFrequency(authorizationInformation, metroId, frequency, tone));
    }
}
