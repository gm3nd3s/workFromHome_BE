package spotify.project.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException() {
        super(String.format(ErrorMessages.USER_ALREADY_EXISTS));
    }
}
