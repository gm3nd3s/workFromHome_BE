package spotify.project.command;

import spotify.project.models.User;

import java.util.ArrayList;
import java.util.Optional;


public class UserConverter {

	public static UserDto convertEntityToUserDto(User user) {
		if(user.getCitiesVisited() == null) {
			return UserDto.builder()
					.id(user.getId())
					.name(user.getName())
					.username(user.getUsername())
					.password(user.getPassword())
					.roles(user.getRoles())
					.citiesVisited(new ArrayList<>())
					.livingCity(user.getLivingCity())
					.cityReview(new ArrayList<>())
					.build();
		}
		return UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles())
				.citiesVisited(user.getCitiesVisited().stream().map(CityConverter::convertToDto).toList())
				.livingCity(user.getLivingCity())
				.cityReview(user.getCityReview().stream().map(ReviewConverter::convertEntityToReviewDto).toList())
				.build();
	}

	public static User convertUserDtoToEntity(UserDto userDto) {
		return User.builder()
				.id(userDto.getId())
				.name(userDto.getName())
				.password(userDto.getPassword())
				.roles(userDto.getRoles())
				.citiesVisited(userDto.getCitiesVisited().stream().map(CityConverter::convertCityDtoToCity).toList())
				.livingCity(userDto.getLivingCity())
				.cityReview(userDto.getCityReview().stream().map(ReviewConverter::convertReviewDtoToEntity).toList())
				.build();
	}

	public static User convertCreateUserDtoToEntity(CreateUserDto createUserDto) {
		return User.builder()
				.name(createUserDto.getName())
				.username(createUserDto.getUsername())
				.password(createUserDto.getPassword())
				.roles(new ArrayList<>())
				.build();
	}

}
