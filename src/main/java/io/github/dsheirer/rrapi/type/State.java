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

import java.util.Objects;

/**
 * State
 */
public class State implements Comparable<State>
{
    private int mStateId;
    private String mName;
    private String mStateCode;

    /**
     * Constructs an instance
     */
    public State()
    {
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
     * Name
     * @return name
     */
    @JacksonXmlProperty(localName = "stateName")
    public String getName()
    {
        return mName;
    }

    /**
     * Sets the name
     * @param name value
     */
    public void setName(String name)
    {
        mName = name;
    }

    /**
     * Code or digraph
     * @return code
     */
    @JacksonXmlProperty(localName = "stateCode")
    public String getStateCode()
    {
        return mStateCode;
    }

    /**
     * Sets the code
     * @param stateCode value
     */
    public void setStateCode(String stateCode)
    {
        mStateCode = stateCode;
    }

    @Override
    public int compareTo(State o)
    {
        return getName().compareTo(o.getName());
    }

    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(o == null || getClass() != o.getClass())
        {
            return false;
        }
        State state = (State)o;
        return getStateId() == state.getStateId();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getStateId());
    }
}
