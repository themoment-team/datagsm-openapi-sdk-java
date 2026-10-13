package team.themoment.datagsm.sdk.openapi.client;

import com.fasterxml.jackson.core.type.TypeReference;
import team.themoment.datagsm.sdk.openapi.exception.DataGsmException;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.JsonUtil;
import team.themoment.datagsm.sdk.openapi.model.CommonApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * API 구현체 공통 처리: 인증 헤더, 요청 직렬화, {@link CommonApiResponse} 언래핑
 */
abstract class AbstractApiClient {
    private static final String API_KEY_HEADER = "X-API-KEY";

    private final HttpClient httpClient;
    private final String apiKey;
    private final String baseUrl;

    AbstractApiClient(HttpClient httpClient, String apiKey, String baseUrl) {
        this.httpClient = httpClient;
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    <T> T get(String path, Map<String, String> queryParams, TypeReference<CommonApiResponse<T>> type) {
        return unwrap(httpClient.get(baseUrl + path, createHeaders(), queryParams), type);
    }

    <T> T post(String path, Object body, TypeReference<CommonApiResponse<T>> type) {
        return unwrap(httpClient.post(baseUrl + path, createHeaders(), toJson(body)), type);
    }

    <T> T put(String path, Object body, TypeReference<CommonApiResponse<T>> type) {
        return unwrap(httpClient.put(baseUrl + path, createHeaders(), toJson(body)), type);
    }

    /**
     * 응답 데이터가 없는(Unit) POST 요청. 204 또는 빈 본문도 성공으로 처리합니다.
     */
    void postWithoutResult(String path, Object body) {
        httpClient.post(baseUrl + path, createHeaders(), toJson(body));
    }

    /**
     * 응답 데이터가 없는(Unit) DELETE 요청. 204 또는 빈 본문도 성공으로 처리합니다.
     */
    void delete(String path) {
        httpClient.delete(baseUrl + path, createHeaders());
    }

    static String idPath(String prefix, Long id) {
        Objects.requireNonNull(id, "id is required");
        return prefix + "/" + id;
    }

    static <T> T requireBody(T body, String name) {
        return Objects.requireNonNull(body, name + " is required");
    }

    private static String toJson(Object body) {
        return body != null ? JsonUtil.toJson(body) : null;
    }

    private static <T> T unwrap(String responseBody, TypeReference<CommonApiResponse<T>> type) {
        if (responseBody == null || responseBody.isBlank()) {
            throw new DataGsmException("Empty response body");
        }
        return JsonUtil.fromJson(responseBody, type).getData();
    }

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(API_KEY_HEADER, apiKey);
        return headers;
    }
}
