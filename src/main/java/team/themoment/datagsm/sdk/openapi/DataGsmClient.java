package team.themoment.datagsm.sdk.openapi;

import team.themoment.datagsm.sdk.openapi.client.*;
import team.themoment.datagsm.sdk.openapi.http.HttpClient;
import team.themoment.datagsm.sdk.openapi.http.OkHttpClientImpl;

/**
 * DataGSM OpenAPI 메인 클라이언트
 */
public class DataGsmClient implements AutoCloseable {
    private static final String DEFAULT_BASE_URL = "https://openapi.data.hellogsm.kr";

    private final HttpClient httpClient;
    private final StudentApi studentApi;
    private final ClubApi clubApi;
    private final NeisApi neisApi;
    private final ProjectApi projectApi;

    private DataGsmClient(Builder builder) {
        this.httpClient = builder.httpClient != null ? builder.httpClient : new OkHttpClientImpl();

        String baseUrl = builder.baseUrl != null ? builder.baseUrl : DEFAULT_BASE_URL;

        this.studentApi = new StudentApiImpl(httpClient, builder.apiKey, baseUrl);
        this.clubApi = new ClubApiImpl(httpClient, builder.apiKey, baseUrl);
        this.neisApi = new NeisApiImpl(httpClient, builder.apiKey, baseUrl);
        this.projectApi = new ProjectApiImpl(httpClient, builder.apiKey, baseUrl);
    }

    /**
     * 학생 API 반환
     *
     * @return 학생 API
     */
    public StudentApi students() {
        return studentApi;
    }

    /**
     * 동아리 API 반환
     *
     * @return 동아리 API
     */
    public ClubApi clubs() {
        return clubApi;
    }

    /**
     * NEIS API 반환
     *
     * @return NEIS API
     */
    public NeisApi neis() {
        return neisApi;
    }

    /**
     * 프로젝트 API 반환
     *
     * @return 프로젝트 API
     */
    public ProjectApi projects() {
        return projectApi;
    }

    /**
     * 리소스 정리
     */
    @Override
    public void close() {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    /**
     * DataGsmClient 빌더
     *
     * @param apiKey API 키
     * @return 빌더
     */
    public static Builder builder(String apiKey) {
        return new Builder(apiKey);
    }

    /**
     * 빌더 클래스
     */
    public static class Builder {
        private final String apiKey;
        private String baseUrl;
        private HttpClient httpClient;

        private Builder(String apiKey) {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalArgumentException("API key is required");
            }
            this.apiKey = apiKey;
        }

        /**
         * 베이스 URL 설정 (선택)
         *
         * @param baseUrl 베이스 URL
         * @return 빌더
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * HTTP 클라이언트 설정 (선택)
         *
         * @param httpClient HTTP 클라이언트
         * @return 빌더
         */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        /**
         * DataGsmClient 생성
         *
         * @return DataGsmClient
         */
        public DataGsmClient build() {
            return new DataGsmClient(this);
        }
    }
}