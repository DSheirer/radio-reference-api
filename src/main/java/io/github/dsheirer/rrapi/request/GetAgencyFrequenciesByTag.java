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
 * Agency frequencies by tag
 */
public class GetAgencyFrequenciesByTag extends RequestBody
{
    private int mAgencyId;
    private int mTagId;

    /**
     * Constructs the request
     * @param authorizationInformation for the user
     * @param agencyId to request
     * @param tagId to query
     */
    public GetAgencyFrequenciesByTag(AuthorizationInformation authorizationInformation, int agencyId, int tagId)
    {
        super(authorizationInformation);
        mAgencyId = agencyId;
        mTagId = tagId;
    }

    /**
     * Agency id
     * @return id
     */
    @JacksonXmlProperty(localName = "aid")
    public int getAgencyId()
    {
        return mAgencyId;
    }

    /**
     * Sets the agency id
     * @param agencyId to query
     */
    public void setAgencyId(int agencyId)
    {
        mAgencyId = agencyId;
    }

    /**
     * Tag Id
     * @return id
     */
    @JacksonXmlProperty(localName = "tag")
    public int getTagId()
    {
        return mTagId;
    }

    /**
     * Sets the tag ID
     * @param tagId to query
     */
    public void setTagId(int tagId)
    {
        mTagId = tagId;
    }

    /**
     * Creates the request envelope
     * @param authorizationInformation credentials
     * @param agencyId to query
     * @param tagId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int agencyId, int tagId)
    {
        return RequestBody.create(new GetAgencyFrequenciesByTag(authorizationInformation, agencyId, tagId));
    }
}
