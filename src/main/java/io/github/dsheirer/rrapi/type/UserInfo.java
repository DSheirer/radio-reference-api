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
 * User account information
 */
public class UserInfo
{
    private String mUserName;
    private String mExpirationDate;

    /**
     * Constructs an instance of user information
     */
    public UserInfo()
    {
    }

    /**
     * User name
     * @return user name
     */
    @JacksonXmlProperty(localName = "username")
    public String getUserName()
    {
        return mUserName;
    }

    public void setUserName(String userName)
    {
        mUserName = userName;
    }

    /**
     * Expiration date for the account
     * @return expiration date
     */
    @JacksonXmlProperty(localName = "subExpireDate")
    public String getExpirationDate()
    {
        return mExpirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        mExpirationDate = expirationDate;
    }

}
