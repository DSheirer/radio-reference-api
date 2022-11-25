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

package io.github.dsheirer.rrapi.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * Get voices response
 */
public class GetVoicesResponse extends ResponseBody
{
    private List<Voice> mVoices = new ArrayList<>();

    /**
     * Constructs an instance
     */
    public GetVoicesResponse()
    {
    }

    /**
     * Voices payload
     * @return voices
     */
    @JacksonXmlProperty(localName = "return")
    public List<Voice> getVoices()
    {
        return mVoices;
    }

    /**
     * Sets the voices payload
     * @param voices payload
     */
    public void setVoices(List<Voice> voices)
    {
        mVoices = voices;
    }
}
