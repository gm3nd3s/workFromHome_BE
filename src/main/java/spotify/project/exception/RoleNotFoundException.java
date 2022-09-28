package spotify.project.exception;

public class RoleNotFoundException extends RuntimeException {
	public RoleNotFoundException() {
		super(Messages.ROLE_NOT_FOUND);
	}
}
