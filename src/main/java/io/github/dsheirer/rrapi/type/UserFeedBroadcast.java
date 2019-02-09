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

public class UserFeedBroadcast
{
    private int mFeedId;
    private String mDescription;
    private String mHostname;
    private String mPort;
    private String mMount;
    private String mPassword;

    public UserFeedBroadcast()
    {
    }

    @JacksonXmlProperty(localName = "feedId")
    public int getFeedId()
    {
        return mFeedId;
    }

    public void setFeedId(int feedId)
    {
        mFeedId = feedId;
    }

    @JacksonXmlProperty(localName = "descr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    @JacksonXmlProperty(localName = "hostname")
    public String getHostname()
    {
        return mHostname;
    }

    public void setHostname(String hostname)
    {
        mHostname = hostname;
    }

    @JacksonXmlProperty(localName = "port")
    public String getPort()
    {
        return mPort;
    }

    public void setPort(String port)
    {
        mPort = port;
    }

    @JacksonXmlProperty(localName = "mount")
    public String getMount()
    {
        return mMount;
    }

    public void setMount(String mount)
    {
        mMount = mount;
    }

    @JacksonXmlProperty(localName = "password")
    public String getPassword()
    {
        return mPassword;
    }

    public void setPassword(String password)
    {
        mPassword = password;
    }
}
