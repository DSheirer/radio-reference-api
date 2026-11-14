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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parsed contents of the committed radioreference.com WSDL: operations with their parameters and return types, and the
 * complex types.  Generates document-style sample responses, the format the service returns for the 'doc' style
 * requested by this library: plain elements, with array items as repeated item elements.
 *
 * Sample values are fixed so that tests can predict them: every int is 1, every decimal is 12.5, every boolean is
 * true, every dateTime is {@link #SAMPLE_DATE_TIME} and every string is the name of its element.
 */
public class Wsdl
{
    public static final Path WSDL_PATH = Path.of("src/main/wsdl/radioreference-v18.wsdl");
    public static final String SAMPLE_INT = "1";
    public static final String SAMPLE_DECIMAL = "12.5";
    public static final String SAMPLE_BOOLEAN = "true";
    public static final String SAMPLE_DATE_TIME = "2024-01-02T03:04:05Z";

    private static final String WSDL_NS = "http://schemas.xmlsoap.org/wsdl/";
    private static final String XSD_NS = "http://www.w3.org/2001/XMLSchema";

    /**
     * Operation parameter or return value
     * @param name of the part
     * @param type of the part, xsd:<name> for simple types or the complex type name
     */
    public record Part(String name, String type) {}

    /**
     * WSDL operation
     * @param name of the operation
     * @param parameters in declaration order
     * @param returns parts of the response
     */
    public record Operation(String name, List<Part> parameters, List<Part> returns) {}

    private final Map<String,Operation> mOperations = new LinkedHashMap<>();
    private final Map<String,List<Part>> mStructs = new LinkedHashMap<>();
    private final Map<String,String> mArrays = new LinkedHashMap<>();

    private Wsdl(Document document)
    {
        Element root = document.getDocumentElement();

        for(Element complexType: elements(root, XSD_NS, "complexType"))
        {
            String name = complexType.getAttribute("name");
            List<Element> fields = elements(complexType, XSD_NS, "element");

            if(!fields.isEmpty())
            {
                List<Part> parts = new ArrayList<>();
                for(Element field: fields)
                {
                    parts.add(new Part(field.getAttribute("name"), typeName(field.getAttribute("type"))));
                }
                mStructs.put(name, parts);
            }
            else
            {
                for(Element attribute: elements(complexType, XSD_NS, "attribute"))
                {
                    String arrayType = attribute.getAttributeNS(WSDL_NS, "arrayType");

                    if(!arrayType.isEmpty())
                    {
                        mArrays.put(name, typeName(arrayType.replace("[]", "")));
                    }
                }
            }
        }

        Map<String,List<Part>> messages = new LinkedHashMap<>();

        for(Element message: children(root, WSDL_NS, "message"))
        {
            List<Part> parts = new ArrayList<>();
            for(Element part: children(message, WSDL_NS, "part"))
            {
                parts.add(new Part(part.getAttribute("name"), typeName(part.getAttribute("type"))));
            }
            messages.put(message.getAttribute("name"), parts);
        }

        Element portType = children(root, WSDL_NS, "portType").get(0);

        for(Element operation: children(portType, WSDL_NS, "operation"))
        {
            String input = localName(children(operation, WSDL_NS, "input").get(0).getAttribute("message"));
            String output = localName(children(operation, WSDL_NS, "output").get(0).getAttribute("message"));
            String name = operation.getAttribute("name");
            mOperations.put(name, new Operation(name, messages.get(input), messages.get(output)));
        }
    }

    /**
     * Loads the committed WSDL
     */
    public static Wsdl load() throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        try(InputStream in = Files.newInputStream(WSDL_PATH))
        {
            return new Wsdl(factory.newDocumentBuilder().parse(in));
        }
    }

    public Map<String,Operation> getOperations()
    {
        return Collections.unmodifiableMap(mOperations);
    }

    public Operation getOperation(String name)
    {
        Operation operation = mOperations.get(name);

        if(operation == null)
        {
            throw new IllegalArgumentException("Unknown WSDL operation: " + name);
        }

        return operation;
    }

    /**
     * Fields of a struct type, or null if the type is not a struct
     */
    public List<Part> getStruct(String type)
    {
        return mStructs.get(type);
    }

    /**
     * Item type of an array type, or null if the type is not an array
     */
    public String getArrayItemType(String type)
    {
        return mArrays.get(type);
    }

    public static boolean isSimple(String type)
    {
        return type.startsWith("xsd:");
    }

    /**
     * Sample value for a simple type
     * @param type of the value
     * @param elementName used as the value of strings
     */
    public static String sampleValue(String type, String elementName)
    {
        return switch(type)
        {
            case "xsd:int" -> SAMPLE_INT;
            case "xsd:decimal" -> SAMPLE_DECIMAL;
            case "xsd:boolean" -> SAMPLE_BOOLEAN;
            case "xsd:dateTime" -> SAMPLE_DATE_TIME;
            case "xsd:string" -> elementName;
            default -> throw new IllegalArgumentException("Unsupported simple type: " + type);
        };
    }

    /**
     * Document-style SOAP response envelope for the operation, with sample values for every element and one item in
     * every array.
     */
    public String sampleResponse(String operationName)
    {
        Operation operation = getOperation(operationName);
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        sb.append("<SOAP-ENV:Body><").append(operationName).append("Response>");

        for(Part part: operation.returns())
        {
            appendSample(sb, part.name(), part.type());
        }

        sb.append("</").append(operationName).append("Response></SOAP-ENV:Body></SOAP-ENV:Envelope>");
        return sb.toString();
    }

    private void appendSample(StringBuilder sb, String elementName, String type)
    {
        sb.append('<').append(elementName).append('>');

        if(isSimple(type))
        {
            sb.append(sampleValue(type, elementName));
        }
        else if(mArrays.containsKey(type))
        {
            appendSample(sb, "item", mArrays.get(type));
        }
        else if(mStructs.containsKey(type))
        {
            for(Part field: mStructs.get(type))
            {
                appendSample(sb, field.name(), field.type());
            }
        }
        else
        {
            throw new IllegalArgumentException("Unknown WSDL type: " + type);
        }

        sb.append("</").append(elementName).append('>');
    }

    /**
     * Normalizes a type reference: xsd:int stays as is, tns:Name becomes Name
     */
    private static String typeName(String type)
    {
        return type.startsWith("xsd:") ? type : localName(type);
    }

    private static String localName(String qualifiedName)
    {
        int colon = qualifiedName.indexOf(':');
        return colon < 0 ? qualifiedName : qualifiedName.substring(colon + 1);
    }

    private static List<Element> elements(Element parent, String namespace, String localName)
    {
        List<Element> elements = new ArrayList<>();
        NodeList nodes = parent.getElementsByTagNameNS(namespace, localName);
        for(int x = 0; x < nodes.getLength(); x++)
        {
            elements.add((Element)nodes.item(x));
        }
        return elements;
    }

    private static List<Element> children(Element parent, String namespace, String localName)
    {
        List<Element> elements = new ArrayList<>();
        for(Node node = parent.getFirstChild(); node != null; node = node.getNextSibling())
        {
            if(node instanceof Element element && namespace.equals(element.getNamespaceURI()) &&
                localName.equals(element.getLocalName()))
            {
                elements.add(element);
            }
        }
        return elements;
    }
}
