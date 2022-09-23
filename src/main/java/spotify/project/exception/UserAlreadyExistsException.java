package spotify.project.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super(String.format(ErrorMessages.ROLE_NOT_FOUND));
    }
}
