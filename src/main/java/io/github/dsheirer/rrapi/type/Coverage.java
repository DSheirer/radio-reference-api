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

import java.util.List;
import java.util.Objects;

/**
 * Coverage area of a database entity (county, agency, frequency subcategory, trunked system or site, talkgroup
 * category): a center point with a coverage radius, and optional rectangles that outline the area more closely.
 *
 * The service doesn't document the unit of the range.  It is a radius in miles: county ranges only cover their
 * counties as miles, e.g. Albany NY has range 20 (a 1,257 square mile circle for a 533 square mile county, where 20
 * kilometers would be a 485 square mile circle), and St. Lawrence NY has range 40 for 2,821 square miles.
 *
 * The service uses latitude and longitude 0 when an entity has no location; see {@link #hasLocation()}.
 *
 * @param latitude of the center in decimal degrees
 * @param longitude of the center in decimal degrees
 * @param rangeMiles coverage radius in miles, 0 if not set
 * @param rectangles outlining the coverage area, empty if not set
 */
public record Coverage(double latitude, double longitude, double rangeMiles, List<Rectangle> rectangles)
{
    public static final double KILOMETERS_PER_MILE = 1.609344;
    private static final double EARTH_MEAN_RADIUS_KILOMETERS = 6371.0088;

    public Coverage
    {
        rectangles = rectangles == null ? List.of() : rectangles.stream().filter(Objects::nonNull).toList();
    }

    /**
     * Indicates if the center point is set: valid coordinates other than 0, 0.
     */
    public boolean hasLocation()
    {
        return Double.isFinite(latitude) && Double.isFinite(longitude) &&
            Math.abs(latitude) <= 90 && Math.abs(longitude) <= 180 && !(latitude == 0 && longitude == 0);
    }

    /**
     * Coverage radius in kilometers, 0 if not set
     */
    public double rangeKilometers()
    {
        return rangeMiles > 0 ? rangeMiles * KILOMETERS_PER_MILE : 0;
    }

    /**
     * Great circle distance from the center to the location
     * @return kilometers, or NaN if the center is not set
     */
    public double distanceKilometers(double latitude, double longitude)
    {
        return hasLocation() ? distanceKilometers(this.latitude, this.longitude, latitude, longitude) : Double.NaN;
    }

    /**
     * Distance from the location to the edge of the coverage area: 0 inside the area.  Uses the rectangles when set,
     * otherwise the circle around the center.
     * @return kilometers, or NaN if neither the rectangles nor the center are set
     */
    public double distanceToCoverageKilometers(double latitude, double longitude)
    {
        if(!rectangles.isEmpty() && contains(latitude, longitude))
        {
            return 0;
        }

        if(!hasLocation())
        {
            return Double.NaN;
        }

        return Math.max(0, distanceKilometers(latitude, longitude) - rangeKilometers());
    }

    /**
     * Indicates if the location is inside the coverage area: inside one of the rectangles when they are set, otherwise
     * within the range of the center.
     */
    public boolean contains(double latitude, double longitude)
    {
        if(!rectangles.isEmpty())
        {
            return rectangles.stream().anyMatch(rectangle -> rectangle.contains(latitude, longitude));
        }

        return hasLocation() && rangeMiles > 0 && distanceKilometers(latitude, longitude) <= rangeKilometers();
    }

    /**
     * Great circle (haversine) distance between two locations
     * @return kilometers
     */
    public static double distanceKilometers(double latitude1, double longitude1, double latitude2, double longitude2)
    {
        double phi1 = Math.toRadians(latitude1);
        double phi2 = Math.toRadians(latitude2);
        double sinHalfDeltaPhi = Math.sin(Math.toRadians(latitude2 - latitude1) / 2);
        double sinHalfDeltaLambda = Math.sin(Math.toRadians(longitude2 - longitude1) / 2);
        double a = sinHalfDeltaPhi * sinHalfDeltaPhi + Math.cos(phi1) * Math.cos(phi2) * sinHalfDeltaLambda * sinHalfDeltaLambda;
        return 2 * EARTH_MEAN_RADIUS_KILOMETERS * Math.asin(Math.min(1, Math.sqrt(a)));
    }
}
