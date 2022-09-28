package spotify.project.mocks;

import spotify.project.command.CityConverter;
import spotify.project.command.CreateUserDto;
import spotify.project.command.ReviewConverter;
import spotify.project.command.UserDto;
import spotify.project.models.User;

import java.util.ArrayList;
import java.util.List;

import static spotify.project.mocks.MockedCitys.getMockedCity1;
import static spotify.project.mocks.MockedCitys.getMockedCities;
import static spotify.project.mocks.MockedReviews.getMockedReviews;
import static spotify.project.mocks.MockedRoles.getMockedRoles;

public class MockedUsers {

    public static User getMockedUser0(){
        return User.builder()
                .name("diogo")
                .username("diogo")
                .password("1234567")
                .roles(new ArrayList<>())
                .build();
    }
    public static User getMockedUser1() {
        return User.builder()
                .id(1L)
                .name("Tiago")
                .username("tiago")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build();
    }
    public static User getMockedUser2() {


        return User.builder()
                .id(2L)
                .name("Ana")
                .username("ana")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build();


    }

    public static User getMockedUser3(){
        return User.builder()
                .id(3L)
                .name("gonçalo")
                .username("gonçalo")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build();
    }

    public static List<User> getMockedUsers(){
        return List.of(User.builder()
                .id(1L)
                .name("Tiago")
                .username("tiago")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build(),
                User.builder()
                .id(2L)
                .name("Ana")
                .username("ana")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build(),
                User.builder()
                .id(3L)
                .name("gonçalo")
                .username("gonçalo")
                .password("1234567")
                .roles(getMockedRoles())
                .livingCity(getMockedCity1())
                .citiesVisited(getMockedCities())
                .cityReview(getMockedReviews())
                .build());
    }

    public static List<UserDto> getMockedUsersDto(List<User> userList) {

        return List.of(
                UserDto.builder()
                        .id(userList.get(0).getId())
                        .name(userList.get(0).getName())
                        .username(userList.get(0).getUsername())
                        .password(userList.get(0).getPassword())
                        .roles(userList.get(0).getRoles())
                        .citiesVisited(userList.get(0).getCitiesVisited().stream().map(CityConverter::convertToDto).toList())
                        .livingCity(userList.get(0).getLivingCity())
                        .cityReview(userList.get(0).getCityReview().stream().map(ReviewConverter::convertEntityToReviewDto).toList())
                        .build(),
                UserDto.builder()
                        .id(userList.get(1).getId())
                        .name(userList.get(1).getName())
                        .username(userList.get(1).getUsername())
                        .password(userList.get(1).getPassword())
                        .roles(userList.get(1).getRoles())
                        .citiesVisited(userList.get(1).getCitiesVisited().stream().map(CityConverter::convertToDto).toList())
                        .livingCity(userList.get(1).getLivingCity())
                        .cityReview(userList.get(1).getCityReview().stream().map(ReviewConverter::convertEntityToReviewDto).toList())
                        .build(),
                UserDto.builder()
                        .id(userList.get(2).getId())
                        .name(userList.get(2).getName())
                        .username(userList.get(2).getUsername())
                        .password(userList.get(2).getPassword())
                        .roles(userList.get(2).getRoles())
                        .citiesVisited(userList.get(2).getCitiesVisited().stream().map(CityConverter::convertToDto).toList())
                        .livingCity(userList.get(2).getLivingCity())
                        .cityReview(userList.get(2).getCityReview().stream().map(ReviewConverter::convertEntityToReviewDto).toList())
                        .build());
    }

  public static CreateUserDto getCreateUserDtoMock(){
        return CreateUserDto.builder()
                .name("diogo")
                .username("diogo")
                .password("1234567")
                .build();
    }
/*
    public static UserDto userDto(){
        return UserDto.builder()
                .id(1L)
                .name(createUserDto().getName())
                .username(createUserDto().username)
                .password("1234567")
                .roles(List.of(getMockedRole3()))
                .build();
    }*/

    public static UserDto convertEntityToUserDto(User user) {
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
