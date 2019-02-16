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
 * State request item used when requesting states by state ID value
 */
public class StateRequestItem
{
    private int mStateId;

    /**
     * Constructs an instance
     * @param stateId value
     */
    public StateRequestItem(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * State identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "stid")
    public int getStateId()
    {
        return mStateId;
    }

    /**
     * Sets the state identifier
     * @param stateId value
     */
    public void setStateId(int stateId)
    {
        mStateId = stateId;
    }

    /**
     * Creates an instance of a state request for the specified state identifier
     * @param stateId to request
     * @return instance of this class
     */
    public static StateRequestItem create(int stateId)
    {
        return new StateRequestItem(stateId);
    }
}
