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
import io.github.dsheirer.rrapi.type.Talkgroup;
import java.util.List;

/**
 * Gets talkgroups response
 */
public class GetTalkgroupsResponse extends ResponseBody
{
    private List<Talkgroup> mTalkgroups;

    /**
     * Constructs an instance
     */
    public GetTalkgroupsResponse()
    {
    }

    /**
     * Talkgroups payload
     * @return talkgroups
     */
    @JacksonXmlProperty(localName = "return")
    public List<Talkgroup> getTalkgroups()
    {
        return mTalkgroups;
    }

    /**
     * Sets the talkgroups paylaod
     * @param talkgroups payload
     */
    public void setTalkgroups(List<Talkgroup> talkgroups)
    {
        mTalkgroups = talkgroups;
    }
}
