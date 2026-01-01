package team.themoment.datagsm.sdk.openapi.http;

import java.util.Map;

/**
 * HTTP 클라이언트 추상화 인터페이스
 */
public interface HttpClient {
    /**
     * GET 요청
     *
     * @param url 요청 URL
     * @param headers 헤더
     * @param queryParams 쿼리 파라미터
     * @return 응답 본문
     */
    String get(String url, Map<String, String> headers, Map<String, String> queryParams);

    /**
     * 리소스 정리
     */
    void close();
}