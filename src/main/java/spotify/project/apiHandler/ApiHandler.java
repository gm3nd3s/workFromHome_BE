package spotify.project.apiHandler;

import spotify.project.command.CityDto;
import spotify.project.command.CountriesDto;
import spotify.project.command.CountryDto;
import spotify.project.command.CreateCityDto;

import java.util.List;

public interface ApiHandler {

	CreateCityDto cityDto(String city);


}
