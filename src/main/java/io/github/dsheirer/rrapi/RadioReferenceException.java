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

/**
 * Radio reference API exception
 */
public class RadioReferenceException extends Exception
{
    /**
     * HTTP status code
     */
    private Integer mHttpStatusCode;

    /**
     * Fault
     */
    private Fault mFault;

    /**
     * Constructs an instance
     * @param message to report
     * @param throwable cause
     */
    public RadioReferenceException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    /**
     * Constructs an instance
     * @param message to report
     */
    public RadioReferenceException(String message)
    {
        super(message);
    }

    /**
     * Constructs an instance
     * @param message to report
     * @param httpStatusCode for the request
     * @param fault being reported
     */
    public RadioReferenceException(String message, int httpStatusCode, Fault fault)
    {
        super(message);
        mHttpStatusCode = httpStatusCode;
        mFault = fault;
    }

    /**
     * HTTP status code
     * @return status code
     */
    public Integer getHttpStatusCode()
    {
        return mHttpStatusCode;
    }

    /**
     * Indicates if this exception has an HTTP status code
     * @return true if non-null
     */
    public boolean hasHttpStatusCode()
    {
        return mHttpStatusCode != null;
    }

    /**
     * SOAP fault
     * @return fault
     */
    public Fault getFault()
    {
        return mFault;
    }

    /**
     * Indicates if this exception contains a SOAP fault
     * @return true if non-null
     */
    public boolean hasFault()
    {
        return mFault != null;
    }
}
