## Java Client Library for radioreference.com Web Services API

A client library for accessing the web services methods available from the radioreference.com website.  The web service wsdl uses legacy rpc encoding which is not compatible with current versions of the wsimport utility and therefore requires the use of non-modular (pre-Java 9) apache axis 1.4 library and related dependencies.  This library uses the Java HttpClient and Faster Jackson annotated classes for serialization of SOAP messages.  SOAP implementation and serialization are largely hidden from the user via the public methods available in the RadioReferenceService class.

### Compatibility
* SOAP Web Service API Version 18
* See https://wiki.radioreference.com/index.php/API
* See https://wiki.radioreference.com/index.php/RadioReference.com_Web_Service3.1

### Requirements
* Java 25+

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

### WSDL
`src/main/wsdl/radioreference-v18.wsdl` is the web service definition that this library implements.  The nightly
workflow compares it with the live WSDL and fails, showing the differences in the job summary, when RadioReference
changes the API.

### Releases
Each version tag (e.g. `v18.0.0`) is published as a [GitHub release](https://github.com/DSheirer/radio-reference-api/releases)
with the library, sources and javadoc jars and a `SHA256SUMS` file.  A rolling `nightly` pre-release is built from
`master` after each merge.

To release, set `version` in `gradle.properties` and push a matching tag: `v<version>`, or `v<version>-rc1` for a
pre-release.

### Gradle
Add an ivy repository for the GitHub releases.  The jar has no dependency metadata, so also declare
`jackson-dataformat-xml` and `slf4j-api`:

    repositories {
        ivy {
            url = 'https://github.com/DSheirer/radio-reference-api/releases/download'
            patternLayout { artifact 'v[revision]/[artifact]-[revision].[ext]' }
            metadataSources { artifact() }
            content { includeModule 'io.github.dsheirer', 'radio-reference-api' }
        }
    }

    dependencies {
        implementation 'io.github.dsheirer:radio-reference-api:18.0.0'
    }

### Building
Requires JDK 25 or newer: `./gradlew build`.  The tests call the live radioreference.com service and only run with
`./gradlew test -PliveTests`, using `radio.reference.user` and `radio.reference.password` from your `gradle.properties`.
