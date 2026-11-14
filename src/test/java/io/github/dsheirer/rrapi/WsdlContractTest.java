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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.dsheirer.rrapi.request.RequestBody;
import io.github.dsheirer.rrapi.response.Fault;
import io.github.dsheirer.rrapi.response.ResponseBody;
import io.github.dsheirer.rrapi.response.ResponseEnvelope;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

/**
 * Checks the library classes against the committed WSDL, without calling the web service.
 *
 * - Every WSDL operation has a registered request and response class.
 * - Every request class sends the WSDL parameter names.
 * - Every element of every response type maps to a Java property of a compatible type.  The XML mapper fails on
 *   unknown elements, so an unmapped element would break the whole response.
 * - A sample response with every element populated deserializes, and every sample value reaches its Java property.
 */
public class WsdlContractTest
{
    private static final XmlMapper MAPPER = new XmlMapper();
    private static final Map<String,Set<Class<?>>> COMPATIBLE_TYPES = Map.of(
        "xsd:int", Set.of(int.class, Integer.class, long.class, Long.class),
        "xsd:decimal", Set.of(double.class, Double.class, float.class, Float.class, BigDecimal.class),
        "xsd:boolean", Set.of(boolean.class, Boolean.class),
        "xsd:dateTime", Set.of(Date.class),
        "xsd:string", Set.of(String.class));

    /**
     * Parameters the library sends that the WSDL doesn't declare.  The WSDL declares getCountryList without
     * parameters; the library sends authInfo with it as it does with every request and the service ignores it.
     */
    private static final Map<String,Set<String>> KNOWN_EXTRA_PARAMETERS = Map.of("getCountryList", Set.of("authInfo"));

    private static Wsdl sWsdl;
    private static Map<String,Class<?>> sRequestClasses;
    private static Map<String,Class<?>> sResponseClasses;

    @BeforeAll
    static void load() throws Exception
    {
        sWsdl = Wsdl.load();
        sRequestClasses = subTypes(RequestBody.class);
        sResponseClasses = subTypes(ResponseBody.class);
    }

    @Test
    void wsdlIsParsed()
    {
        Assertions.assertEquals(31, sWsdl.getOperations().size(), "WSDL v18 defines 31 operations");
    }

    @Test
    void everyOperationHasRequestAndResponseClasses()
    {
        List<String> problems = new ArrayList<>();

        for(String operation: sWsdl.getOperations().keySet())
        {
            if(!sRequestClasses.containsKey(operation))
            {
                problems.add(operation + ": no request class registered in RequestBody");
            }

            if(!sResponseClasses.containsKey(operation + "Response"))
            {
                problems.add(operation + ": no response class registered in ResponseBody");
            }
        }

        assertNoProblems(problems);
    }

    @Test
    void faultIsRegistered()
    {
        Assertions.assertEquals(Fault.class, sResponseClasses.get("Fault"));
    }

    @TestFactory
    Stream<DynamicTest> requestParametersMatchWsdl()
    {
        return sWsdl.getOperations().values().stream().map(operation -> DynamicTest.dynamicTest(operation.name(), () -> {
            Class<?> requestClass = sRequestClasses.get(operation.name());
            Assertions.assertNotNull(requestClass, "no request class");

            Set<String> expected = operation.parameters().stream().map(Wsdl.Part::name).collect(Collectors.toSet());
            Set<String> actual = new java.util.HashSet<>(serializedProperties(requestClass).keySet());
            actual.removeAll(KNOWN_EXTRA_PARAMETERS.getOrDefault(operation.name(), Set.of()));
            Assertions.assertEquals(expected, actual, requestClass.getSimpleName() + " parameter names");
        }));
    }

    /**
     * The service matches RPC parameters by position.  Without an explicit property order Jackson's order follows
     * reflection order, which the JVM doesn't guarantee, so every request with parameters must declare its order.
     */
    @TestFactory
    Stream<DynamicTest> requestParametersAreSentInWsdlOrder()
    {
        return sWsdl.getOperations().values().stream()
            .filter(operation -> operation.parameters().size() > 1)
            .map(operation -> DynamicTest.dynamicTest(operation.name(), () -> {
                Class<?> requestClass = sRequestClasses.get(operation.name());
                Assertions.assertNotNull(requestClass.getAnnotation(com.fasterxml.jackson.annotation.JsonPropertyOrder.class),
                    requestClass.getSimpleName() + " must declare @JsonPropertyOrder");

                List<String> expected = operation.parameters().stream().map(Wsdl.Part::name).toList();
                List<String> actual = new ArrayList<>(serializedProperties(requestClass).keySet());
                Assertions.assertEquals(expected, actual, requestClass.getSimpleName() + " parameter order");
            }));
    }

    @TestFactory
    Stream<DynamicTest> responseTypesMapEveryElement()
    {
        return sWsdl.getOperations().values().stream().map(operation -> DynamicTest.dynamicTest(operation.name(), () -> {
            Class<?> responseClass = sResponseClasses.get(operation.name() + "Response");
            Assertions.assertNotNull(responseClass, "no response class");

            List<String> problems = new ArrayList<>();
            Map<String,BeanPropertyDefinition> properties = deserializedProperties(MAPPER.constructType(responseClass));

            for(Wsdl.Part part: operation.returns())
            {
                checkMapping(part.type(), part.name(), properties.get(part.name()),
                    responseClass.getSimpleName() + "." + part.name(), problems, new ArrayList<>());
            }

            assertNoProblems(problems);
        }));
    }

    @TestFactory
    Stream<DynamicTest> sampleResponsesDeserializeEveryValue()
    {
        return sWsdl.getOperations().values().stream().map(operation -> DynamicTest.dynamicTest(operation.name(), () -> {
            String xml = sWsdl.sampleResponse(operation.name());
            ResponseEnvelope envelope = RadioReferenceService.deserialize(xml);
            Assertions.assertNotNull(envelope.getResponseBody(), "no response body");
            Assertions.assertEquals(sResponseClasses.get(operation.name() + "Response"), envelope.getResponseBody().getClass());

            JsonNode tree = MAPPER.valueToTree(envelope.getResponseBody());

            //The response body is serialized wrapped in its type name, which differs from the operation name for
            //response classes shared by several operations
            Set<String> returnNames = operation.returns().stream().map(Wsdl.Part::name).collect(Collectors.toSet());
            String wrapper = tree.size() == 1 ? tree.fieldNames().next() : null;

            if(wrapper != null && !returnNames.contains(wrapper))
            {
                tree = tree.get(wrapper);
            }

            List<String> problems = new ArrayList<>();

            Map<String,BeanPropertyDefinition> properties =
                serializedProperties(MAPPER.constructType(envelope.getResponseBody().getClass()));

            for(Wsdl.Part part: operation.returns())
            {
                checkValue(part.type(), part.name(), properties.get(part.name()), tree,
                    operation.name() + "." + part.name(), problems, new ArrayList<>());
            }

            assertNoProblems(problems);
        }));
    }

    /**
     * Checks that the WSDL element maps to a Java property of a compatible type, recursing into structs and arrays.
     */
    private static void checkMapping(String type, String elementName, BeanPropertyDefinition property, String path,
                                     List<String> problems, List<String> visiting)
    {
        if(property == null || !property.couldDeserialize())
        {
            problems.add(path + ": WSDL element '" + elementName + "' (" + type + ") has no Java property");
            return;
        }

        checkType(type, property.getPrimaryType(), path, problems, visiting);
    }

    private static void checkType(String type, JavaType javaType, String path, List<String> problems, List<String> visiting)
    {
        if(Wsdl.isSimple(type))
        {
            Set<Class<?>> compatible = COMPATIBLE_TYPES.get(type);

            if(compatible == null || !compatible.contains(javaType.getRawClass()))
            {
                problems.add(path + ": WSDL type " + type + " mapped to incompatible Java type " +
                    javaType.getRawClass().getSimpleName());
            }
            return;
        }

        String itemType = sWsdl.getArrayItemType(type);

        if(itemType != null)
        {
            if(!javaType.isCollectionLikeType() && !javaType.isArrayType())
            {
                problems.add(path + ": WSDL array " + type + " mapped to non-collection Java type " +
                    javaType.getRawClass().getSimpleName());
                return;
            }

            checkType(itemType, javaType.getContentType(), path + "[]", problems, visiting);
            return;
        }

        List<Wsdl.Part> fields = sWsdl.getStruct(type);

        if(fields == null)
        {
            problems.add(path + ": unknown WSDL type " + type);
            return;
        }

        if(visiting.contains(type))
        {
            return;
        }

        visiting.add(type);
        Map<String,BeanPropertyDefinition> properties = deserializedProperties(javaType);

        for(Wsdl.Part field: fields)
        {
            checkMapping(field.type(), field.name(), properties.get(field.name()),
                path + "." + field.name(), problems, visiting);
        }

        visiting.remove(type);
    }

    /**
     * Checks that the sample value of the WSDL element reached the deserialized object, recursing into structs and
     * arrays.  The serialized tree names properties by property name, so the Java property maps the element name.
     *
     * @param property Java property mapped to the element
     * @param parent tree node of the object that holds the property
     */
    private static void checkValue(String type, String elementName, BeanPropertyDefinition property, JsonNode parent,
                                   String path, List<String> problems, List<String> visiting)
    {
        JsonNode actual = property != null && parent != null ? parent.get(property.getName()) : null;

        if(actual == null || actual.isNull() || actual.isMissingNode())
        {
            problems.add(path + ": sample value was not deserialized");
            return;
        }

        checkValue(type, elementName, property.getPrimaryType(), actual, path, problems, visiting);
    }

    private static void checkValue(String type, String elementName, JavaType javaType, JsonNode actual, String path,
                                   List<String> problems, List<String> visiting)
    {
        if(Wsdl.isSimple(type))
        {
            String expected = Wsdl.sampleValue(type, elementName);

            if(!matches(type, expected, actual))
            {
                problems.add(path + ": expected sample value [" + expected + "] but found [" + actual + "]");
            }
            return;
        }

        String itemType = sWsdl.getArrayItemType(type);

        if(itemType != null)
        {
            if(!actual.isArray() || actual.size() != 1)
            {
                problems.add(path + ": expected an array with the one sample item but found " + actual);
                return;
            }

            checkValue(itemType, "item", javaType.getContentType(), actual.get(0), path + "[0]", problems, visiting);
            return;
        }

        if(visiting.contains(type))
        {
            return;
        }

        visiting.add(type);
        Map<String,BeanPropertyDefinition> properties = serializedProperties(javaType);

        for(Wsdl.Part field: sWsdl.getStruct(type))
        {
            checkValue(field.type(), field.name(), properties.get(field.name()), actual, path + "." + field.name(),
                problems, visiting);
        }

        visiting.remove(type);
    }

    private static boolean matches(String type, String expected, JsonNode actual)
    {
        return switch(type)
        {
            case "xsd:int", "xsd:decimal" -> actual.isNumber() && actual.asDouble() == Double.parseDouble(expected);
            case "xsd:boolean" -> actual.isBoolean() && actual.asBoolean() == Boolean.parseBoolean(expected);
            case "xsd:dateTime" -> {
                long expectedMillis = Instant.parse(expected).toEpochMilli();
                if(actual.isNumber())
                {
                    yield actual.asLong() == expectedMillis;
                }
                //Properties with a custom date format serialize as text
                yield actual.isTextual() && Instant.parse(actual.asText()).toEpochMilli() == expectedMillis;
            }
            default -> actual.isTextual() && actual.asText().equals(expected);
        };
    }

    /**
     * Deserializable properties of the type, by XML element name
     */
    private static Map<String,BeanPropertyDefinition> deserializedProperties(JavaType type)
    {
        Map<String,BeanPropertyDefinition> properties = new HashMap<>();
        for(BeanPropertyDefinition property: MAPPER.getDeserializationConfig().introspect(type).findProperties())
        {
            properties.put(elementName(property), property);
        }
        return properties;
    }

    /**
     * Serialized properties of the class, by XML element name
     */
    private static Map<String,BeanPropertyDefinition> serializedProperties(Class<?> type)
    {
        return serializedProperties(MAPPER.constructType(type));
    }

    private static Map<String,BeanPropertyDefinition> serializedProperties(JavaType type)
    {
        Map<String,BeanPropertyDefinition> properties = new LinkedHashMap<>();
        for(BeanPropertyDefinition property: MAPPER.getSerializationConfig().introspect(type).findProperties())
        {
            if(property.couldSerialize())
            {
                properties.put(elementName(property), property);
            }
        }
        return properties;
    }

    /**
     * XML element name of the property: the wrapper element for a list wrapped in a parent element
     * (e.g. &lt;sysid&gt;&lt;item&gt;...&lt;/item&gt;&lt;/sysid&gt;), otherwise the property name.
     */
    private static String elementName(BeanPropertyDefinition property)
    {
        return property.getWrapperName() != null && !property.getWrapperName().getSimpleName().isEmpty() ?
            property.getWrapperName().getSimpleName() : property.getName();
    }

    /**
     * Subtypes registered on the base class, by type name
     */
    private static Map<String,Class<?>> subTypes(Class<?> base)
    {
        Map<String,Class<?>> types = new HashMap<>();
        for(JsonSubTypes.Type type: base.getAnnotation(JsonSubTypes.class).value())
        {
            types.put(type.name(), type.value());
        }
        return types;
    }

    private static void assertNoProblems(Collection<String> problems)
    {
        Assertions.assertTrue(problems.isEmpty(), () -> problems.size() + " problem(s):\n  " + String.join("\n  ", problems));
    }
}
