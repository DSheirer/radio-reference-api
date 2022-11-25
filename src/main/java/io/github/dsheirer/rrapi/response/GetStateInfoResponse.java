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
import io.github.dsheirer.rrapi.type.StateInfo;

/**
 * Get state info response
 */
public class GetStateInfoResponse extends ResponseBody
{
    private StateInfo mStateInfo;

    /**
     * Constructs an instance
     */
    public GetStateInfoResponse()
    {
    }

    /**
     * State info object
     * @return state info
     */
    @JacksonXmlProperty(localName = "return")
    public StateInfo getStateInfo()
    {
        return mStateInfo;
    }

    /**
     * Sets the state info object
     * @param stateInfo to set
     */
    public void setStateInfo(StateInfo stateInfo)
    {
        mStateInfo = stateInfo;
    }
}
