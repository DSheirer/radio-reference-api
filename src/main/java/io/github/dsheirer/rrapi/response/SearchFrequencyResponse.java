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

package io.github.dsheirer.rrapi.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.github.dsheirer.rrapi.type.SearchFrequencyResult;
import io.github.dsheirer.rrapi.type.Voice;

import java.util.ArrayList;
import java.util.List;

/**
 * Response body of the {@code searchCountyFreq}, {@code searchStateFreq} and {@code searchMetroFreq} operations, with a
 * list of {@link SearchFrequencyResult} in the {@code return} element.
 *
 * <p>Read by {@link io.github.dsheirer.rrapi.RadioReferenceService#searchCountyFrequencies(int, double)}, {@link
 * io.github.dsheirer.rrapi.RadioReferenceService#searchStateFrequencies(int, double)} and {@link
 * io.github.dsheirer.rrapi.RadioReferenceService#searchMetroFrequencies(int, double)}.</p>
 */
public class SearchFrequencyResponse extends ResponseBody
{
    private List<SearchFrequencyResult> mResults = new ArrayList<>();

    /**
     * Constructs an instance
     */
    public SearchFrequencyResponse()
    {
    }

    /**
     * Results
     * @return results
     */
    @JacksonXmlProperty(localName = "return")
    public List<SearchFrequencyResult> getResults()
    {
        return mResults;
    }

    /**
     * Sets the results
     * @param results to set
     */
    public void setResults(List<SearchFrequencyResult> results)
    {
        mResults = results;
    }
}
