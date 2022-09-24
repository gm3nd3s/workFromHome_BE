package spotify.project.services;

import org.springframework.stereotype.Service;
import spotify.project.apiHandler.ApiHandler;
import spotify.project.command.CityDto;

@Service
public class TeleportService {



	private final ApiHandler apiHandler;

	public TeleportService(ApiHandler apiHandler) {
		this.apiHandler = apiHandler;
	}

	public CityDto getCityDto(String cityName) {
		return apiHandler.cityDto(cityName);
	}
}
