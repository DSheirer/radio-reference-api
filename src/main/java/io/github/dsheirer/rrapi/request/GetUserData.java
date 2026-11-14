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

import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Request for the {@code getUserData} operation: account details of the authenticated user.
 *
 * <p>The only parameter is {@code authInfo}, the credentials from {@link RequestBody}.</p>
 *
 * <p>Sent by {@link io.github.dsheirer.rrapi.RadioReferenceService#getUserInfo()}.</p>
 */
public class GetUserData extends RequestBody
{
    /**
     * Constructs an instance
     * @param authorizationInformation for the requester
     */
    public GetUserData(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Creates a request envelope
     * @param authorizationInformation for the requester
     * @return envelope
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetUserData(authorizationInformation));
    }
}
