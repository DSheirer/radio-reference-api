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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.dsheirer.rrapi.response.ResponseEnvelope;
import io.github.dsheirer.rrapi.type.Country;
import io.github.dsheirer.rrapi.type.CountyInfo;
import io.github.dsheirer.rrapi.type.Frequency;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.io.TempDir;

/**
 * Every value returned by every operation survives a round trip through RadioReferenceJson.
 */
public class JsonPersistenceTest
{
    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static Wsdl sWsdl;

    @BeforeAll
    static void load() throws Exception
    {
        sWsdl = Wsdl.load();
    }

    /**
     * Deserializes the WSDL sample response, which populates every element, then saves each returned value as JSON
     * and reads it back.  The restored value must equal the original in every property.
     */
    @TestFactory
    Stream<DynamicTest> everyResponseValueRoundTrips()
    {
        return sWsdl.getOperations().values().stream().map(operation -> DynamicTest.dynamicTest(operation.name(), () -> {
            ResponseEnvelope envelope = RadioReferenceService.deserialize(sWsdl.sampleResponse(operation.name()));
            Object body = envelope.getResponseBody();
            List<BeanPropertyDefinition> properties = XML_MAPPER.getSerializationConfig()
                .introspect(XML_MAPPER.constructType(body.getClass())).findProperties();

            for(Wsdl.Part part: operation.returns())
            {
                BeanPropertyDefinition property = properties.stream()
                    .filter(p -> p.getName().equals(part.name()) ||
                        (p.getWrapperName() != null && p.getWrapperName().getSimpleName().equals(part.name())))
                    .findFirst().orElseThrow(() -> new AssertionError("no property for " + part.name()));

                Object value = property.getAccessor().getValue(body);
                Assertions.assertNotNull(value, part.name());

                JavaType type = property.getPrimaryType();
                String json = RadioReferenceJson.toJson(value);
                Object restored = RadioReferenceJson.reader(type).readValue(json);

                JsonNode expected = XML_MAPPER.valueToTree(value);
                JsonNode actual = XML_MAPPER.valueToTree(restored);
                Assertions.assertEquals(expected, actual, operation.name() + "." + part.name() + " after JSON round trip:\n" + json);
            }
        }));
    }

    @Test
    void writesAndReadsFiles(@TempDir Path directory) throws Exception
    {
        String xml = Files.readString(Path.of("src/test/resources/responses/getCountryList.xml"));
        List<Country> countries = new RadioReferenceService(new io.github.dsheirer.rrapi.type.AuthorizationInformation(
            "key", "user", "password"), request -> new SoapTransport.Response(200, xml)).getCountries();

        Path file = directory.resolve("cache/countries.json");
        RadioReferenceJson.write(file, countries);
        RadioReferenceJson.write(file, countries); //Replaces the existing file

        List<Country> restored = RadioReferenceJson.read(file, new TypeReference<List<Country>>() {});
        Assertions.assertEquals(XML_MAPPER.valueToTree(countries), XML_MAPPER.valueToTree(restored));
        Assertions.assertEquals(236, restored.size());

        try(Stream<Path> files = Files.list(file.getParent()))
        {
            Assertions.assertEquals(List.of(file), files.toList(), "no temporary files left behind");
        }
    }

    @Test
    void datesAreWrittenAsText() throws Exception
    {
        Frequency frequency = new Frequency();
        frequency.setLastUpdated(Date.from(java.time.Instant.parse("2024-01-02T03:04:05Z")));

        String json = RadioReferenceJson.toJson(frequency);
        Assertions.assertTrue(json.contains("\"2024-01-02T03:04:05.000+00:00\""), json);
        Assertions.assertEquals(frequency.getLastUpdated(), RadioReferenceJson.fromJson(json, Frequency.class).getLastUpdated());
    }

    @Test
    void derivedValuesAreNotSaved() throws Exception
    {
        CountyInfo county = new CountyInfo();
        county.setLatitude(42.61);
        county.setLongitude(-73.99);
        county.setRange(20);
        Frequency frequency = new Frequency();
        frequency.setDownlink(460.0375);

        Assertions.assertFalse(RadioReferenceJson.toJson(county).contains("coverage"));
        Assertions.assertFalse(RadioReferenceJson.toJson(frequency).contains("downlinkHz"));
    }

    @Test
    void unknownPropertiesAreIgnored() throws Exception
    {
        Country country = RadioReferenceJson.fromJson("{\"countryId\":1,\"name\":\"United States\",\"addedLater\":true}",
            Country.class);

        Assertions.assertEquals(1, country.getCountryId());
        Assertions.assertEquals("United States", country.getName());
    }
}
