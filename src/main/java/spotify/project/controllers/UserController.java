package spotify.project.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spotify.project.command.*;
import spotify.project.models.Review;
import spotify.project.models.Role;
import spotify.project.services.TokenService;
import spotify.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static spotify.project.utils.PrintErrors.printErrors;

@RestController
@RequestMapping("/api")
public class UserController {
	TokenService tokenService;
	UserService userService;

	public UserController(UserService userService, TokenService tokenService) {
		this.userService = userService;
		this.tokenService = tokenService;
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
		return ResponseEntity.ok().body(userService.findUserDtoByUsername(username));
	}

	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserDto user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return printErrors(bindingResult);
		}
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/role")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
	}

	@PutMapping("/role={name}/user={username}")
	public ResponseEntity<?> addRoleToUser(@PathVariable String name, @PathVariable String username) {
		return new ResponseEntity<>(userService.addRoleToUser(username, name), HttpStatus.OK);
	}

	@DeleteMapping("/delete/user={username}")
	public void deleteUser(@PathVariable String username) {
		if (username == null) {
			throw new RuntimeException("You need to give a proper username");
		}
		userService.deleteUser(username);
	}


	@DeleteMapping("/delete/role={roleType}")
	public void deleteRole(@PathVariable String roleType) {
		if (roleType == null) {
			throw new RuntimeException("You need to give a proper username");
		}
		userService.deleteRole(roleType);
	}

	@GetMapping("/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		tokenService.refreshToken(request, response);
	}

	@GetMapping("/allCities")
	public List<CityDto> getAllCitiesInDB() {
		return userService.getAllCitiesInDb();
	}

	@GetMapping("/allCities/{username}")
	public ResponseEntity<List<CityDto>> getAllCitiesVisitedByUser(@PathVariable String username) {
		return new ResponseEntity<>(userService.getAllCitiesVisitedByUser(username), HttpStatus.OK);
	}

	@GetMapping("/livingCity/{username}")
	public ResponseEntity<CityDto> getUserLivingCity(@PathVariable String username) {
		return new ResponseEntity<>(userService.getUserLivingCity(username), HttpStatus.OK);
	}

	@GetMapping("/citiesOrdered")
	public ResponseEntity<List<CityDto>> getCitiesInDBOrdered() {
		return new ResponseEntity<>(userService.getCitiesInDBOrdered(), HttpStatus.OK);
	}

	@GetMapping("cities/{category}/{score}")
	public ResponseEntity<List<CityDtoWithCategory>> getCitiesWithCategoryBiggerThan(@PathVariable String category, @PathVariable Integer score) {
		return new ResponseEntity<>(userService.getCitiesWithCategoryBiggerThan(category, score), HttpStatus.OK);
	}

	@GetMapping("/cityDB/{cityName}")
	public CityDto getCityDtoByName(@PathVariable String cityName) { //criar DTO para enviar com info toda
		return userService.getCityDtoByName(cityName);
	}

	@PutMapping("/visited/{username}/{cityName}")
	public ResponseEntity<?> addCityToUser(@PathVariable String username, @PathVariable String cityName) {
		return new ResponseEntity<>(userService.addCityToUser(username, cityName), HttpStatus.OK);
	}


	@PostMapping("/review/{username}/{cityName}")
	public ResponseEntity<?> addReviewToCityVisited(@RequestBody CreateReviewDto review, @PathVariable String username, @PathVariable String cityName) {
		return new ResponseEntity<>(userService.addReviewToCityVisited(review, username, cityName), HttpStatus.OK);
	}

	@PutMapping("/livingCity/{username}/{cityName}")
	public ResponseEntity<?> addLivingCityToUser(@PathVariable String username, @PathVariable String cityName) {
		return new ResponseEntity<>(userService.addLivingCityToUser(username, cityName), HttpStatus.OK);
	}
}
