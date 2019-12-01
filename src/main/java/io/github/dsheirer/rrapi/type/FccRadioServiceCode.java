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
 * Federal Communications Commission (FCC) radio service code
 */
public class FccRadioServiceCode
{
    private String mCode;
    private String mDescription;

    /**
     * Constructs an instance
     */
    public FccRadioServiceCode()
    {
    }

    /**
     * Code
     * @return code
     */
    @JacksonXmlProperty(localName = "code")
    public String getCode()
    {
        return mCode;
    }

    public void setCode(String code)
    {
        mCode = code;
    }

    /**
     * Description of the code
     * @return description
     */
    @JacksonXmlProperty(localName = "description")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }
}
