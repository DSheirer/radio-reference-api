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

public class Bandplan
{
    private String mBase;
    private String mSpacing;
    private String mOffset;

    public Bandplan()
    {
    }

    @JacksonXmlProperty(localName = "base")
    public String getBase()
    {
        return mBase;
    }

    public void setBase(String base)
    {
        mBase = base;
    }

    @JacksonXmlProperty(localName = "spacing")
    public String getSpacing()
    {
        return mSpacing;
    }

    public void setSpacing(String spacing)
    {
        mSpacing = spacing;
    }

    @JacksonXmlProperty(localName = "offset")
    public String getOffset()
    {
        return mOffset;
    }

    public void setOffset(String offset)
    {
        mOffset = offset;
    }
}
