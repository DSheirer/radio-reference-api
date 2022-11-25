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
 * Fleetmap configuration details for legacy Motorola systems
 */
public class Fleetmap
{
    private String mB0;
    private String mB1;
    private String mB2;
    private String mB3;
    private String mB4;
    private String mB5;
    private String mB6;
    private String mB7;

    /**
     * Constructs a fleetmap instance
     */
    public Fleetmap()
    {
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b0")
    public String getB0()
    {
        return mB0;
    }

    /**
     * Sets the fleetmap element
     * @param b0 to set
     */
    public void setB0(String b0)
    {
        mB0 = b0;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b1")
    public String getB1()
    {
        return mB1;
    }

    /**
     * Sets the fleetmap element
     * @param b1 to set
     */
    public void setB1(String b1)
    {
        mB1 = b1;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b2")
    public String getB2()
    {
        return mB2;
    }

    /**
     * Sets the fleetmap element
     * @param b2 to set
     */
    public void setB2(String b2)
    {
        mB2 = b2;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b3")
    public String getB3()
    {
        return mB3;
    }

    /**
     * Sets the fleetmap element
     * @param b3 to set
     */
    public void setB3(String b3)
    {
        mB3 = b3;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b4")
    public String getB4()
    {
        return mB4;
    }

    /**
     * Sets the fleetmap element
     * @param b4 to set
     */
    public void setB4(String b4)
    {
        mB4 = b4;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b5")
    public String getB5()
    {
        return mB5;
    }

    /**
     * Sets the fleetmap element
     * @param b5 to set
     */
    public void setB5(String b5)
    {
        mB5 = b5;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b6")
    public String getB6()
    {
        return mB6;
    }

    /**
     * Sets the fleetmap element
     * @param b6 to set
     */
    public void setB6(String b6)
    {
        mB6 = b6;
    }

    /**
     * Fleetmap element
     * @return element
     */
    @JacksonXmlProperty(localName = "b7")
    public String getB7()
    {
        return mB7;
    }

    /**
     * Sets the fleetmap element
     * @param b7 to set
     */
    public void setB7(String b7)
    {
        mB7 = b7;
    }
}
