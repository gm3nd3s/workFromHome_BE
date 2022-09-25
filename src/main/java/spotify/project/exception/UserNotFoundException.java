package spotify.project.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super(String.format(ErrorMessages.USER_NOT_FOUND));
	}

}
