package spotify.project.apiHandler;

import spotify.project.command.CreateCityDto;

public interface ApiHandler {

	CreateCityDto cityDto(String city);
}
