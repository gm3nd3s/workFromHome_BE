package spotify.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.project.command.CityDto;
import spotify.project.services.TeleportService;

@RestController
@RequestMapping("/api")
public class TeleportController {

	private TeleportService teleportService;

	public TeleportController(TeleportService teleportService) {
		this.teleportService = teleportService;
	}

	@GetMapping("/city/{cityName}")
	public CityDto getCityDto(@PathVariable String cityName) {
		return teleportService.getCityDto(cityName);
	}
}
