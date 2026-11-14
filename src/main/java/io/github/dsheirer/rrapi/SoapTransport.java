/*
 * *****************************************************************************
 * Copyright (C) 2026 Dennis Sheirer
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

import java.io.IOException;
import java.time.Duration;

/**
 * Sends a SOAP request envelope and returns the HTTP response.  Package-private so that tests can replace the HTTP
 * transport with canned responses.
 */
@FunctionalInterface
interface SoapTransport
{
    /**
     * HTTP status code and body of a response
     * @param statusCode HTTP status code
     * @param body of the response
     * @param retryAfter delay from the Retry-After header, or null if the response doesn't have one
     */
    record Response(int statusCode, String body, Duration retryAfter)
    {
        Response(int statusCode, String body)
        {
            this(statusCode, body, null);
        }
    }

    /**
     * Posts the request envelope XML to the web service.
     *
     * @param requestXml SOAP request envelope
     * @return response
     * @throws IOException if there is an error submitting the request
     * @throws InterruptedException if interrupted while waiting for the response
     */
    Response post(String requestXml) throws IOException, InterruptedException;
}
