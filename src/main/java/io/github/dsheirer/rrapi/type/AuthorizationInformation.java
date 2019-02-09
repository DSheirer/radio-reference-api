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
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.github.dsheirer.rrapi.RadioReferenceService;

@JacksonXmlRootElement(localName = "authInfo")
public class AuthorizationInformation
{
    private String mApplicationKey;
    private String mUserName;
    private String mPassword;
    private String mVersion = RadioReferenceService.API_VERSION_15;
    private String mStyle = "doc";

    public AuthorizationInformation()
    {
    }

    public AuthorizationInformation(String applicationKey, String username, String password)
    {
        mApplicationKey = applicationKey;
        mUserName = username;
        mPassword = password;
    }

    @JacksonXmlProperty(localName = "appKey")
    public String getApplicationKey()
    {
        return mApplicationKey;
    }

    public void setApplicationKey(String applicationKey)
    {
        mApplicationKey = applicationKey;
    }

    @JacksonXmlProperty(localName = "username")
    public String getUserName()
    {
        return mUserName;
    }

    public void setUserName(String userName)
    {
        mUserName = userName;
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

    @JacksonXmlProperty(localName = "version")
    public String getVersion()
    {
        return mVersion;
    }

    public void setVersion(String version)
    {
        mVersion = version;
    }

    @JacksonXmlProperty(localName = "style")
    public String getStyle()
    {
        return mStyle;
    }

    public void setStyle(String style)
    {
        mStyle = style;
    }
}
