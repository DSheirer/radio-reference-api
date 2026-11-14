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

package io.github.dsheirer.rrapi.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

/**
 * SOAP encoded array request parameter: &lt;request SOAP-ENC:arrayType="tns:stid[3]"&gt;&lt;item&gt;...&lt;/item&gt;...
 *
 * The service only decodes the parameter as an array when it has the arrayType attribute.  Without it, the service
 * decodes the items as a single struct and ignores the requested ids.
 *
 * @param <T> item type
 */
public class RequestArray<T>
{
    /**
     * XML namespace of the SOAP encoding, for the arrayType attribute
     */
    public static final String SOAP_ENCODING_NAMESPACE = "http://schemas.xmlsoap.org/soap/encoding/";

    private final String mItemType;
    private final List<T> mItems;

    /**
     * Constructs an instance
     * @param itemType WSDL type of the items, for example tns:stid
     * @param items of the array
     */
    public RequestArray(String itemType, List<T> items)
    {
        mItemType = itemType;
        mItems = items;
    }

    /**
     * SOAP encoding array type with the item count, for example tns:stid[3]
     * @return array type
     */
    @JacksonXmlProperty(isAttribute = true, namespace = SOAP_ENCODING_NAMESPACE, localName = "arrayType")
    public String getArrayType()
    {
        return mItemType + "[" + mItems.size() + "]";
    }

    /**
     * Items of the array
     * @return items
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "item")
    public List<T> getItems()
    {
        return mItems;
    }
}
