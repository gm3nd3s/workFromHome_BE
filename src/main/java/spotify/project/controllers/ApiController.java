package spotify.project.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.project.command.CityUrbanAreaDto;
import spotify.project.command.CreateCityDto;
import spotify.project.services.CityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

	private CityServiceImpl cityService;

	public ApiController(CityServiceImpl cityService) {
		this.cityService = cityService;
	}

	@GetMapping("/city/{cityName}")
	public CreateCityDto getCityDto(@PathVariable String cityName) {
		return cityService.getCityDto(cityName);
	}

	@GetMapping("/cities")
	public List<CityUrbanAreaDto> getCities() {
		return cityService.getCities();
	}

	@GetMapping("/citas/{category}/{minimum_score}")
	public ResponseEntity<List<String>> getCitiesWithMinimumScoreForCategory(
			@PathVariable("category") String category,
			@PathVariable("minimum_score") Integer minimumScore
	) {
		return new ResponseEntity<>(cityService.getCitiesWithMinimumScoreForCategory(category, minimumScore), HttpStatus.OK);
	}
}
