package spotify.project.exception;

public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException() {
		super(String.format(ErrorMessages.INVALID_PASSWORD));
	}
}
