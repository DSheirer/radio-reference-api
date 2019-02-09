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

package io.github.dsheirer.rrapi.type;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;

public class Category
{
    private int mCategoryId;
    private String mName;
    private List<SubCategory> mSubCategories = new ArrayList<>();

    public Category()
    {
    }

    @JacksonXmlProperty(localName = "cid")
    public int getCategoryId()
    {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId)
    {
        mCategoryId = categoryId;
    }

    @JacksonXmlProperty(localName = "cName")
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @JacksonXmlProperty(localName = "subcats")
    public List<SubCategory> getSubCategories()
    {
        return mSubCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories)
    {
        mSubCategories = subCategories;
    }
}
