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

/**
 * County
 */
public class County
{
    private int mCountyId;
    private String mName;
    private String mCountyHeader;

    /**
     * Constructs an instance
     */
    public County()
    {
    }

    /**
     * County identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "ctid")
    public int getCountyId()
    {
        return mCountyId;
    }

    /**
     * Sets the country identifier
     * @param countyId value
     */
    public void setCountyId(int countyId)
    {
        mCountyId = countyId;
    }

    /**
     * County name
     * @return name
     */
    @JacksonXmlProperty(localName = "countyName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the county name
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * County header, indicating of the object represents a county or other geo-political classifier.
     * @return header.
     */
    @JacksonXmlProperty(localName = "countyHeader")
    public String getCountyHeader()
    {
        return mCountyHeader;
    }

    /**
     * Sets the county header.
     * @param countyHeader value
     */
    public void setCountyHeader(String countyHeader)
    {
        mCountyHeader = countyHeader;
    }
}
