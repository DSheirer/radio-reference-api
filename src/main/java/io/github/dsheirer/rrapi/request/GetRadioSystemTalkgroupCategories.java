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

package io.github.dsheirer.rrapi.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

public class GetRadioSystemTalkgroupCategories extends RequestBody
{
    private int mSystemId;

    public GetRadioSystemTalkgroupCategories(AuthorizationInformation authorizationInformation, int systemId)
    {
        super(authorizationInformation);
        mSystemId = systemId;
    }

    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemId)
    {
        return RequestBody.create(new GetRadioSystemTalkgroupCategories(authorizationInformation, systemId));
    }
}
