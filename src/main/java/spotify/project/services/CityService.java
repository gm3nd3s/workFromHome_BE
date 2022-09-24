package spotify.project.services;

import org.springframework.stereotype.Service;
import spotify.project.apiHandler.ApiHandler;
import spotify.project.command.CityDto;
import spotify.project.command.CountryDto;
import spotify.project.command.CreateCityDto;

import java.util.List;

@Service
public class CityService {



	private final ApiHandler apiHandler;

	public CityService(ApiHandler apiHandler) {
		this.apiHandler = apiHandler;
	}

	public CreateCityDto getCityDto(String cityName) {
		return apiHandler.cityDto(cityName);}
}
