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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;

/**
 * Request for the {@code getTag} operation: function tags.
 *
 * <p>Parameters, in the order the service expects them:</p>
 * <ul>
 *     <li>{@code id} - tag id, or empty for all tags</li>
 *     <li>{@code authInfo} - credentials, from {@link RequestBody}</li>
 * </ul>
 *
 * <p>Sent by {@link io.github.dsheirer.rrapi.RadioReferenceService#getTagsMap()}.</p>
 */
//RPC parameters are matched by position, so they are sent in WSDL order
@JsonPropertyOrder({"id", "authInfo"})
public class GetTags extends RequestBody
{
    private Integer mTagId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param tagId to request
     */
    public GetTags(AuthorizationInformation authorizationInformation, int tagId)
    {
        super(authorizationInformation);
        mTagId = tagId;
    }

    /**
     * Constructs a get ALL tags request
     * @param authorizationInformation credentials
     */
    public GetTags(AuthorizationInformation authorizationInformation)
    {
        super(authorizationInformation);
    }

    /**
     * Tag ID
     * @return id
     */
    @JacksonXmlProperty(localName = "id")
    public Integer getTagId()
    {
        return mTagId;
    }

    /**
     * Set tag ID
     * @param tagId to query
     */
    public void setTagId(int tagId)
    {
        mTagId = tagId;
    }

    /**
     * Creates a get specific tag request
     * @param authorizationInformation credentials
     * @param tagId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int tagId)
    {
        return RequestBody.create(new GetTags(authorizationInformation, tagId));
    }

    /**
     * Creates a get ALL tags request
     * @param authorizationInformation credentials
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation)
    {
        return RequestBody.create(new GetTags(authorizationInformation));
    }
}
