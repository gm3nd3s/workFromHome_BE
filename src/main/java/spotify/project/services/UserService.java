package spotify.project.services;


import spotify.project.command.CityDto;
import spotify.project.command.CreateUserDto;
import spotify.project.command.UserDto;
import spotify.project.models.City;
import spotify.project.models.Role;
import spotify.project.models.User;

import java.util.List;

public interface UserService {
    UserDto registerUser(CreateUserDto user);
    Role saveRole(Role role);
    User addRoleToUser(String username, String roleType);
    UserDto findByUserName(String username); //assuming every username must be different
    List<UserDto> getUsers();

    boolean checkIfUserHasRole(User user, Role role);

    void createRoles();

    void createOwner();

    User findUserByUsername(String username);
    Role findRoleByRoleType(String roleType);

    boolean userExists(String username);
    boolean roleExists(String roleType);

    void deleteUser(String username);

    void deleteRole(String roleType);

    List<CityDto> getAllCitiesInDb();

    CityDto getCityDtoByName(String cityName);

    UserDto addCityToUser(String username, String cityName);

    UserDto addLivingCityToUser(String username, String cityName);

   /* void addCityToUser(String username, String cityName);

    void addCityToUserListOfCities(String username, String cityName);*/
}
