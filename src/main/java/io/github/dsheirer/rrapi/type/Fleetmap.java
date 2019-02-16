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


    @JacksonXmlProperty(localName = "b0")
    public String getB0()
    {
        return mB0;
    }

    public void setB0(String b0)
    {
        mB0 = b0;
    }

    @JacksonXmlProperty(localName = "b1")
    public String getB1()
    {
        return mB1;
    }

    public void setB1(String b1)
    {
        mB1 = b1;
    }

    @JacksonXmlProperty(localName = "b2")
    public String getB2()
    {
        return mB2;
    }

    public void setB2(String b2)
    {
        mB2 = b2;
    }

    @JacksonXmlProperty(localName = "b3")
    public String getB3()
    {
        return mB3;
    }

    public void setB3(String b3)
    {
        mB3 = b3;
    }

    @JacksonXmlProperty(localName = "b4")
    public String getB4()
    {
        return mB4;
    }

    public void setB4(String b4)
    {
        mB4 = b4;
    }

    @JacksonXmlProperty(localName = "b5")
    public String getB5()
    {
        return mB5;
    }

    public void setB5(String b5)
    {
        mB5 = b5;
    }

    @JacksonXmlProperty(localName = "b6")
    public String getB6()
    {
        return mB6;
    }

    public void setB6(String b6)
    {
        mB6 = b6;
    }

    @JacksonXmlProperty(localName = "b7")
    public String getB7()
    {
        return mB7;
    }

    public void setB7(String b7)
    {
        mB7 = b7;
    }
}
