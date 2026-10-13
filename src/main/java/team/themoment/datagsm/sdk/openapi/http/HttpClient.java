package team.themoment.datagsm.sdk.openapi.http;

import java.util.Map;

/**
 * HTTP 클라이언트 추상화 인터페이스
 *
 * <p>쓰기 메서드({@link #post}, {@link #put}, {@link #delete})는 기본 구현이 {@link UnsupportedOperationException}을
 * 던지므로, 읽기 전용 커스텀 구현은 수정 없이 계속 사용할 수 있습니다.</p>
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
     * POST 요청
     *
     * @param url 요청 URL
     * @param headers 헤더
     * @param jsonBody JSON 요청 본문 (본문이 없으면 null)
     * @return 응답 본문 (본문이 없으면 빈 문자열)
     */
    default String post(String url, Map<String, String> headers, String jsonBody) {
        throw new UnsupportedOperationException("POST is not supported by " + getClass().getName());
    }

    /**
     * PUT 요청
     *
     * @param url 요청 URL
     * @param headers 헤더
     * @param jsonBody JSON 요청 본문
     * @return 응답 본문 (본문이 없으면 빈 문자열)
     */
    default String put(String url, Map<String, String> headers, String jsonBody) {
        throw new UnsupportedOperationException("PUT is not supported by " + getClass().getName());
    }

    /**
     * DELETE 요청
     *
     * @param url 요청 URL
     * @param headers 헤더
     * @return 응답 본문 (본문이 없으면 빈 문자열)
     */
    default String delete(String url, Map<String, String> headers) {
        throw new UnsupportedOperationException("DELETE is not supported by " + getClass().getName());
    }

    /**
     * 리소스 정리
     */
    void close();
}
