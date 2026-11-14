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

package io.github.dsheirer.rrapi.type;

import java.math.BigDecimal;

/**
 * Converts between the MHz values used by the web service and whole Hz.
 *
 * The service uses decimal MHz values like 460.0375, which a double can't represent exactly.  Truncating the product
 * (long)(mhz * 1E6) can be 1 Hz low, so conversions round to the nearest Hz.
 */
public final class FrequencyConversion
{
    private static final double HERTZ_PER_MEGAHERTZ = 1_000_000.0;

    private FrequencyConversion()
    {
    }

    /**
     * Converts MHz to Hz, rounded to the nearest Hz
     * @param megahertz frequency
     * @return frequency in Hz, or 0 if the value is not finite
     */
    public static long toHertz(double megahertz)
    {
        return Double.isFinite(megahertz) ? Math.round(megahertz * HERTZ_PER_MEGAHERTZ) : 0;
    }

    /**
     * Converts Hz to MHz
     * @param hertz frequency
     * @return frequency in MHz
     */
    public static double toMegahertz(long hertz)
    {
        return hertz / HERTZ_PER_MEGAHERTZ;
    }

    /**
     * Formats MHz as the service expects it, rounded to the nearest Hz without floating point artifacts, for example
     * 460.0375 or 851.0
     * @param megahertz frequency
     * @return decimal text
     */
    public static String toMegahertzText(double megahertz)
    {
        String text = BigDecimal.valueOf(toHertz(megahertz)).movePointLeft(6).stripTrailingZeros().toPlainString();
        return text.contains(".") ? text : text + ".0";
    }
}
