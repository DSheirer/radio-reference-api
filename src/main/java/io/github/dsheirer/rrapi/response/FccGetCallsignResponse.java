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

package io.github.dsheirer.rrapi.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.CountryInfo;
import io.github.dsheirer.rrapi.type.FccCallsignDetails;

/**
 * Get FCC callsign response
 */
public class FccGetCallsignResponse extends ResponseBody
{
    private FccCallsignDetails mFccCallsignDetails;

    /**
     * Constructs an instance
     */
    public FccGetCallsignResponse()
    {
    }

    /**
     * Payload details
     * @return fcc callsign details
     */
    @JacksonXmlProperty(localName = "return")
    public FccCallsignDetails getFccCallsignDetails()
    {
        return mFccCallsignDetails;
    }

    /**
     * Sets the payload
     * @param fccCallsignDetails results
     */
    public void setFccCallsignDetails(FccCallsignDetails fccCallsignDetails)
    {
        mFccCallsignDetails = fccCallsignDetails;
    }
}
