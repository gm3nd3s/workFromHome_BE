package spotify.project.exception;

public class RoleAlreadyExistsException extends RuntimeException{
    public RoleAlreadyExistsException() {
        super(String.format(ErrorMessages.ROLE_ALREADY_EXISTS));
    }
}
