package spotify.project.exception;

public class RoleAlreadyExistsException extends RuntimeException {
	public RoleAlreadyExistsException() {
		super(Messages.ROLE_ALREADY_EXISTS);
	}
}
