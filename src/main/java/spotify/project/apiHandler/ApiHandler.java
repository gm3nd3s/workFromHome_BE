package spotify.project.apiHandler;

import spotify.project.command.CreateCityDto;
import spotify.project.command.UrbanAreaDto;

public interface ApiHandler {

	CreateCityDto cityDto(String city);

	UrbanAreaDto getCities();
}
