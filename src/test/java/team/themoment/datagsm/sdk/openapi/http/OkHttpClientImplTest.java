package team.themoment.datagsm.sdk.openapi.http;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team.themoment.datagsm.sdk.openapi.exception.BadRequestException;
import team.themoment.datagsm.sdk.openapi.exception.ConflictException;
import team.themoment.datagsm.sdk.openapi.exception.DataGsmException;
import team.themoment.datagsm.sdk.openapi.exception.NotFoundException;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OkHttpClientImplTest {
    private static final Map<String, String> HEADERS = Map.of("X-API-KEY", "test-key");

    private MockWebServer server;
    private OkHttpClientImpl client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
        client = new OkHttpClientImpl();
    }

    @AfterEach
    void tearDown() throws IOException {
        client.close();
        server.shutdown();
    }

    private String url(String path) {
        return server.url(path).toString();
    }

    @Test
    void get_skipsNullQueryParameters() throws InterruptedException {
        server.enqueue(new MockResponse().setBody("{}"));
        Map<String, String> params = new LinkedHashMap<>();
        params.put("clubStatus", "ACTIVE");
        params.put("clubName", null);

        client.get(url("/v1/clubs"), HEADERS, params);

        RecordedRequest request = server.takeRequest();
        assertEquals("GET", request.getMethod());
        assertEquals("/v1/clubs?clubStatus=ACTIVE", request.getPath());
        assertEquals("test-key", request.getHeader("X-API-KEY"));
    }

    @Test
    void post_sendsJsonBody() throws InterruptedException {
        server.enqueue(new MockResponse().setBody("{\"data\":null}"));

        String response = client.post(url("/v1/projects"), HEADERS, "{\"name\":\"x\"}");

        RecordedRequest request = server.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals("{\"name\":\"x\"}", request.getBody().readUtf8());
        assertTrue(request.getHeader("Content-Type").startsWith("application/json"));
        assertEquals("{\"data\":null}", response);
    }

    @Test
    void post_withoutBody_sendsEmptyJsonRequest() throws InterruptedException {
        server.enqueue(new MockResponse().setResponseCode(200));

        client.post(url("/v1/projects/1/reactivate"), HEADERS, null);

        RecordedRequest request = server.takeRequest();
        assertEquals("POST", request.getMethod());
        assertEquals(0, request.getBodySize());
    }

    @Test
    void put_sendsJsonBody() throws InterruptedException {
        server.enqueue(new MockResponse().setBody("{}"));

        client.put(url("/v1/students/1"), HEADERS, "{\"name\":\"y\"}");

        RecordedRequest request = server.takeRequest();
        assertEquals("PUT", request.getMethod());
        assertEquals("{\"name\":\"y\"}", request.getBody().readUtf8());
    }

    @Test
    void delete_returnsEmptyStringOnNoContent() throws InterruptedException {
        server.enqueue(new MockResponse().setResponseCode(204));

        String response = client.delete(url("/v1/clubs/1"), HEADERS);

        RecordedRequest request = server.takeRequest();
        assertEquals("DELETE", request.getMethod());
        assertEquals("", response);
    }

    @Test
    void notFound_throwsNotFoundException() {
        server.enqueue(new MockResponse().setResponseCode(404).setBody("{\"message\":\"없음\"}"));

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> client.delete(url("/v1/projects/99"), HEADERS));
        assertEquals(404, e.getStatusCode());
    }

    @Test
    void conflict_throwsConflictException() {
        server.enqueue(new MockResponse().setResponseCode(409));

        ConflictException e = assertThrows(ConflictException.class,
                () -> client.post(url("/v1/projects"), HEADERS, "{}"));
        assertEquals(409, e.getStatusCode());
        assertEquals("HTTP 409 error", e.getMessage());
    }

    @Test
    void badRequest_throwsBadRequestException() {
        server.enqueue(new MockResponse().setResponseCode(400).setBody("bad"));

        assertThrows(BadRequestException.class, () -> client.put(url("/v1/students/1"), HEADERS, "{}"));
    }

    @Test
    void unmappedStatus_throwsDataGsmExceptionWithStatusCode() {
        server.enqueue(new MockResponse().setResponseCode(418));

        DataGsmException e = assertThrows(DataGsmException.class,
                () -> client.get(url("/v1/students"), HEADERS, Map.of()));
        assertEquals(418, e.getStatusCode());
    }

    @Test
    void defaultWriteMethods_areUnsupportedForReadOnlyImplementations() {
        HttpClient readOnly = new HttpClient() {
            @Override
            public String get(String url, Map<String, String> headers, Map<String, String> queryParams) {
                return "";
            }

            @Override
            public void close() {
            }
        };

        assertThrows(UnsupportedOperationException.class, () -> readOnly.post("http://x", HEADERS, "{}"));
        assertThrows(UnsupportedOperationException.class, () -> readOnly.put("http://x", HEADERS, "{}"));
        assertThrows(UnsupportedOperationException.class, () -> readOnly.delete("http://x", HEADERS));
    }
}
