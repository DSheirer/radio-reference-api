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
 * Flavor of protocol that identifies a specific type or variant of a digital protocol.
 *
 * @see Type
 */
public class Flavor
{
    private int mTypeId;
    private int mFlavorId;
    private String mName;

    /**
     * Constructs an instance
     */
    public Flavor()
    {
    }

    /**
     * Protocol type identifier (parent) for this flavor
     * @return id
     */
    @JacksonXmlProperty(localName = "sType")
    public int getTypeId()
    {
        return mTypeId;
    }

    /**
     * Sets the type identifier
     * @param typeId value
     */
    public void setTypeId(int typeId)
    {
        mTypeId = typeId;
    }

    /**
     * Flavor identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "sFlavor")
    public int getFlavorId()
    {
        return mFlavorId;
    }

    /**
     * Sets the flavor identifier
     * @param flavorId value
     */
    public void setFlavorId(int flavorId)
    {
        mFlavorId = flavorId;
    }

    /**
     * Flavor name or description
     * @return name
     */
    @JacksonXmlProperty(localName = "sFlavorDescr")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the flavor name or description
     * @param name of the flavor
     */
    public void setName(String name)
    {
        mName = name;
    }
}
