package spotify.project.exception;

public class ReviewAlreadyExistsException extends RuntimeException {

	public ReviewAlreadyExistsException() {
		super(Messages.REVIEW_ALREADY_EXISTS);
	}
}
