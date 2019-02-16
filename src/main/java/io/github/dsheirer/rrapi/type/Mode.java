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
 * Operating mode indicating analog or digital modulation type/mode
 */
public class Mode
{
    private int mModeId;
    private String mModeName;

    /**
     * Constructs an instance
     */
    public Mode()
    {
    }

    /**
     * Mode identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "mode")
    public int getModeId()
    {
        return mModeId;
    }

    /**
     * Sets the mode identifier
     * @param modeId value
     */
    public void setModeId(int modeId)
    {
        mModeId = modeId;
    }

    /**
     * Mode name
     * @return name
     */
    @JacksonXmlProperty(localName = "modeName")
    public String getName()
    {
        return mModeName;
    }

    /**
     * Sets the mode name
     * @param modeName value
     */
    public void setName(String modeName)
    {
        mModeName = modeName;
    }
}
