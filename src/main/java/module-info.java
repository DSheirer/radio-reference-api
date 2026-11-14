/**
 * Java client for the radioreference.com SOAP web service, API version 18.
 *
 * <p>{@link io.github.dsheirer.rrapi.RadioReferenceService} is the entry point: each method sends one web service
 * operation, or a few for the cached lookups, and returns the data types in {@link io.github.dsheirer.rrapi.type}.
 * The web service requires a radioreference.com premium subscription and an application key.</p>
 *
 * <pre>{@code
 * AuthorizationInformation credentials = new AuthorizationInformation("appKey", "username", "password");
 * RadioReferenceService service = new RadioReferenceService(credentials);
 *
 * try
 * {
 *     CountyInfo county = service.getCountyInfo(countyId);
 *     List<SearchFrequencyResult> results = service.searchCountyFrequencies(countyId, 460.0375);
 * }
 * catch(RadioReferenceException e)
 * {
 *     if(e.isRetryable())
 *     {
 *         //Back off and try again later
 *     }
 * }
 * }</pre>
 *
 * <ul>
 *     <li>The service is thread safe, with connect and request timeouts, and
 *     {@link io.github.dsheirer.rrapi.RadioReferenceService#async(io.github.dsheirer.rrapi.RadioReferenceService.ServiceCall)
 *     async()} runs any call in the background with cancellation.</li>
 *     <li>{@link io.github.dsheirer.rrapi.RadioReferenceException#getErrorType()} categorizes errors, e.g. invalid
 *     credentials, an invalid id or a temporary service error.</li>
 *     <li>Frequencies are in MHz as the service provides them; the {@code get...Hz()} methods and
 *     {@link io.github.dsheirer.rrapi.type.FrequencyConversion} give whole Hz.</li>
 *     <li>{@link io.github.dsheirer.rrapi.type.Coverage} describes the coverage area of counties, agencies,
 *     subcategories, systems and sites, with distance checks.</li>
 *     <li>{@link io.github.dsheirer.rrapi.RadioReferenceJson} saves the data types as JSON, e.g. to cache them on
 *     disk.</li>
 * </ul>
 *
 * <p>The {@link io.github.dsheirer.rrapi.request} and {@link io.github.dsheirer.rrapi.response} packages hold the
 * SOAP messages of the WSDL operations.  Applications don't normally use them directly.</p>
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