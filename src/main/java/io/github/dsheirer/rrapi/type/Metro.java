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

import java.util.Objects;

/**
 * Metro area: id and name.  Returned by {@link io.github.dsheirer.rrapi.RadioReferenceService#getMetrosMap()}; the
 * counties of a metro area come from {@link io.github.dsheirer.rrapi.RadioReferenceService#getMetroCounties(int)}.
 */
public class Metro implements Comparable<Metro>
{
    private int mMetroId;
    private String mName;

    /**
     * Constructs an instance
     */
    public Metro()
    {
    }

    /**
     * Metro identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "mid")
    public int getMetroId()
    {
        return mMetroId;
    }

    /**
     * Sets the metro id
     * @param metroId value
     */
    public void setMetroId(int metroId)
    {
        mMetroId = metroId;
    }

    /**
     * Name of the metro area
     * @return name
     */
    @JacksonXmlProperty(localName = "metroName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the name
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }

    @Override
    public int compareTo(Metro o)
    {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        Metro metro = (Metro)o;
        return getMetroId() == metro.getMetroId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getMetroId());
    }
}
