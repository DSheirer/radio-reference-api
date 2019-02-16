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
 * Protocol type or name
 *
 * @see Flavor Flavor for protocol type variants or flavors
 * @see Voice Voice for protocol voice CODEC options
 */
public class Type
{
    private int mTypeId;
    private String mName;

    /**
     * Constructs an instance
     */
    public Type()
    {
    }

    /**
     * Type identifier
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
     * Protocol type name or description
     * @return name
     */
    @JacksonXmlProperty(localName = "sTypeDescr")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the protocol type name
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }
}
