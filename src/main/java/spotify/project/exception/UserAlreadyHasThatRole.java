package spotify.project.exception;

public class UserAlreadyHasThatRole extends RuntimeException {
	public UserAlreadyHasThatRole() {
		super(String.format(ErrorMessages.USER_ALREADY_HAS_THAT_ROLE));
	}
}
