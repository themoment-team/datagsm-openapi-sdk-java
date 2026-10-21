package team.themoment.datagsm.sdk.openapi.http;

import okhttp3.*;
import team.themoment.datagsm.sdk.openapi.exception.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp 기반 HTTP 클라이언트 구현
 */
public class OkHttpClientImpl implements HttpClient {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final long DEFAULT_TIMEOUT_SECONDS = 30;

    private final OkHttpClient client;

    public OkHttpClientImpl() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClientImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> queryParams) {
        HttpUrl.Builder urlBuilder = parseUrl(url).newBuilder();

        if (queryParams != null) {
            queryParams.forEach((key, value) -> {
                if (value != null) {
                    urlBuilder.addQueryParameter(key, value);
                }
            });
        }

        Request.Builder requestBuilder = new Request.Builder()
                .url(urlBuilder.build())
                .get();

        return execute(requestBuilder, headers);
    }

    @Override
    public String post(String url, Map<String, String> headers, String jsonBody) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(parseUrl(url))
                .post(toRequestBody(jsonBody));
        return execute(requestBuilder, headers);
    }

    @Override
    public String put(String url, Map<String, String> headers, String jsonBody) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(parseUrl(url))
                .put(toRequestBody(jsonBody));
        return execute(requestBuilder, headers);
    }

    @Override
    public String delete(String url, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(parseUrl(url))
                .delete();
        return execute(requestBuilder, headers);
    }

    private static HttpUrl parseUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }
        return httpUrl;
    }

    private static RequestBody toRequestBody(String jsonBody) {
        return RequestBody.create(jsonBody != null ? jsonBody : "", JSON);
    }

    private String execute(Request.Builder requestBuilder, Map<String, String> headers) {
        if (headers != null) {
            headers.forEach(requestBuilder::addHeader);
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            ResponseBody body = response.body();
            String responseBody = body != null ? body.string() : "";

            if (!response.isSuccessful()) {
                handleErrorResponse(response.code(), responseBody);
            }

            return responseBody;
        } catch (IOException e) {
            throw new DataGsmException("HTTP request failed: " + e.getMessage(), e);
        }
    }

    private void handleErrorResponse(int statusCode, String responseBody) {
        String message = responseBody != null && !responseBody.isEmpty()
                ? responseBody
                : "HTTP " + statusCode + " error";

        switch (statusCode) {
            case 400:
                throw new BadRequestException(message);
            case 401:
                throw new UnauthorizedException(message);
            case 403:
                throw new ForbiddenException(message);
            case 404:
                throw new NotFoundException(message);
            case 409:
                throw new ConflictException(message);
            case 429:
                throw new RateLimitException(message);
            case 500:
            case 502:
            case 503:
            case 504:
                throw new ServerErrorException(message);
            default:
                throw new DataGsmException(message, statusCode);
        }
    }

    @Override
    public void close() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }
}
