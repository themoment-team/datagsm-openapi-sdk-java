package team.themoment.datagsm.sdk.openapi.client;

import team.themoment.datagsm.sdk.openapi.http.HttpClient;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * 요청을 기록하고 미리 준비한 응답을 순서대로 돌려주는 테스트용 HttpClient
 */
class RecordingHttpClient implements HttpClient {
    static final class Call {
        final String method;
        final String url;
        final Map<String, String> headers;
        final Map<String, String> queryParams;
        final String body;

        Call(String method, String url, Map<String, String> headers, Map<String, String> queryParams, String body) {
            this.method = method;
            this.url = url;
            this.headers = headers;
            this.queryParams = queryParams;
            this.body = body;
        }
    }

    private final Deque<String> responses = new ArrayDeque<>();
    private final List<Call> calls = new ArrayList<>();

    RecordingHttpClient respond(String... bodies) {
        Collections.addAll(responses, bodies);
        return this;
    }

    List<Call> calls() {
        return calls;
    }

    Call lastCall() {
        return calls.get(calls.size() - 1);
    }

    private String record(String method, String url, Map<String, String> headers,
                          Map<String, String> queryParams, String body) {
        calls.add(new Call(method, url, headers, queryParams, body));
        return responses.isEmpty() ? "" : responses.poll();
    }

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> queryParams) {
        return record("GET", url, headers, queryParams, null);
    }

    @Override
    public String post(String url, Map<String, String> headers, String jsonBody) {
        return record("POST", url, headers, null, jsonBody);
    }

    @Override
    public String put(String url, Map<String, String> headers, String jsonBody) {
        return record("PUT", url, headers, null, jsonBody);
    }

    @Override
    public String delete(String url, Map<String, String> headers) {
        return record("DELETE", url, headers, null, null);
    }

    @Override
    public void close() {
    }
}
