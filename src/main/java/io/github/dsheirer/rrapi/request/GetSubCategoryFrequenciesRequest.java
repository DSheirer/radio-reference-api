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
 * Sub-Category Frequencies query
 */
public class GetSubCategoryFrequenciesRequest extends RequestBody
{
    private Integer mSubCategoryId;

    /**
     * Constructs the get tag request
     * @param authorizationInformation for the user
     * @param subCategoryId to request
     */
    public GetSubCategoryFrequenciesRequest(AuthorizationInformation authorizationInformation, int subCategoryId)
    {
        super(authorizationInformation);
        mSubCategoryId = subCategoryId;
    }

    /**
     * Sub-Category ID
     * @return id
     */
    @JacksonXmlProperty(localName = "request")
    public Integer getSubCategoryId()
    {
        return mSubCategoryId;
    }

    /**
     * Sets the sytem flavor id
     * @param subCategoryId id
     */
    public void setSubCategoryId(int subCategoryId)
    {
        mSubCategoryId = subCategoryId;
    }

    /**
     * Creates a get specific tag request
     * @param authorizationInformation credentials
     * @param subCategoryId to query
     * @return request
     */
    public static RequestEnvelope create(AuthorizationInformation authorizationInformation, int subCategoryId)
    {
        return RequestBody.create(new GetSubCategoryFrequenciesRequest(authorizationInformation, subCategoryId));
    }
}
