package spotify.project.exception;

public class NullException extends RuntimeException{
	public NullException() {
		super(Messages.NULL_EXCEPTION);
	}

}
