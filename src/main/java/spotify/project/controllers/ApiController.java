package spotify.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.project.command.CreateCityDto;
import spotify.project.services.CityService;
import spotify.project.services.CountryService;

@RestController
@RequestMapping("/api")
public class ApiController {

	private CityService cityService;
	private CountryService countryService;
	public ApiController( CityService cityService) {
		this.cityService = cityService;

	}

	@GetMapping("/city/{cityName}")
	public CreateCityDto getCityDto(@PathVariable String cityName) {
		return cityService.getCityDto(cityName);
	}

	/*@GetMapping(value = "/countries")
	public CountriesDto getCountries(){
		return countryService.getCountryDto();
	}*/

}
