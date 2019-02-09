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

public class Mode
{
    private int mModeId;
    private String mModeName;

    public Mode()
    {
    }

    @JacksonXmlProperty(localName = "mode")
    public int getModeId()
    {
        return mModeId;
    }

    public void setModeId(int modeId)
    {
        mModeId = modeId;
    }

    @JacksonXmlProperty(localName = "modeName")
    public String getModeName()
    {
        return mModeName;
    }

    public void setModeName(String modeName)
    {
        mModeName = modeName;
    }
}
