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
 * Radio Network information
 */
public class RadioNetwork
{
    private String mSystemId;
    private String mCt;
    private String mWacn;
    private String mModel;

    /**
     * Constructs an instance
     */
    public RadioNetwork()
    {
    }

    /**
     * System identifier
     * @return id
     */
    @JacksonXmlProperty(localName = "sysid")
    public String getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the system identifier
     * @param systemId value
     */
    public void setSystemId(String systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Connect tone?
     * @return value
     */
    @JacksonXmlProperty(localName = "ct")
    public String getCt()
    {
        return mCt;
    }

    /**
     * Sets the connect tone?
     * @param ct value
     */
    public void setCt(String ct)
    {
        mCt = ct;
    }

    /**
     * WACN value
     * @return wacn
     */
    @JacksonXmlProperty(localName = "wacn")
    public String getWacn()
    {
        return mWacn;
    }

    /**
     * Sets the WACN value
     * @param wacn to set
     */
    public void setWacn(String wacn)
    {
        mWacn = wacn;
    }

    /**
     * Model
     * @return model value
     */
    @JacksonXmlProperty(localName = "model")
    public String getModel()
    {
        return mModel;
    }

    /**
     * Sets the model
     * @param model value
     */
    public void setModel(String model)
    {
        mModel = model;
    }
}
