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

import com.sun.net.httpserver.HttpServer;
import io.github.dsheirer.rrapi.type.AuthorizationInformation;
import io.github.dsheirer.rrapi.type.Country;
import io.github.dsheirer.rrapi.type.Tag;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Request timeouts with the real HTTP client against a local server, async calls with cancellation, and thread safe
 * caching of the lookup maps.
 */
public class ConcurrencyAndTimeoutTest
{
    private static final AuthorizationInformation AUTH = new AuthorizationInformation("key", "user", "password");
    private static Wsdl sWsdl;
    private static String sCountryListResponse;
    private HttpServer mServer;

    @BeforeAll
    static void load() throws Exception
    {
        sWsdl = Wsdl.load();
        sCountryListResponse = Files.readString(Path.of("src/test/resources/responses/getCountryList.xml"));
    }

    @AfterEach
    void stopServer()
    {
        if(mServer != null)
        {
            mServer.stop(0);
        }
    }

    /**
     * Starts a local server that answers every request after the delay with the body
     * @return URI of the server
     */
    private URI startServer(Duration delay, String body, AtomicReference<String> requestBody,
                            AtomicReference<String> userAgent) throws IOException
    {
        mServer = HttpServer.create(new InetSocketAddress(InetAddress.getLoopbackAddress(), 0), 0);
        mServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        mServer.createContext("/soap2/", exchange -> {
            try
            {
                if(requestBody != null)
                {
                    requestBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
                }
                if(userAgent != null)
                {
                    userAgent.set(exchange.getRequestHeaders().getFirst("User-Agent"));
                }
                Thread.sleep(delay);
                byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "text/xml; charset=utf-8");
                exchange.sendResponseHeaders(200, bytes.length);
                try(OutputStream out = exchange.getResponseBody())
                {
                    out.write(bytes);
                }
            }
            catch(InterruptedException | IOException e)
            {
                //Client gave up
            }
            finally
            {
                exchange.close();
            }
        });
        mServer.start();
        return URI.create("http://127.0.0.1:" + mServer.getAddress().getPort() + "/soap2/");
    }

    private static RadioReferenceService service(URI uri, Duration requestTimeout) throws RadioReferenceException
    {
        return new RadioReferenceService(AUTH, RadioReferenceService.httpTransport(uri, Duration.ofSeconds(5), requestTimeout));
    }

    @Test
    void httpTransportPostsRequestAndParsesResponse() throws Exception
    {
        AtomicReference<String> requestBody = new AtomicReference<>();
        AtomicReference<String> userAgent = new AtomicReference<>();
        URI uri = startServer(Duration.ZERO, sCountryListResponse, requestBody, userAgent);

        List<Country> countries = service(uri, Duration.ofSeconds(5)).getCountries();

        Assertions.assertEquals(236, countries.size());
        Assertions.assertTrue(requestBody.get().contains("<getCountryList>"), "request body");
        Assertions.assertEquals("io.github.dsheirer.rrapi", userAgent.get());
    }

    @Test
    void slowResponseTimesOut() throws Exception
    {
        URI uri = startServer(Duration.ofSeconds(5), sCountryListResponse, null, null);
        RadioReferenceService service = service(uri, Duration.ofMillis(300));

        long start = System.nanoTime();
        RadioReferenceException exception = Assertions.assertThrows(RadioReferenceException.class, service::getCountries);
        long elapsedMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);

        Assertions.assertInstanceOf(HttpTimeoutException.class, exception.getCause());
        Assertions.assertTrue(exception.getMessage().contains("Timed out"), exception.getMessage());
        Assertions.assertTrue(elapsedMillis < 3000, "timed out after " + elapsedMillis + " ms");
    }

    @Test
    void invalidTimeoutsAreRejected()
    {
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new RadioReferenceService(AUTH, Duration.ZERO, Duration.ofSeconds(1)));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new RadioReferenceService(AUTH, Duration.ofSeconds(1), Duration.ofSeconds(-1)));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> new RadioReferenceService(AUTH, null, Duration.ofSeconds(1)));
    }

    @Test
    void asyncCallRunsOnVirtualThreadAndCompletes() throws Exception
    {
        AtomicBoolean virtual = new AtomicBoolean();
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            virtual.set(Thread.currentThread().isVirtual());
            return new SoapTransport.Response(200, sCountryListResponse);
        });

        List<Country> countries = service.async(RadioReferenceService::getCountries).get(5, TimeUnit.SECONDS);

        Assertions.assertEquals(236, countries.size());
        Assertions.assertTrue(virtual.get(), "call ran on a virtual thread");
    }

    @Test
    void asyncCallCompletesExceptionallyWithServiceError() throws Exception
    {
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            throw new IOException("connection reset");
        });

        ExecutionException exception = Assertions.assertThrows(ExecutionException.class,
            () -> service.async(RadioReferenceService::getCountries).get(5, TimeUnit.SECONDS));

        Assertions.assertInstanceOf(RadioReferenceException.class, exception.getCause());
    }

    @Test
    void cancellingAsyncCallInterruptsTheRequest() throws Exception
    {
        CountDownLatch started = new CountDownLatch(1);
        CountDownLatch interrupted = new CountDownLatch(1);
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            started.countDown();
            try
            {
                Thread.sleep(Duration.ofSeconds(30));
            }
            catch(InterruptedException e)
            {
                interrupted.countDown();
                throw e;
            }
            return new SoapTransport.Response(200, sCountryListResponse);
        });

        CompletableFuture<List<Country>> future = service.async(RadioReferenceService::getCountries);
        Assertions.assertTrue(started.await(5, TimeUnit.SECONDS), "request started");

        Assertions.assertTrue(future.cancel(true));

        Assertions.assertTrue(interrupted.await(5, TimeUnit.SECONDS), "request was interrupted");
        Assertions.assertTrue(future.isCancelled());
        Assertions.assertThrows(CancellationException.class, future::get);
    }

    @Test
    void cancellingAsyncCallAbortsRealHttpRequest() throws Exception
    {
        URI uri = startServer(Duration.ofSeconds(30), sCountryListResponse, null, null);
        RadioReferenceService service = service(uri, Duration.ofSeconds(60));
        AtomicReference<Throwable> failure = new AtomicReference<>();
        CountDownLatch finished = new CountDownLatch(1);

        CompletableFuture<List<Country>> future = service.async(s -> {
            try
            {
                return s.getCountries();
            }
            catch(RadioReferenceException e)
            {
                failure.set(e.getCause());
                throw e;
            }
            finally
            {
                finished.countDown();
            }
        });

        Thread.sleep(300); //Let the request reach the server
        future.cancel(true);

        Assertions.assertTrue(finished.await(5, TimeUnit.SECONDS), "HTTP request was aborted instead of waiting 30 s");
        Assertions.assertInstanceOf(InterruptedException.class, failure.get());
    }

    @Test
    void concurrentCallersShareOneLookup() throws Exception
    {
        AtomicInteger requests = new AtomicInteger();
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            requests.incrementAndGet();
            Thread.sleep(200); //Keep the load in progress while the other callers arrive
            return new SoapTransport.Response(200, sWsdl.sampleResponse("getTag"));
        });

        int callers = 16;
        CountDownLatch go = new CountDownLatch(1);
        List<Future<Map<Integer,Tag>>> results = new ArrayList<>();

        try(ExecutorService executor = Executors.newFixedThreadPool(callers))
        {
            for(int x = 0; x < callers; x++)
            {
                results.add(executor.submit(() -> {
                    go.await();
                    return service.getTagsMap();
                }));
            }

            go.countDown();
            Map<Integer,Tag> first = results.get(0).get(10, TimeUnit.SECONDS);

            for(Future<Map<Integer,Tag>> result: results)
            {
                Assertions.assertSame(first, result.get(10, TimeUnit.SECONDS), "every caller gets the cached map");
            }
        }

        Assertions.assertEquals(1, requests.get(), "web service calls");
    }

    @Test
    void cachedMapsAreReadOnlyAndClearTagMapReloads() throws Exception
    {
        AtomicInteger requests = new AtomicInteger();
        RadioReferenceService service = new RadioReferenceService(AUTH, xml -> {
            requests.incrementAndGet();
            return new SoapTransport.Response(200, sWsdl.sampleResponse("getTag"));
        });

        Map<Integer,Tag> tags = service.getTagsMap();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> tags.put(99, new Tag()));
        Assertions.assertSame(tags, service.getTagsMap());

        service.clearTagMap();
        Assertions.assertNotSame(tags, service.getTagsMap());
        Assertions.assertEquals(2, requests.get());
    }

    @Test
    void failedLookupIsNotCached() throws Exception
    {
        List<String> responses = Collections.synchronizedList(new ArrayList<>(List.of(
            "<html>error</html>", sWsdl.sampleResponse("getMode"))));
        RadioReferenceService service = new RadioReferenceService(AUTH,
            xml -> new SoapTransport.Response(200, responses.remove(0)));

        Assertions.assertThrows(RadioReferenceException.class, service::getModesMap);
        Assertions.assertFalse(service.getModesMap().isEmpty(), "second call loads the map");
    }
}
