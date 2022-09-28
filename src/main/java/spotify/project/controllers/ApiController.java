package spotify.project.controllers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.project.command.CityDtoWithCategory;
import spotify.project.command.CityUrbanAreaDto;
import spotify.project.command.CreateCityDto;
import spotify.project.services.CityServiceImpl;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/teleportApi")
public class ApiController {

	private final CityServiceImpl cityService;

	public ApiController(CityServiceImpl cityService) {
		this.cityService = cityService;
	}


	@GetMapping("/city/{cityName}")
	public ResponseEntity<CreateCityDto> getCityDto(@PathVariable String cityName) {
		return new ResponseEntity<>(cityService.getCityDto(cityName), HttpStatus.OK);
	}


	@GetMapping("/cities")
	public ResponseEntity<List<CityUrbanAreaDto>> getCities() {
		return new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
	}

	@GetMapping("/citiesFromApi/{category}/{minimum_score}")
	public ResponseEntity<List<CityDtoWithCategory>> getCitiesWithMinimumScoreForCategory(
			@PathVariable("category") String category,
			@PathVariable("minimum_score") Integer minimumScore
	) {
		return new ResponseEntity<>(cityService.getCitiesWithMinimumScoreForCategory(category, minimumScore), HttpStatus.OK);
	}
}
