package spotify.project.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super(Messages.USER_NOT_FOUND);
	}

}
