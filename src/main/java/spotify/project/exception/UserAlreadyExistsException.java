package spotify.project.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException() {
		super(Messages.ROLE_NOT_FOUND);
	}
}
