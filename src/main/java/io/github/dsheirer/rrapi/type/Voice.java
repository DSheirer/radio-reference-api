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
 * Voice CODEC option for a protocol type
 */
public class Voice
{
    private int mTypeId;
    private int mVoiceId;
    private String mName;

    /**
     * Constructs a voice instance
     */
    public Voice()
    {
    }

    /**
     * Protocol type (parent).
     *
     * @see Type
     *
     * @return type identifier
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
     * Voice identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "sVoice")
    public int getVoiceId()
    {
        return mVoiceId;
    }

    /**
     * Sets the voice identifier
     * @param voiceId value
     */
    public void setVoiceId(int voiceId)
    {
        mVoiceId = voiceId;
    }

    /**
     * Name or description
     * @return name
     */
    @JacksonXmlProperty(localName = "sVoiceDescr")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the name or description
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }
}
