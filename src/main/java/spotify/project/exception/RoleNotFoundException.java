package spotify.project.exception;

public class RoleNotFoundException extends RuntimeException {
	public RoleNotFoundException() {
		super(Messages.USER_ALREADY_EXISTS);
	}
}
