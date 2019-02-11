## Java Client Library for radioreference.com Web Services API

This client library for accessing the web services methods available from the radioreference.com website.  This web service wsdl uses legacy rpc encoding which is not compatible with current wsimport utility and therefore requires the use of non-modular apache axis 1.4 library and related dependencies.  This library uses the Java 11 HttpClient and Faster Jackson annotated classes for serialization of SOAP messages.  SOAP implementation and serialization are largely hidden from the user via the public methods available in the RadioReferenceService class.

### Compatibility
* SOAP Web Service API Version 15
* See https://wiki.radioreference.com/index.php/API

### Requirements
* Java 11+

### Usage
    AuthorizationInformation authorizationInformation = new AuthorizationInformation("apikey", "usernamer", "password");

    try
    {
        RadioReferenceService service = new RadioReferenceService(authorizationInformation);
        UserInfo userInfo = service.getUserInfo();
    }
    catch(RadioReferenceException e)
    {
        //Handle error
    }
