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

package io.github.dsheirer.rrapi;

import io.github.dsheirer.rrapi.request.SearchCountyFrequency;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.Frequency;
import io.github.dsheirer.rrapi.type.FrequencyConversion;
import io.github.dsheirer.rrapi.type.SearchFrequencyResult;
import io.github.dsheirer.rrapi.type.SiteFrequency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FrequencyConversionTest
{
    @Test
    void roundsToNearestHertz()
    {
        Assertions.assertEquals(460_037_500L, FrequencyConversion.toHertz(460.0375));
        Assertions.assertEquals(851_012_500L, FrequencyConversion.toHertz(851.0125));
        Assertions.assertEquals(154_445_000L, FrequencyConversion.toHertz(154.445));
        Assertions.assertEquals(0L, FrequencyConversion.toHertz(0.0));
        Assertions.assertEquals(0L, FrequencyConversion.toHertz(Double.NaN));
    }

    /**
     * Truncating, (long)(mhz * 1E6), is 1 Hz low for some channels, e.g. 32.01 MHz becomes 32,009,999 Hz.  Rounding
     * recovers every channel.  hz / 1E6 is the same double that parsing the decimal text from a response produces.
     */
    @Test
    void roundingFixesTruncationError()
    {
        int truncationErrors = 0;
        long[][] bands = {{30_000_000L, 50_000_000L, 5_000L}, {136_000_000L, 174_000_000L, 2_500L},
            {380_000_000L, 512_000_000L, 6_250L}, {764_000_000L, 870_000_000L, 6_250L}};

        for(long[] band: bands)
        {
            for(long hz = band[0]; hz < band[1]; hz += band[2])
            {
                double mhz = hz / 1E6;

                if((long)(mhz * 1E6) != hz)
                {
                    truncationErrors++;
                }

                Assertions.assertEquals(hz, FrequencyConversion.toHertz(mhz), "round trip of " + hz);
            }
        }

        Assertions.assertEquals(32_009_999L, (long)(32.01 * 1E6), "truncation example");
        Assertions.assertEquals(32_010_000L, FrequencyConversion.toHertz(32.01));
        Assertions.assertTrue(truncationErrors > 0, "truncation is wrong for some channels, which is why this exists");
    }

    @Test
    void formatsMegahertzWithoutFloatingPointArtifacts()
    {
        Assertions.assertEquals("460.0375", FrequencyConversion.toMegahertzText(460.0375));
        Assertions.assertEquals("460.0375", FrequencyConversion.toMegahertzText(460.03749999999997));
        Assertions.assertEquals("460.0375", FrequencyConversion.toMegahertzText(460.0 + 0.0375));
        Assertions.assertEquals("851.0", FrequencyConversion.toMegahertzText(851.0));
        Assertions.assertEquals("851.00625", FrequencyConversion.toMegahertzText(851_006_250L / 1E6));
    }

    @Test
    void searchRequestSendsFrequencyRoundedToHertz() throws Exception
    {
        String xml = SearchCountyFrequency.create(new AuthorizationInformation("key", "user", "password"), 1,
            460.03749999999997, null).toXmlString();

        Assertions.assertTrue(xml.contains("<freq>460.0375</freq>"), xml);
    }

    @Test
    void typesProvideHertzValues()
    {
        Frequency frequency = new Frequency();
        frequency.setDownlink(460.0375);
        frequency.setUplink(465.0375);
        Assertions.assertEquals(460_037_500L, frequency.getDownlinkHz());
        Assertions.assertEquals(465_037_500L, frequency.getUplinkHz());

        SearchFrequencyResult result = new SearchFrequencyResult();
        result.setDownlink(851.0125);
        Assertions.assertEquals(851_012_500L, result.getDownlinkHz());
        Assertions.assertEquals(0L, result.getUplinkHz());

        SiteFrequency siteFrequency = new SiteFrequency();
        siteFrequency.setFrequency(851.00625);
        Assertions.assertEquals(851_006_250L, siteFrequency.getFrequencyHz());
    }
}
