package spotify.project.exception;

public class InvalidPasswordChangeRequestException extends RuntimeException {
	public InvalidPasswordChangeRequestException() {
		super(Messages.INVALID_PASSWORD_CHANGE_REQUEST);
	}
}
