package team.themoment.datagsm.sdk.openapi.exception;

/**
 * 409 Conflict - 이미 존재하는 리소스와 충돌함 (예: 중복된 이름)
 */
public class ConflictException extends DataGsmException {
    public ConflictException(String message) {
        super(message, 409);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, 409, cause);
    }
}
