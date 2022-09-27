package spotify.project.exception;

public class CityNotVisitedException extends RuntimeException {
    public CityNotVisitedException() {
        super(Messages.CITY_NOT_VISITED);
    }
}
