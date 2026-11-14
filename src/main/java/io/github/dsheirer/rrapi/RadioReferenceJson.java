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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Saves and loads the library's data types as JSON, for example to cache web service results on disk between
 * application runs.
 *
 * Every type returned by the service round-trips without losing values.  Dates are written as ISO-8601 text.  Unknown
 * properties are ignored when reading, so a file written by a newer version of the library still loads.
 *
 * To check if a saved copy is current, compare its lastUpdated date (counties, agencies, trunked systems, talkgroup
 * categories, frequencies and talkgroups) with the value the service returns now.
 */
public final class RadioReferenceJson
{
    private static final JsonMapper MAPPER = JsonMapper.builder()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .build();

    private RadioReferenceJson()
    {
    }

    /**
     * Converts the value to JSON
     * @param value to convert: a data type, or a list or map of them
     * @return JSON text
     * @throws IOException if the value can't be converted
     */
    public static String toJson(Object value) throws IOException
    {
        return MAPPER.writeValueAsString(value);
    }

    /**
     * Reads a value from JSON
     * @param json text
     * @param type of the value
     * @return value
     * @throws IOException if the JSON can't be read as the type
     */
    public static <T> T fromJson(String json, Class<T> type) throws IOException
    {
        return MAPPER.readValue(json, type);
    }

    /**
     * Reads a generic value, e.g. a list of frequencies, from JSON
     * @param json text
     * @param type of the value, e.g. new TypeReference&lt;List&lt;Frequency&gt;&gt;() {}
     * @return value
     * @throws IOException if the JSON can't be read as the type
     */
    public static <T> T fromJson(String json, TypeReference<T> type) throws IOException
    {
        return MAPPER.readValue(json, type);
    }

    /**
     * Writes the value to a JSON file.  The file is replaced atomically where the file system supports it, so readers
     * never see a partly written file.
     * @param path of the file
     * @param value to write
     * @throws IOException if the file can't be written
     */
    public static void write(Path path, Object value) throws IOException
    {
        Path absolute = path.toAbsolutePath();
        Path directory = absolute.getParent();

        if(directory != null)
        {
            Files.createDirectories(directory);
        }

        Path temporary = Files.createTempFile(directory, absolute.getFileName().toString(), ".tmp");

        try
        {
            MAPPER.writeValue(temporary.toFile(), value);

            try
            {
                Files.move(temporary, absolute, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            }
            catch(AtomicMoveNotSupportedException e)
            {
                Files.move(temporary, absolute, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        finally
        {
            Files.deleteIfExists(temporary);
        }
    }

    /**
     * Reads a value from a JSON file
     * @param path of the file
     * @param type of the value
     * @return value
     * @throws IOException if the file can't be read as the type
     */
    public static <T> T read(Path path, Class<T> type) throws IOException
    {
        return MAPPER.readValue(path.toFile(), type);
    }

    /**
     * Reads a generic value, e.g. a list of frequencies, from a JSON file
     * @param path of the file
     * @param type of the value, e.g. new TypeReference&lt;List&lt;Frequency&gt;&gt;() {}
     * @return value
     * @throws IOException if the file can't be read as the type
     */
    public static <T> T read(Path path, TypeReference<T> type) throws IOException
    {
        return MAPPER.readValue(path.toFile(), type);
    }

    /**
     * Reader for the type, for reading values with a type known only at runtime.  Package-private for testing.
     */
    static ObjectReader reader(JavaType type)
    {
        return MAPPER.readerFor(type);
    }

    /**
     * Writer used by toJson and write.  Package-private for testing.
     */
    static ObjectWriter writer()
    {
        return MAPPER.writer();
    }
}
