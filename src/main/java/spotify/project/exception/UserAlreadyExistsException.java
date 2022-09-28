package spotify.project.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException() {
		super(Messages.USER_ALREADY_EXISTS);
	}
}
