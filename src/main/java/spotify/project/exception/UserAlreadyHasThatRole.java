package spotify.project.exception;

public class UserAlreadyHasThatRole extends RuntimeException {
	public UserAlreadyHasThatRole() {
		super(Messages.USER_ALREADY_HAS_THAT_ROLE);
	}
}
