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
 * County and identifier used when requesting counties by ID value.
 */
public class CountyRequestItem
{
    private int mCountyId;

    /**
     * Constructs a county request item
     * @param countyId value
     */
    public CountyRequestItem(int countyId)
    {
        mCountyId = countyId;
    }

    /**
     * County identifier
     * @return value
     */
    @JacksonXmlProperty(localName = "ctid")
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
     * Creates an instance of a county request item for the specified county identifier.
     * @param countyId value
     * @return instance
     */
    public static CountyRequestItem create(int countyId)
    {
        return new CountyRequestItem(countyId);
    }
}
