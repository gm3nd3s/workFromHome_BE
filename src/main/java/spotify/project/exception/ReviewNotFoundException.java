package spotify.project.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException() {
        super(Messages.REVIEW_NOT_FOUND);
    }
}
