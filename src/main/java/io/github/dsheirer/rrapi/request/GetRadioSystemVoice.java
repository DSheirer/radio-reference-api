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

/**
 * Radio System Voice information
 */
public class GetRadioSystemVoice extends RequestBody
{
    private Integer mSystemVoiceId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param systemVoiceId to request
     */
    public GetRadioSystemVoice(AuthorizationInformation authorizationInformation, int systemVoiceId)
    {
        super(authorizationInformation);
        mSystemVoiceId = systemVoiceId;
    }

    /**
     * Constructs a get ALL tags request
     * @param authorizationInformation credentials
     */
    public GetRadioSystemVoice(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * System voice id
     * @return id
     */
    @JacksonXmlProperty(localName = "request")
    public Integer getSystemVoiceId()
    {
        return mSystemVoiceId;
    }

    /**
     * Sets the system voice id
     * @param systemVoiceId to query
     */
    public void setSystemVoiceId(int systemVoiceId)
    {
        mSystemVoiceId = systemVoiceId;
    }

    /**
     * Creates a get specific voice request
     * @param authorizationInformation credentials
     * @param systemVoiceId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemVoiceId)
    {
        return RequestBody.create(new GetRadioSystemVoice(authorizationInformation, systemVoiceId));
    }

    /**
     * Creates a get ALL voices request
     * @param authorizationInformation credentials
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetRadioSystemVoice(authorizationInformation));
    }
}
