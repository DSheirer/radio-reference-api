## Java Client Library for radioreference.com Web Services API

[![Nightly](https://github.com/DSheirer/radio-reference-api/actions/workflows/nightly.yml/badge.svg?branch=master)](https://github.com/DSheirer/radio-reference-api/actions/workflows/nightly.yml)
[![Nightly release](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fapi.github.com%2Frepos%2FDSheirer%2Fradio-reference-api%2Freleases%2Ftags%2Fnightly&query=%24.name&label=download&color=blue)](https://github.com/DSheirer/radio-reference-api/releases/tag/nightly)
[![Latest release](https://img.shields.io/github/v/release/DSheirer/radio-reference-api?label=release)](https://github.com/DSheirer/radio-reference-api/releases/latest)
[![Docs](https://img.shields.io/badge/docs-GitHub%20Pages-blue)](https://dsheirer.github.io/radio-reference-api/)
[![Javadoc](https://img.shields.io/github/v/release/DSheirer/radio-reference-api?label=javadoc&color=blue)](https://dsheirer.github.io/radio-reference-api/javadoc/)

A client library for accessing the web services methods available from the radioreference.com website.  The web service wsdl uses legacy rpc encoding which is not compatible with current versions of the wsimport utility and therefore requires the use of non-modular (pre-Java 9) apache axis 1.4 library and related dependencies.  This library uses the Java HttpClient and Faster Jackson annotated classes for serialization of SOAP messages.  SOAP implementation and serialization are largely hidden from the user via the public methods available in the RadioReferenceService class.

The [javadoc](https://dsheirer.github.io/radio-reference-api/javadoc/) documents the API of the latest release.

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

The service is thread safe.  Requests time out after 10 seconds connecting and 30 seconds waiting for the response;
use `new RadioReferenceService(authorizationInformation, connectTimeout, requestTimeout)` to change the limits.

Any call can run in the background on a virtual thread.  Cancelling the returned future aborts the HTTP request:

    CompletableFuture<List<SearchFrequencyResult>> future =
        service.async(s -> s.searchCountyFrequencies(countyId, 460.0375));

Frequencies are in MHz, as the service provides them.  Use `getDownlinkHz()`, `getUplinkHz()` and
`getFrequencyHz()`, or `FrequencyConversion`, for whole Hz values rounded to the nearest Hz.

Counties, agencies, frequency subcategories, trunked systems, sites and talkgroup categories provide their coverage
area with `getCoverage()`: the center, the range (a radius in miles) and any outline rectangles, with distance and
containment checks.

    Coverage coverage = service.getCountyInfo(countyId).getCoverage();
    double kilometers = coverage.distanceToCoverageKilometers(latitude, longitude); //0 inside the coverage area

Errors are categorized by `RadioReferenceException.getErrorType()`: `AUTHENTICATION`, `APPLICATION_KEY`,
`SUBSCRIPTION`, `INVALID_REQUEST` (e.g. an id that doesn't exist), `RATE_LIMITED`, `SERVICE_ERROR`, `TIMEOUT`,
`NETWORK`, `INTERRUPTED` or `UNKNOWN`.  `isRetryable()` indicates if the same request could succeed later, and
`getRetryAfter()` returns the delay the service asked for, when it sends one.

    catch(RadioReferenceException e)
    {
        if(e.isRetryable())
        {
            //Back off and try again later
        }
        else if(e.getErrorType() == RadioReferenceException.ErrorType.AUTHENTICATION)
        {
            //Ask the user for their username and password
        }
    }

`RadioReferenceJson` saves and loads the data types as JSON, e.g. to cache results on disk.  Compare a saved copy's
`getLastUpdated()` with the current value from the service to tell if it is stale.

    RadioReferenceJson.write(path, frequencies);
    List<Frequency> cached = RadioReferenceJson.read(path, new TypeReference<List<Frequency>>() {});

### WSDL
`src/main/wsdl/radioreference-v18.wsdl` is the web service definition that this library implements.  The nightly
workflow compares it with the live WSDL and fails, showing the differences in the job summary, when RadioReference
changes the API.

### Releases
Each version tag (e.g. `v18.0.0`) is published as a [GitHub release](https://github.com/DSheirer/radio-reference-api/releases)
with the library, sources and javadoc jars and a `SHA256SUMS` file.  A rolling `nightly` pre-release is built from
`master` after each merge.

To release, push a tag with the version: `v18.<minor>.<patch>`, or e.g. `v18.2.0-rc1` for a pre-release.  The
tag's major version has to match the major version of `version` in `gradle.properties`, which follows the
RadioReference API version; that file's version is otherwise only used for local and nightly builds.

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
        implementation 'io.github.dsheirer:radio-reference-api:18.1.0'
    }

### Building
Requires JDK 25 or newer: `./gradlew build`.

The build runs offline tests that check the library against the committed WSDL: every operation, parameter name and
order, and every field of every response type, using generated sample responses and recorded real responses in
`src/test/resources/responses`.

Tests tagged `live` call the radioreference.com service and only run with `./gradlew test -PliveTests`, using
`radio.reference.user` and `radio.reference.password` from your `gradle.properties`.
