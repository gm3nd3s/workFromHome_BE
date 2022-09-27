package spotify.project.exception;

public class InvalidPasswordException extends RuntimeException {

	public InvalidPasswordException() {
		super(Messages.INVALID_PASSWORD);
	}
}
