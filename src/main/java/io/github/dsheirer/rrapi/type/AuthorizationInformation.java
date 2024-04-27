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

/**
 * Authorization credentials used for authenticating to the web service for each request.
 */
@JacksonXmlRootElement(localName = "authInfo")
public class AuthorizationInformation
{
    private String mApplicationKey;
    private String mUserName;
    private String mPassword;
    private String mVersion = RadioReferenceService.API_VERSION;
    private String mStyle = "doc";

    /**
     * Constructs an instance
     */
    public AuthorizationInformation()
    {
    }

    /**
     * Constructs an instance with the required data fields
     * @param applicationKey provided by radioreference.com to each application/developer
     * @param username for the user account
     * @param password for the user account
     */
    public AuthorizationInformation(String applicationKey, String username, String password)
    {
        mApplicationKey = applicationKey;
        mUserName = username;
        mPassword = password;
    }

    /**
     * Application key provided by radioreference.com to identify the client appliation that is accessing the
     * web service.
     *
     * @return application key
     */
    @JacksonXmlProperty(localName = "appKey")
    public String getApplicationKey()
    {
        return mApplicationKey;
    }

    /**
     * Sets the application key
     * @param applicationKey to use for the request
     */
    public void setApplicationKey(String applicationKey)
    {
        mApplicationKey = applicationKey;
    }

    /**
     * User account user name
     * @return name
     */
    @JacksonXmlProperty(localName = "username")
    public String getUserName()
    {
        return mUserName;
    }

    /**
     * Sets the user account user name
     * @param userName to use
     */
    public void setUserName(String userName)
    {
        mUserName = userName;
    }

    /**
     * Password for the user account
     * @return password
     */
    @JacksonXmlProperty(localName = "password")
    public String getPassword()
    {
        return mPassword;
    }

    /**
     * Sets the password for the user account
     * @param password to use
     */
    public void setPassword(String password)
    {
        mPassword = password;
    }

    /**
     * Web Service API version to use for web service requests
     *
      * @return version (default = 15)
     */
    @JacksonXmlProperty(localName = "version")
    public String getVersion()
    {
        return mVersion;
    }

    /**
     * Sets the version of the api to use for any requests.
     *
     * Note: each instance uses a default value for the version that is compatible with this client library.  Changing
     * this value may cause the web service calls to break.
     * @param version number to use in requests
     */
    public void setVersion(String version)
    {
        mVersion = version;
    }

    /**
     * SOAP request style.
     *
     * Note: each instance uses a default style of 'doc'.  Changing or overriding this value can cause web service
     * calls to break.
     *
     * @return soap request style
     */
    @JacksonXmlProperty(localName = "style")
    public String getStyle()
    {
        return mStyle;
    }

    /**
     * Sets the SOAP request style.
     * @param style to use (default = 'doc')
     */
    public void setStyle(String style)
    {
        mStyle = style;
    }
}
