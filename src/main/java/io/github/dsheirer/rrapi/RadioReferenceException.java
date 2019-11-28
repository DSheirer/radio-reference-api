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

package io.github.dsheirer.rrapi;

import io.github.dsheirer.rrapi.response.Fault;

public class RadioReferenceException extends Exception
{
    private Integer mHttpStatusCode;
    private Fault mFault;

    public RadioReferenceException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    public RadioReferenceException(String message)
    {
        super(message);
    }

    public RadioReferenceException(String message, int httpStatusCode, Fault fault)
    {
        super(message);
        mHttpStatusCode = httpStatusCode;
        mFault = fault;
    }

    public Integer getHttpStatusCode()
    {
        return mHttpStatusCode;
    }

    public boolean hasHttpStatusCode()
    {
        return mHttpStatusCode != null;
    }

    public Fault getFault()
    {
        return mFault;
    }

    public boolean hasFault()
    {
        return mFault != null;
    }
}
