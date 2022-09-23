package spotify.project.exception;

public class InvalidPasswordChangeRequestException extends RuntimeException {
    public InvalidPasswordChangeRequestException() {
        super(ErrorMessages.INVALID_PASSWORD_CHANGE_REQUEST);
    }
}
