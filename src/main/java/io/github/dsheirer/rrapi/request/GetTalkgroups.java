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
import io.github.dsheirer.rrapi.type.TalkgroupRequestFilter;

/**
 * Get talkgroups request
 */
@JsonPropertyOrder({"sid", "tgCid", "tgTag", "tgDec"})
public class GetTalkgroups extends RequestBody
{
    private int mSystemId;
    private Integer mCategoryId;
    private Integer mTagId;
    private Integer mDecimalValue;

    /**
     * Constructs an instance
     * @param authorizationInformation for the requester
     * @param systemId to request
     */
    public GetTalkgroups(AuthorizationInformation authorizationInformation, int systemId)
    {
        super(authorizationInformation);
        mSystemId = systemId;
    }

    /**
     * Requested system ID
     * @return system ID
     */
    @JacksonXmlProperty(localName = "sid")
    public int getSystemId()
    {
        return mSystemId;
    }

    /**
     * Sets the requested system ID
     * @param systemId to set
     */
    public void setSystemId(int systemId)
    {
        mSystemId = systemId;
    }

    /**
     * Category ID for the request
     * @return category ID
     */
    @JacksonXmlProperty(localName = "tgCid")
    public Integer getCategoryId()
    {
        return mCategoryId;
    }

    /**
     * Sets the category ID
     * @param categoryId to set
     */
    public void setCategoryId(Integer categoryId)
    {
        mCategoryId = categoryId;
    }

    /**
     * Tag ID for the request
     * @return value
     */
    @JacksonXmlProperty(localName = "tgTag")
    public Integer getTagId()
    {
        return mTagId;
    }

    /**
     * Tag ID for the request
     * @param tagId to set
     */
    public void setTagId(Integer tagId)
    {
        mTagId = tagId;
    }

    /**
     * Decimal value for the talkgroup
     * @return value
     */
    @JacksonXmlProperty(localName = "tgDec")
    public Integer getDecimalValue()
    {
        return mDecimalValue;
    }

    /**
     * Sets the requested talkgroup decimal value
     * @param decimalValue of the talkgorup
     */
    public void setDecimalValue(Integer decimalValue)
    {
        mDecimalValue = decimalValue;
    }


    /**
     * Creates a request to get all talkgroups for the specified system
     * @param authorizationInformation to use
     * @param systemId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int systemId)
    {
        return RequestBody.create(new GetTalkgroups(authorizationInformation, systemId));
    }

    /**
     * Creates a request using the specified filter
     * @param authorizationInformation to use
     * @param filter with filter values
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, TalkgroupRequestFilter filter)
    {
        GetTalkgroups request = new GetTalkgroups(authorizationInformation, filter.getSystemId());
        request.setCategoryId(filter.getCategoryId());
        request.setDecimalValue(filter.getDecimalValue());
        request.setTagId(filter.getTagId());
        return RequestBody.create(request);
    }
}
