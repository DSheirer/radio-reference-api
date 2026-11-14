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

import io.github.dsheirer.rrapi.type.CountyInfo;
import io.github.dsheirer.rrapi.type.Coverage;
import io.github.dsheirer.rrapi.type.Rectangle;
import io.github.dsheirer.rrapi.type.Site;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoverageTest
{
    private static final double SYRACUSE_LAT = 43.0481;
    private static final double SYRACUSE_LON = -76.1474;
    private static final double ALBANY_LAT = 42.6526;
    private static final double ALBANY_LON = -73.7562;

    private static Rectangle rectangle(double northWestLat, double northWestLon, double southEastLat, double southEastLon)
    {
        Rectangle rectangle = new Rectangle();
        rectangle.setNorthWestLatitude(northWestLat);
        rectangle.setNorthWestLongitude(northWestLon);
        rectangle.setSouthEastLatitude(southEastLat);
        rectangle.setSouthEastLongitude(southEastLon);
        return rectangle;
    }

    @Test
    void greatCircleDistance()
    {
        Assertions.assertEquals(343.56, Coverage.distanceKilometers(51.5074, -0.1278, 48.8566, 2.3522), 0.05, "London to Paris");
        Assertions.assertEquals(199.82, Coverage.distanceKilometers(SYRACUSE_LAT, SYRACUSE_LON, ALBANY_LAT, ALBANY_LON), 0.05,
            "Syracuse to Albany");
        Assertions.assertEquals(0.0, Coverage.distanceKilometers(ALBANY_LAT, ALBANY_LON, ALBANY_LAT, ALBANY_LON), 1e-9);
        Assertions.assertEquals(Math.PI * 6371.0088, Coverage.distanceKilometers(0, 0, 0, 180), 1e-6, "antipodes");
    }

    @Test
    void rangeIsMiles()
    {
        Coverage coverage = new Coverage(ALBANY_LAT, ALBANY_LON, 20, null);
        Assertions.assertEquals(32.18688, coverage.rangeKilometers(), 1e-9);
        Assertions.assertEquals(0, new Coverage(ALBANY_LAT, ALBANY_LON, -1, null).rangeKilometers());
    }

    @Test
    void circleCoverage()
    {
        Coverage albany = new Coverage(ALBANY_LAT, ALBANY_LON, 20, List.of());

        Assertions.assertTrue(albany.hasLocation());
        Assertions.assertTrue(albany.contains(42.75, -73.80), "inside 20 miles");
        Assertions.assertFalse(albany.contains(SYRACUSE_LAT, SYRACUSE_LON), "Syracuse is 124 miles away");
        Assertions.assertEquals(0, albany.distanceToCoverageKilometers(42.75, -73.80));
        Assertions.assertEquals(199.82 - 32.18688, albany.distanceToCoverageKilometers(SYRACUSE_LAT, SYRACUSE_LON), 0.05);
    }

    @Test
    void zeroLocationMeansUnknown()
    {
        Coverage unknown = new Coverage(0, 0, 0, null);

        Assertions.assertFalse(unknown.hasLocation());
        Assertions.assertTrue(Double.isNaN(unknown.distanceKilometers(ALBANY_LAT, ALBANY_LON)));
        Assertions.assertTrue(Double.isNaN(unknown.distanceToCoverageKilometers(ALBANY_LAT, ALBANY_LON)));
        Assertions.assertFalse(unknown.contains(0, 0));
        Assertions.assertFalse(new Coverage(91, 0, 10, null).hasLocation(), "invalid latitude");
        Assertions.assertFalse(new Coverage(Double.NaN, 1, 10, null).hasLocation(), "not a number");
    }

    @Test
    void rectanglesOutlineTheArea()
    {
        //A narrow north-south strip east of Albany: the 20 mile circle covers points the strip doesn't
        Rectangle strip = rectangle(43.0, -73.70, 42.30, -73.60);
        Coverage coverage = new Coverage(ALBANY_LAT, ALBANY_LON, 20, List.of(strip));

        Assertions.assertTrue(coverage.contains(42.65, -73.65), "inside the rectangle");
        Assertions.assertFalse(coverage.contains(42.65, -73.80), "inside the circle but outside the rectangle");
        Assertions.assertEquals(0, coverage.distanceToCoverageKilometers(42.65, -73.65));
    }

    @Test
    void rectanglesWithoutCenter()
    {
        Coverage coverage = new Coverage(0, 0, 0, List.of(rectangle(43.0, -74.0, 42.0, -73.0)));

        Assertions.assertFalse(coverage.hasLocation());
        Assertions.assertTrue(coverage.contains(42.5, -73.5));
        Assertions.assertEquals(0, coverage.distanceToCoverageKilometers(42.5, -73.5));
        Assertions.assertTrue(Double.isNaN(coverage.distanceToCoverageKilometers(40.0, -73.5)), "outside, no center");
    }

    @Test
    void rectangleContainsEdgesAndHandlesTheAntimeridian()
    {
        Rectangle rectangle = rectangle(43.0, -74.0, 42.0, -73.0);
        Assertions.assertTrue(rectangle.contains(43.0, -74.0), "north west corner");
        Assertions.assertTrue(rectangle.contains(42.0, -73.0), "south east corner");
        Assertions.assertFalse(rectangle.contains(43.1, -73.5), "north of the rectangle");
        Assertions.assertFalse(rectangle.contains(42.5, -72.9), "east of the rectangle");

        Rectangle aleutians = rectangle(53.0, 172.0, 51.0, -178.0);
        Assertions.assertTrue(aleutians.contains(52.0, 179.5));
        Assertions.assertTrue(aleutians.contains(52.0, -179.5));
        Assertions.assertFalse(aleutians.contains(52.0, 0.0));
    }

    @Test
    void typesProvideTheirCoverage()
    {
        CountyInfo county = new CountyInfo();
        county.setLatitude(42.61);
        county.setLongitude(-73.99);
        county.setRange(20);
        county.setRectangles(List.of(rectangle(42.8, -74.3, 42.4, -73.7)));

        Coverage coverage = county.getCoverage();
        Assertions.assertEquals(new Coverage(42.61, -73.99, 20, county.getRectangles()), coverage);
        Assertions.assertTrue(coverage.contains(42.65, -73.75));

        Site site = new Site();
        Assertions.assertFalse(site.getCoverage().hasLocation(), "a site without a location");
    }
}
