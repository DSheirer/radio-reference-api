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
 * Trunked System Voice information
 */
public class GetTrunkedSystemVoice extends RequestBody
{
    private Integer mSystemVoiceId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param stateIds to request
     */
    public GetTrunkedSystemVoice(AuthorizationInformation authorizationInformation, int systemVoiceId)
    {
        super(authorizationInformation);
        mSystemVoiceId = systemVoiceId;
    }

    /**
     * Constructs a get ALL tags request
     * @param authorizationInformation
     */
    public GetTrunkedSystemVoice(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    @JacksonXmlProperty(localName = "request")
    public Integer getSystemVoiceId()
    {
        return mSystemVoiceId;
    }

    public void setSystemVoiceId(int systemVoiceId)
    {
        mSystemVoiceId = systemVoiceId;
    }

    /**
     * Creates a get specific tag request
     * @param authorizationInformation
     * @param systemVoiceId
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemVoiceId)
    {
        return RequestBody.create(new GetTrunkedSystemVoice(authorizationInformation, systemVoiceId));
    }

    /**
     * Creates a get ALL tags request
     * @param authorizationInformation
     * @return
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetTrunkedSystemVoice(authorizationInformation));
    }
}
