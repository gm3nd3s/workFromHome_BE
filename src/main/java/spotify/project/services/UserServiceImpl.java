package spotify.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spotify.project.command.*;
import spotify.project.exception.*;
import spotify.project.models.City;
import spotify.project.models.Review;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@EnableCaching
public class UserServiceImpl implements UserService {

	private final CityService cityService;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	private final ReviewService reviewService;


	@Override
	public UserDto registerUser(CreateUserDto user) {
		if (userExists(user.getUsername())) {
			throw new UserAlreadyExistsException();
		}
		log.info("Saving new user {} to database.", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return UserConverter
				.convertEntityToUserDto(userRepository.
						save(addRoleToUser(userRepository.
								save(UserConverter.
										convertCreateUserDtoToEntity(user)).getName(), "USER")));
	}

	@Override
	public Role saveRole(Role role) {
		if (roleExists(role.getRoleType())) {
			throw new RoleAlreadyExistsException();
		}
		log.info("Saving new role {} to the database", role.getRoleType());
		return roleRepository.save(role);
	}
	@CachePut(value = "users", key="#p0", unless = "#result == null")
	@Override
	public User addRoleToUser(String username, String roleType) {
		User user = findUserByUsername(username);
		Role role = findRoleByRoleType(roleType);

		if (checkIfUserHasRole(user, role)) {
			throw new UserAlreadyHasThatRole();
		}

		user.getRoles().add(role);
		log.info("adding role {} to user {}", roleType, username);
		return userRepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public Role findRoleByRoleType(String roleType) {
		return roleRepository.findByRoleType(roleType).orElseThrow(RoleNotFoundException::new);
	}

	@Override
	public boolean userExists(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	@Override
	public boolean roleExists(String roleType) {
		return roleRepository.findByRoleType(roleType).isPresent();
	}

	@Override
	public boolean checkIfUserHasRole(User user, Role role) {
		for (int i = 0; i < user.getRoles().size(); i++) {
			if (user.getRoles().get(i).equals(role)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void createRoles() {
		List<Role> roles = roleRepository.findAll();
		if (roles.isEmpty()) {
			saveRole(new Role(null, "OWNER"));
			saveRole(new Role(null, "ADMIN"));
			saveRole(new Role(null, "USER"));
		}
	}


	@Override
	public void createOwner() {
		Optional<User> user = userRepository.findByUsername("owner");
		if (user.isEmpty()) {
			User newUser =
					User.builder()
							.name("owner")
							.username("owner")
							.roles(new ArrayList<>())
							.password(passwordEncoder.encode("owner123"))
							.build();
			userRepository.save(newUser);
			addRoleToUser("owner", "OWNER");
		}
	}
	@CacheEvict(value = "users", allEntries=true)
	@Override
	public void deleteUser(String username) {
		userRepository.delete(findUserByUsername(username));
	}
	@CacheEvict(value = "users", allEntries=true)
	@Override
	public void deleteRole(String roleType) {
		roleRepository.delete(findRoleByRoleType(roleType));
	}

	@Cacheable(value = "users", key="#p0", unless = "#result == null")
	@Override
	public UserDto findUserDtoByUsername(String username) {
		log.info("fetching {}", username);
		return UserConverter.convertEntityToUserDto(findUserByUsername(username));
	}

	@Override
	public List<UserDto> getUsers() {
		log.info("fetching all users");
		return userRepository
				.findAll()
				.stream()
				.map(UserConverter::convertEntityToUserDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<CityDto> getAllCitiesInDb() {
		return cityService.getAllCitiesInDB();
	}
	@Cacheable(value = "cities", key="#p0", unless = "#result == null")	@Override
	public CityDto getCityDtoByName(String cityName) {
		System.out.println("gettingFromDB");
		return cityService.getCityDtoByName(cityName);
	}

	@CacheEvict(value = "users", allEntries=true)
	@Override
	public UserDto addCityToUser(String username, String cityName) {
		User user = findUserByUsername(username);
		City city = cityService.findCityByCityName(cityName);
		if (checkCityInVisitedList(city, user)) {
			throw new CityAlreadyExistsException();
		}
		user.getCitiesVisited().add(city);
		userRepository.save(user);
		return UserConverter.convertEntityToUserDto(user);
	}

	public boolean checkCityInVisitedList(City city, User user) {
		if(user.getCitiesVisited().size() == 0){
			return false;}
		return user.getCitiesVisited().stream().map(city1 -> city1.equals(city)).findFirst().get();
	}


	@CacheEvict(value = "users", allEntries=true)
	@Override
	public UserDto addLivingCityToUser(String username, String cityName) {
		User user = findUserByUsername(username);
		City city = cityService.findCityByCityName(cityName);
		user.setLivingCity(city);
		city.getUsers().add(user);
		userRepository.save(user);
		cityService.saveCityOnRepository(city);
		return UserConverter.convertEntityToUserDto(user);

	}

	@Override
	public List<CityDto> getAllCitiesVisitedByUser(String username) {

		return findUserByUsername(username)
				.getCitiesVisited()
				.parallelStream()
				.map(CityConverter::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public CityDto getUserLivingCity(String username) {
		return CityConverter
				.convertToDto(findUserByUsername(username).getLivingCity());
	}

	@Override
	public List<CityDto> getCitiesInDBOrdered() {
		return cityService.getCitiesInDBOrdered();
	}

	@Override
	public List<CityDtoWithCategory> getCitiesWithCategoryBiggerThan(String category, Integer score) {
		return cityService.getCitiesWithCategoryBiggerThan(category, score);
	}


	@CacheEvict(value = "users", allEntries=true)
	@Override
	public ReviewDto addReviewToCityVisited(CreateReviewDto review, String username, String cityName) {

		User user = findUserByUsername(username);
		City city = cityService.findCityByCityName(cityName);
		if(!checkIfUserAlreadyVisitedCity(user, city)){
			throw new CityNotVisitedException();
		}
		if (checkReviewAlreadyExists(user, cityName)) {
			throw new ReviewAlreadyExistsException();
		}


		Review review1 = ReviewConverter.convertCreateReviewDtoToEntity(review);

		review1.setUser(user);
		review1.setCity(city);
		review1.setCityName(cityName);
		review1.setLocalDate(LocalDate.now());
		reviewService.saveReview(review1);

		return ReviewConverter.convertEntityToReviewDto(review1);
	}
	@CacheEvict(value = "users", allEntries=true)
	@Override
	public ReviewDto updateReview(CreateReviewDto createReviewDto, String cityName, String user) {
		User user1 = findUserByUsername(user);
		if (!checkReviewAlreadyExists(user1, cityName)){
			throw new ReviewNotFoundException();
		}
		Review review = user1.getCityReview().stream().filter(review1 -> cityName.equalsIgnoreCase(review1.getCityName())).findFirst().get();
		review.setScoreAverage(createReviewDto.getScoreAverage());
		review.setLocalDate(LocalDate.now());
		reviewService.saveReview(review);
		userRepository.save(user1);
		return ReviewConverter.convertEntityToReviewDto(review);
	}

	public boolean checkReviewAlreadyExists(User user, String cityName) {
		if(user.getCityReview().size() == 0) {
			return false;
		}
		return user.getCityReview().stream().map(review1 -> review1.getCityName().equals(cityName)).findFirst().get();
	}

	public boolean checkIfUserAlreadyVisitedCity(User user, City city){
		if(user.getCitiesVisited().size() == 0){
			return false;
		}
		return user.getCitiesVisited().stream().map(city1 -> city1.equals(city)).findFirst().get();
	}
}
