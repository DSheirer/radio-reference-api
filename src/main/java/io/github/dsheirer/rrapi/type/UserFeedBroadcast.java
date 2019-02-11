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
 * Broadcastify feed / audio streaming channel configuration details
 */
public class UserFeedBroadcast
{
    private int mFeedId;
    private String mDescription;
    private String mHostname;
    private String mPort;
    private String mMount;
    private String mPassword;

    /**
     * Constructs a user feed broadcast instance
     */
    public UserFeedBroadcast()
    {
    }

    /**
     * Feed identifier
     * @return feed id
     */
    @JacksonXmlProperty(localName = "feedId")
    public int getFeedId()
    {
        return mFeedId;
    }

    public void setFeedId(int feedId)
    {
        mFeedId = feedId;
    }

    /**
     * Description of the feed
     * @return description
     */
    @JacksonXmlProperty(localName = "descr")
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    /**
     * Hostname for the server that will receive the feed
     * @return hostname
     */
    @JacksonXmlProperty(localName = "hostname")
    public String getHostname()
    {
        return mHostname;
    }

    public void setHostname(String hostname)
    {
        mHostname = hostname;
    }

    /**
     * Port for the server that will receive the feed
     * @return port number
     */
    @JacksonXmlProperty(localName = "port")
    public String getPort()
    {
        return mPort;
    }

    public void setPort(String port)
    {
        mPort = port;
    }

    /**
     * Icecast mount point for the feed
     * @return mount point
     */
    @JacksonXmlProperty(localName = "mount")
    public String getMount()
    {
        return mMount;
    }

    public void setMount(String mount)
    {
        mMount = mount;
    }

    /**
     * Password for the feed
     * @return password
     */
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
