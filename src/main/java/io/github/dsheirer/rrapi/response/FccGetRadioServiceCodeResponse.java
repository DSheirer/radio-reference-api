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
import io.github.dsheirer.rrapi.type.FccRadioServiceCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Get FCC radio service code response
 */
public class FccGetRadioServiceCodeResponse extends ResponseBody
{
    private List<FccRadioServiceCode> mFccRadioServiceCodes = new ArrayList<>();

    /**
     * Constructs an instance
     */
    public FccGetRadioServiceCodeResponse()
    {
    }

    /**
     * Code payload
     * @return code
     */
    @JacksonXmlProperty(localName = "return")
    public List<FccRadioServiceCode> getFccRadioServiceCodes()
    {
        return mFccRadioServiceCodes;
    }

    /**
     * Sets the payload
     * @param codes to set
     */
    public void setFccRadioServiceCodes(List<FccRadioServiceCode> codes)
    {
        if(codes != null)
        {
            mFccRadioServiceCodes = codes;
        }
    }
}
