package team.themoment.datagsm.sdk.openapi.exception;

/**
 * 404 Not Found - 요청한 리소스를 찾을 수 없음
 */
public class NotFoundException extends DataGsmException {
    public NotFoundException(String message) {
        super(message, 404);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, 404, cause);
    }
}
