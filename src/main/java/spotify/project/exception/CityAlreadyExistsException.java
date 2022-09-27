package spotify.project.exception;

public class CityAlreadyExistsException extends RuntimeException{

	public CityAlreadyExistsException(){
		super(Messages.CITY_ALREADY_EXISTS);
	}
}
