package spotify.project.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spotify.project.command.*;
import spotify.project.exception.*;
import spotify.project.models.City;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final CityServiceImpl cityService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto registerUser(CreateUserDto user) {
        if(userExists(user.getUsername())){
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
        if(roleExists(role.getRoleType())){
            throw new RoleAlreadyExistsException();
        }
        log.info("Saving new role {} to the database", role.getRoleType());
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String roleType) {

    User user = findUserByUsername(username);
    Role role = findRoleByRoleType(roleType);


    if(checkIfUserHasRole(user, role)){
        throw new UserAlreadyHasThatRole();
    }

    user.getRoles().add(role);
        log.info("adding role {} to user {}", roleType, username);
    return userRepository.save(user);//supostamente como tenho o transactional nao preciso de fazer o save novamente.

    }
    @Override
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }
    @Override
    public Role findRoleByRoleType(String roleType){
        return roleRepository.findByRoleType(roleType).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public boolean userExists(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean roleExists(String roleType){
        return roleRepository.findByRoleType(roleType).isPresent();
    }

    @Override
    public boolean checkIfUserHasRole(User user, Role role) {
        for (int i = 0; i < user.getRoles().size(); i++) {
            if(user.getRoles().get(i).equals(role)){
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

    @Override
    public void deleteUser(String username) {
        userRepository.delete(findUserByUsername(username));
    }

    @Override
    public void deleteRole(String roleType) {
        roleRepository.delete(findRoleByRoleType(roleType));
    }

    @Override
    public UserDto findByUserName(String username) {
        log.info("fetching {}", username );
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

    @Override
    public CityDto getCityDtoByName(String cityName) {
        return cityService.getCityDtoByName(cityName);
    }

    @Override
    public UserDto addCityToUser(String username, String cityName) {
        User user = findUserByUsername(username);
        City city = cityService.findCityByCityName(cityName);
        user.getCitiesVisited().add(city);
        userRepository.save(user);

        return  UserConverter.convertEntityToUserDto(user);
    }

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



  /*  @Override
    public void addCityToUser(String username, String cityName) {
        User user = findUserByUsername(username);
        user.setLivingCity(CityConverter.convertCreateCityDtoToCity(cityService.getCityDto(cityName)));
        userRepository.save(user);
    }
*/
  /*  @Override
    public void addCityToUserListOfCities(String username, String cityName) {
        User user = findUserByUsername(username);
        user.getCities().add(CityConverter.convertCreateCityDtoToCity(cityService.getCityDto(cityName)));
        userRepository.save(user);
    }

   */
}
