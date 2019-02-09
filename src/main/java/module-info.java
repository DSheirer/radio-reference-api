module io.github.dsheirer.rrapi {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.dataformat.xml;
    requires jackson.annotations;
    requires java.net.http;
    requires java.sql;
    requires java.xml;
    requires slf4j.api;
    requires com.fasterxml.jackson.databind;

    exports io.github.dsheirer.rrapi;
    exports io.github.dsheirer.rrapi.request;
    exports io.github.dsheirer.rrapi.response;
    exports io.github.dsheirer.rrapi.type;
}