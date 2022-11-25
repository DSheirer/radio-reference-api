## Java Client Library for radioreference.com Web Services API

A client library for accessing the web services methods available from the radioreference.com website.  The web service wsdl uses legacy rpc encoding which is not compatible with current versions of the wsimport utility and therefore requires the use of non-modular (pre-Java 9) apache axis 1.4 library and related dependencies.  This library uses the Java 11 HttpClient and Faster Jackson annotated classes for serialization of SOAP messages.  SOAP implementation and serialization are largely hidden from the user via the public methods available in the RadioReferenceService class.

### Compatibility
* SOAP Web Service API Version 15
* See https://wiki.radioreference.com/index.php/API
* See https://wiki.radioreference.com/index.php/RadioReference.com_Web_Service3.1

### Requirements
* Java 11+

### Usage
    AuthorizationInformation authorizationInformation = new AuthorizationInformation("apikey", "username", "password");

    try
    {
        RadioReferenceService service = new RadioReferenceService(authorizationInformation);
        UserInfo userInfo = service.getUserInfo();
    }
    catch(RadioReferenceException e)
    {
        //Handle error
    }

### Gradle

    dependencies {
        compile 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.9.8'
        compile 'ch.qos.logback:logback-classic:1.2.3'
        compile 'org.slf4j:slf4j-api:1.7.25'
        compile 'io.github.dsheirer:radio-reference-api:15.1.9'
    }
    
### Maven

    <dependency>
      <groupId>io.github.dsheirer</groupId>
      <artifactId>radio-reference-api</artifactId>
      <version>15.1.9</version>
    </dependency>
