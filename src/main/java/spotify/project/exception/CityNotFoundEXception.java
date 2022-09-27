package spotify.project.exception;

public class CityNotFoundEXception extends RuntimeException{
	public CityNotFoundEXception() {
		super(Messages.CITY_NOT_FOUND);
	}
}
