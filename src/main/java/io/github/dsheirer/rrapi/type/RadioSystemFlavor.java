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

public class RadioSystemFlavor
{
    private int mFlavorId;
    private int mFlavor;
    private String mDescription;

    public RadioSystemFlavor()
    {
    }

    @JacksonXmlProperty(localName = "sType")
    public int getFlavorId()
    {
        return mFlavorId;
    }

    public void setFlavorId(int flavorId)
    {
        mFlavorId = flavorId;
    }

    @JacksonXmlProperty(localName = "sFlavor")
    public int getFlavor()
    {
        return mFlavor;
    }

    public void setFlavor(int flavor)
    {
        mFlavor = flavor;
    }

    @JacksonXmlProperty(localName = "sFlavorDescr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }
}
