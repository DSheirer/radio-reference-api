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

public class RadioSystemId
{
    private String mSystemId;
    private String mCt;
    private String mWacn;

    public RadioSystemId()
    {
    }

    @JacksonXmlProperty(localName = "sysid")
    public String getSystemId()
    {
        return mSystemId;
    }

    public void setSystemId(String systemId)
    {
        mSystemId = systemId;
    }

    @JacksonXmlProperty(localName = "ct")
    public String getCt()
    {
        return mCt;
    }

    public void setCt(String ct)
    {
        mCt = ct;
    }

    @JacksonXmlProperty(localName = "wacn")
    public String getWacn()
    {
        return mWacn;
    }

    public void setWacn(String wacn)
    {
        mWacn = wacn;
    }
}
