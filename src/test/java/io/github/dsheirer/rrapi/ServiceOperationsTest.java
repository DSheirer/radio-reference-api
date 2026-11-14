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

import io.github.dsheirer.rrapi.type.Agency;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.Country;
import io.github.dsheirer.rrapi.type.TalkgroupRequestFilter;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Calls every public RadioReferenceService method without the network.  A fake transport checks each request against
 * the WSDL (operation name, parameters in WSDL order and their values) and answers with a sample response generated
 * from the WSDL, so every method is exercised from request serialization through response parsing.
 */
public class ServiceOperationsTest
{
    private static final AuthorizationInformation AUTH = new AuthorizationInformation("key", "user", "password");
    private static final Path RESPONSES = Path.of("src/test/resources/responses");
    private static final String SOAP_ENCODING = "http://schemas.xmlsoap.org/soap/encoding/";

    /**
     * Methods that don't call the web service
     */
    private static final Set<String> LOCAL_METHODS = Set.of("clearTagMap");

    private static Wsdl sWsdl;

    @FunctionalInterface
    interface Invocation
    {
        Object invoke(RadioReferenceService service) throws Exception;
    }

    /**
     * A call of a public service method
     * @param method name of the service method
     * @param operations WSDL operations the call sends
     * @param parameters expected parameter values of the first request, by WSDL parameter name
     * @param invocation that calls the method
     */
    record Call(String method, Set<String> operations, Map<String,String> parameters, Invocation invocation)
    {
        String description()
        {
            return method + " -> " + String.join(", ", operations);
        }
    }

    /**
     * A request received by the fake transport
     * @param operation name
     * @param parameters in the order sent, with the text of simple values
     * @param arrays SOAP-ENC:arrayType attribute and item count of each parameter that has the attribute
     */
    record Request(String operation, LinkedHashMap<String,String> parameters, Map<String,String> arrays) {}

    private static Call call(String method, String operation, Invocation invocation)
    {
        return new Call(method, Set.of(operation), Map.of(), invocation);
    }

    private static Call call(String method, String operation, Map<String,String> parameters, Invocation invocation)
    {
        return new Call(method, Set.of(operation), parameters, invocation);
    }

    private static Call call(String method, Set<String> operations, Invocation invocation)
    {
        return new Call(method, operations, Map.of(), invocation);
    }

    /**
     * Every public method of the service, with the WSDL operations it sends.  Lookup methods use id 1, which matches
     * the sample responses where every int is 1.
     */
    private static List<Call> calls()
    {
        Country country = new Country();
        country.setCountryId(7);
        Agency agency = new Agency();
        agency.setAgencyId(8);
        TalkgroupRequestFilter filter = new TalkgroupRequestFilter(9);
        filter.setCategoryId(10);
        filter.setTagId(11);
        filter.setDecimalValue(12);

        return List.of(
            call("getUserInfo", "getUserData", s -> s.getUserInfo()),
            call("getUserFeeds", "getUserFeedBroadcasts", s -> s.getUserFeeds()),
            call("getCountry", "getCountryList", s -> s.getCountry(1)),
            call("getCountries", "getCountryList", s -> s.getCountries()),
            call("getCountryInfo", "getCountryInfo", Map.of("coid", "7"), s -> s.getCountryInfo(country)),
            call("getCountryInfo", "getCountryInfo", Map.of("coid", "3"), s -> s.getCountryInfo(3)),
            call("getAgencyInfo", "getAgencyInfo", Map.of("aid", "8"), s -> s.getAgencyInfo(agency)),
            call("getAgencyInfo", "getAgencyInfo", Map.of("aid", "4"), s -> s.getAgencyInfo(4)),
            call("getState", "getStatesByList", s -> s.getState(5)),
            call("getStateInfo", "getStateInfo", Map.of("stid", "36"), s -> s.getStateInfo(36)),
            call("getStates", "getStatesByList", s -> s.getStates(List.of(1, 2))),
            call("getCounties", "getCountiesByList", s -> s.getCounties(List.of(1, 2))),
            call("getCountyInfo", "getCountyInfo", Map.of("ctid", "1858"), s -> s.getCountyInfo(1858)),
            call("getCountyFrequenciesByTag", "getCountyFreqsByTag", Map.of("ctid", "21", "tag", "22"),
                s -> s.getCountyFrequenciesByTag(21, 22)),
            call("getAgencyFrequenciesByTag", "getAgencyFreqsByTag", Map.of("aid", "23", "tag", "24"),
                s -> s.getAgencyFrequenciesByTag(23, 24)),
            call("getSystemInformation", "getTrsDetails", Map.of("sid", "5298"), s -> s.getSystemInformation(5298)),
            call("getApco25Systems", "getTrsBySysid", Map.of("sysid", "2AE"), s -> s.getApco25Systems("2AE")),
            call("getSites", "getTrsSites", Map.of("sid", "25"), s -> s.getSites(25)),
            call("getTalkgroups", "getTrsTalkgroups", Map.of("sid", "26"), s -> s.getTalkgroups(26)),
            call("getTalkgroups", "getTrsTalkgroups", Map.of("sid", "9", "tgCid", "10", "tgTag", "11", "tgDec", "12"),
                s -> s.getTalkgroups(filter)),
            call("getTalkgroupCategories", "getTrsTalkgroupCats", Map.of("sid", "27"), s -> s.getTalkgroupCategories(27)),
            call("getFlavor", "getTrsFlavor", s -> s.getFlavor(1)),
            call("getFlavorsMap", "getTrsFlavor", s -> s.getFlavorsMap()),
            call("getMode", "getMode", s -> s.getMode(1)),
            call("getModesMap", "getMode", s -> s.getModesMap()),
            call("getMetro", "getMetroArea", s -> s.getMetro(1)),
            call("getMetrosMap", "getMetroArea", s -> s.getMetrosMap()),
            call("getMetroCountiesMap", Set.of("getMetroArea", "getMetroAreaInfo"), s -> s.getMetroCountiesMap()),
            call("getMetroCounties", "getMetroAreaInfo", Map.of("mid", "28"), s -> s.getMetroCounties(28)),
            call("getMetrosByState", Set.of("getStateInfo", "getMetroArea", "getMetroAreaInfo"),
                s -> s.getMetrosByState(1)),
            call("getTagsMap", "getTag", s -> s.getTagsMap()),
            call("getTag", "getTag", s -> s.getTag(1)),
            call("getType", "getTrsType", s -> s.getType(1)),
            call("getTypesMap", "getTrsType", s -> s.getTypesMap()),
            call("getVoicesMap", "getTrsVoice", s -> s.getVoicesMap()),
            call("getVoice", "getTrsVoice", s -> s.getVoice(1)),
            call("getZipcodeInfo", "getZipcodeInfo", Map.of("zipcode", "13069"), s -> s.getZipcodeInfo(13069)),
            call("getSubCategoryFrequencies", "getSubcatFreqs", Map.of("scid", "29"), s -> s.getSubCategoryFrequencies(29)),
            call("searchCountyFrequencies", "searchCountyFreq", Map.of("ctid", "30", "freq", "460.0375"),
                s -> s.searchCountyFrequencies(30, 460.0375)),
            call("searchCountyFrequencies", "searchCountyFreq", Map.of("ctid", "30", "freq", "460.0375", "tone", "100.0 PL"),
                s -> s.searchCountyFrequencies(30, 460.0375, "100.0 PL")),
            call("searchStateFrequencies", "searchStateFreq", Map.of("stid", "31", "freq", "460.0375"),
                s -> s.searchStateFrequencies(31, 460.0375)),
            call("searchStateFrequencies", "searchStateFreq", Map.of("stid", "31", "freq", "460.0375", "tone", "D023"),
                s -> s.searchStateFrequencies(31, 460.0375, "D023")),
            call("searchMetroFrequencies", "searchMetroFreq", Map.of("mid", "32", "freq", "460.0375"),
                s -> s.searchMetroFrequencies(32, 460.0375)),
            call("searchMetroFrequencies", "searchMetroFreq", Map.of("mid", "32", "freq", "460.0375", "tone", "CSQ"),
                s -> s.searchMetroFrequencies(32, 460.0375, "CSQ")),
            call("getFccCallsignDetails", "fccGetCallsign", Map.of("callsign", "WQDY448"),
                s -> s.getFccCallsignDetails("WQDY448")),
            call("getFccRadioServiceCodes", "fccGetRadioServiceCode", s -> s.getFccRadioServiceCodes()),
            call("getFccRadioServiceCode", "fccGetRadioServiceCode", Map.of("code", "AB"),
                s -> s.getFccRadioServiceCode("AB")),
            call("getFccCallsignsNearLocation", "fccGetProxCallsigns", Map.of("lat", "42.94833", "lon", "-76.02389", "range", "3.0"),
                s -> s.getFccCallsignsNearLocation(42.94833, -76.02389, 3.0)));
    }

    @BeforeAll
    static void load() throws Exception
    {
        sWsdl = Wsdl.load();
    }

    @Test
    void everyWsdlOperationIsCalled()
    {
        Set<String> called = calls().stream().flatMap(call -> call.operations().stream()).collect(Collectors.toSet());
        Set<String> missing = new TreeSet<>(sWsdl.getOperations().keySet());
        missing.removeAll(called);
        Assertions.assertTrue(missing.isEmpty(), "WSDL operations not called by any test: " + missing);
    }

    @Test
    void everyPublicMethodIsCalled()
    {
        Set<String> called = calls().stream().map(Call::method).collect(Collectors.toSet());
        Set<String> missing = Arrays.stream(RadioReferenceService.class.getDeclaredMethods())
            .filter(method -> Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()))
            .map(Method::getName)
            .filter(name -> !called.contains(name) && !LOCAL_METHODS.contains(name))
            .collect(Collectors.toCollection(TreeSet::new));
        Assertions.assertTrue(missing.isEmpty(), "Public service methods not called by any test: " + missing);
    }

    @TestFactory
    Stream<DynamicTest> everyCallSendsWsdlRequestsAndParsesTheResponse()
    {
        return calls().stream().map(call -> DynamicTest.dynamicTest(call.description(), () -> {
            List<Request> requests = new ArrayList<>();
            List<String> problems = new ArrayList<>();

            RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
                Request request = parseRequest(xml);
                requests.add(request);
                checkRequest(request, problems);
                return new SoapTransport.Response(200, sWsdl.sampleResponse(request.operation()));
            });

            Object result = call.invocation().invoke(service);

            Assertions.assertTrue(problems.isEmpty(), () -> String.join("\n", problems));
            Assertions.assertFalse(requests.isEmpty(), "no request was sent");
            Assertions.assertEquals(call.operations(),
                requests.stream().map(Request::operation).collect(Collectors.toCollection(LinkedHashSet::new)),
                "WSDL operations sent");

            Map<String,String> sent = requests.get(0).parameters();
            call.parameters().forEach((name, value) ->
                Assertions.assertEquals(value, sent.get(name), "value of parameter '" + name + "'"));

            Assertions.assertNotNull(result, "method returned null for the sample response");

            if(result instanceof Collection<?> collection)
            {
                Assertions.assertFalse(collection.isEmpty(), "method returned an empty collection");
            }
            else if(result instanceof Map<?,?> map)
            {
                Assertions.assertFalse(map.isEmpty(), "method returned an empty map");
            }
        }));
    }

    @Test
    void lookupsAreCachedAfterTheFirstCall() throws Exception
    {
        List<String> operations = new ArrayList<>();
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            String operation = parseRequest(xml).operation();
            operations.add(operation);
            return new SoapTransport.Response(200, sWsdl.sampleResponse(operation));
        });

        service.getModesMap();
        service.getMode(1);
        service.getTagsMap();
        service.clearTagMap();
        service.getTag(1);

        Assertions.assertEquals(List.of("getMode", "getTag", "getTag"), operations);
    }

    @Test
    void parsesRealCountryListResponse() throws Exception
    {
        String body = Files.readString(RESPONSES.resolve("getCountryList.xml"));
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> new SoapTransport.Response(200, body));

        List<Country> countries = service.getCountries();

        Assertions.assertEquals(236, countries.size());
        Assertions.assertEquals(5, countries.get(0).getCountryId());
        Assertions.assertEquals("Afghanistan", countries.get(0).getName());
        Assertions.assertEquals("AF", countries.get(0).getCountryCode());
        Assertions.assertEquals("United States", service.getCountry(1).getName());
    }

    @Test
    void realAuthenticationFaultThrowsWithFaultDetails() throws Exception
    {
        String body = Files.readString(RESPONSES.resolve("fault-auth.xml"), StandardCharsets.ISO_8859_1);
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> new SoapTransport.Response(500, body));

        RadioReferenceException exception = Assertions.assertThrows(RadioReferenceException.class, service::getUserInfo);

        Assertions.assertTrue(exception.hasFault());
        Assertions.assertEquals("AUTH", exception.getFault().getFaultCode());
        Assertions.assertTrue(exception.getFault().getFaultString().startsWith("Invalid Username or Password"));
    }

    @Test
    void nonXmlErrorResponseThrows() throws Exception
    {
        RadioReferenceService service = new RadioReferenceService(AUTH,
            xml -> new SoapTransport.Response(503, "<html><body>Service Unavailable</body></html>"));

        Assertions.assertThrows(RadioReferenceException.class, service::getCountries);
    }

    @Test
    void transportErrorThrows() throws Exception
    {
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            throw new java.io.IOException("connection reset");
        });

        RadioReferenceException exception = Assertions.assertThrows(RadioReferenceException.class, service::getCountries);
        Assertions.assertInstanceOf(java.io.IOException.class, exception.getCause());
    }

    /**
     * Checks the operation name and the parameter names and order against the WSDL
     */
    private static void checkRequest(Request request, List<String> problems)
    {
        Wsdl.Operation operation = sWsdl.getOperations().get(request.operation());

        if(operation == null)
        {
            problems.add("request for unknown WSDL operation: " + request.operation());
            return;
        }

        List<String> expected = new ArrayList<>(operation.parameters().stream().map(Wsdl.Part::name).toList());
        List<String> actual = new ArrayList<>(request.parameters().keySet());

        //The WSDL declares getCountryList without parameters; the library sends authInfo as it does with every request
        if(expected.isEmpty())
        {
            actual.remove("authInfo");
        }

        //RPC parameters are matched by position, so the order matters.  Optional parameters can be omitted.
        if(!isOrderedSubset(actual, expected))
        {
            problems.add(request.operation() + ": sent parameters " + actual + " but the WSDL declares " + expected);
        }

        if(!expected.isEmpty() && !actual.contains("authInfo"))
        {
            problems.add(request.operation() + ": authInfo was not sent");
        }

        //The service only decodes array parameters as arrays when they declare SOAP-ENC:arrayType with the item count
        for(Wsdl.Part part: operation.parameters())
        {
            String itemType = sWsdl.getArrayItemType(part.type());

            if(itemType != null && request.parameters().containsKey(part.name()))
            {
                String array = request.arrays().get(part.name());
                String expectedPrefix = "tns:" + itemType + "[";

                if(array == null)
                {
                    problems.add(request.operation() + ": array parameter '" + part.name() + "' has no SOAP-ENC:arrayType");
                }
                else
                {
                    String count = array.substring(array.indexOf("items=") + 6);

                    if(!array.startsWith(expectedPrefix + count + "]"))
                    {
                        problems.add(request.operation() + ": array parameter '" + part.name() + "' declares [" +
                            array + "], expected " + expectedPrefix + count + "]");
                    }
                }
            }
        }
    }

    private static boolean isOrderedSubset(List<String> actual, List<String> expected)
    {
        int next = 0;
        for(String name: actual)
        {
            int index = expected.indexOf(name);
            if(index < next)
            {
                return false;
            }
            next = index + 1;
        }
        return true;
    }

    private static Request parseRequest(String xml) throws java.io.IOException
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document document = factory.newDocumentBuilder()
                .parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            Element body = firstChild(document.getDocumentElement(), "Body");
            Element operation = firstElement(body);
            LinkedHashMap<String,String> parameters = new LinkedHashMap<>();
            Map<String,String> arrays = new LinkedHashMap<>();

            for(Node node = operation.getFirstChild(); node != null; node = node.getNextSibling())
            {
                if(node instanceof Element parameter)
                {
                    parameters.put(parameter.getLocalName(), parameter.getTextContent());
                    String arrayType = parameter.getAttributeNS(SOAP_ENCODING, "arrayType");

                    if(!arrayType.isEmpty())
                    {
                        arrays.put(parameter.getLocalName(), arrayType + " items=" +
                            parameter.getElementsByTagName("item").getLength());
                    }
                }
            }

            return new Request(operation.getLocalName(), parameters, arrays);
        }
        catch(Exception e)
        {
            throw new java.io.IOException("Unable to parse request: " + xml, e);
        }
    }

    private static Element firstChild(Element parent, String name)
    {
        for(Node node = parent.getFirstChild(); node != null; node = node.getNextSibling())
        {
            if(node instanceof Element element && element.getLocalName().equals(name))
            {
                return element;
            }
        }
        throw new IllegalStateException("No " + name + " element");
    }

    private static Element firstElement(Element parent)
    {
        for(Node node = parent.getFirstChild(); node != null; node = node.getNextSibling())
        {
            if(node instanceof Element element)
            {
                return element;
            }
        }
        throw new IllegalStateException("No child element in " + parent.getLocalName());
    }
}
