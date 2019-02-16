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
 * Abbreviated agency information
 */
public class Agency
{
    private int mAgencyId;
    private String mName;
    private int mType;

    public Agency()
    {
    }

    /**
     * Agency ID
     * @return id
     */
    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    /**
     * Agency name
     * @return name
     */
    @JacksonXmlProperty(localName = "aName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Agency type.
     * @return type
     */
    @JacksonXmlProperty(localName = "aType")
    public int getType()
    {
        return mType;
    }

    public void setType(int type)
    {
        mType = type;
    }
}
