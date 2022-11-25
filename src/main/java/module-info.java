/**
 * Java 9+ JPMS module information
 */
module io.github.dsheirer.rrapi
{
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires java.net.http;
    requires java.sql;
    requires java.xml;
    requires org.slf4j;

    exports io.github.dsheirer.rrapi;
    exports io.github.dsheirer.rrapi.request;
    exports io.github.dsheirer.rrapi.response;
    exports io.github.dsheirer.rrapi.type;
}