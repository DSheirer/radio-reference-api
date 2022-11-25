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
import io.github.dsheirer.rrapi.type.UserInfo;

/**
 * Get user data response
 */
public class GetUserDataResponse extends ResponseBody
{
    private UserInfo mUserInfo;

    /**
     * Constructs an instance
     */
    public GetUserDataResponse()
    {
    }

    /**
     * User info payload
     * @return user info
     */
    @JacksonXmlProperty(localName = "return")
    public UserInfo getUserInfo()
    {
        return mUserInfo;
    }

    /**
     * Sets the user info payload
     * @param userInfo payload
     */
    public void setUserInfo(UserInfo userInfo)
    {
        mUserInfo = userInfo;
    }
}
