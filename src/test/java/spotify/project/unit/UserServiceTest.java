package spotify.project.unit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spotify.project.command.CreateUserDto;
import spotify.project.command.ReviewConverter;
import spotify.project.command.UserConverter;
import spotify.project.command.UserDto;
import spotify.project.exception.UserAlreadyExistsException;
import spotify.project.exception.UserAlreadyHasThatRole;
import spotify.project.exception.UserNotFoundException;
import spotify.project.mocks.MockedUsers;
import spotify.project.models.City;
import spotify.project.models.Review;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;
import spotify.project.services.CityService;
import spotify.project.services.ReviewService;
import spotify.project.services.UserService;
import spotify.project.services.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static spotify.project.mocks.MockedCitys.getMockedCity1;
import static spotify.project.mocks.MockedCitys.getMockedCity2;
import static spotify.project.mocks.MockedReviews.getMockedReview1;
import static spotify.project.mocks.MockedReviews.getMockedReview2;
import static spotify.project.mocks.MockedRoles.getMockedRole1;
import static spotify.project.mocks.MockedRoles.getMockedRole2;
import static spotify.project.mocks.MockedUsers.*;

@ExtendWith(SpringExtension.class)

public class UserServiceTest {
    private UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ReviewConverter reviewConverter;

    @Mock
    private CityService cityService;

    @Mock
    private ReviewService reviewService;



    @BeforeEach
    public void setUp() {
        userConverter = new UserConverter();
        userService = new UserServiceImpl(cityService, userRepository, roleRepository, passwordEncoder, reviewService);

    }


    @Test
    public void registerUser_should_return_success() {

        // Given

        User expected = getMockedUser0();

        // When

        userRepository.save(expected);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User response = userArgumentCaptor.getValue();

        // Then

        assertNotNull(response);
        assertEquals(expected, response);

    }
    @Test
    public void saveRole_should_return_success() {

        // Given

        Role expected = getMockedRole1();

        // When

        roleRepository.save(expected);
        ArgumentCaptor<Role> roleArgumentCaptor = ArgumentCaptor.forClass(Role.class);
        verify(roleRepository).save(roleArgumentCaptor.capture());
        Role response = roleArgumentCaptor.getValue();

        // Then
        assertNotNull(response);
        assertEquals(expected, response);

    }
    @Test
    public void getUserByUsername_should_throw_userNotFoundException() {

        // Arrange

        String username = "IdoNotExist";

        // Act

        final Executable action = () -> userService.findUserByUsername(username);

        // Asserts

        assertThrows(UserNotFoundException.class, action);

    }

    @Test
    public void check_userExists_should_return_success() {

        // Given

        User user = getMockedUser1();
        userService.saveUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User response = userArgumentCaptor.getValue();

        // When

        userService.userExists(user.getUsername());

        // Then

        verify(userRepository).findByUsername(user.getUsername());

    }



    @Test
    public void test_addRoleToUser_should_succeed(){

        // Given

        Role role = getMockedRole1();

        // When

        userService.saveRole(role);

        // Then

        verify(roleRepository).save(role);

    }

    @Test
    public void check_user_has_role(){

        // Given

        Role role = getMockedRole1();
        Role role1 = getMockedRole2();

        User user = User.builder()
                .name("gonçalo")
                .username("gonçalo")
                .roles(List.of(role))
                .build();

        // When

        boolean response = userService.checkIfUserHasRole(user, role);
        boolean response2 = userService.checkIfUserHasRole(user, role1);

        // Then

        assertTrue(response);
        assertFalse(response2);
    }

    @Test
    public void check_review_already_exists() {

        // Given

        Review review = getMockedReview1();
        Review review2 = getMockedReview2();
        User user = User.builder()
                .name("ana")
                .username("ana")
                .cityReview(List.of(review))
                .build();
        // When

        boolean response = userService.checkReviewAlreadyExists(user, review.getCityName());
        boolean response2 = userService.checkReviewAlreadyExists(user, review2.getCityName());

        // Then

        assertTrue(response);
        assertFalse(response2);
    }

    @Test
    public void test_check_if_has_visited_city(){

        // Given

        City city = getMockedCity1();
        City city2 = getMockedCity2();

        User user = User.builder()
                .name("tiago")
                .username("tiago")
                .citiesVisited(List.of(city))
                        .build();

        // When

        boolean response = userService.checkCityInVisitedList(city, user);
        boolean response2 = userService.checkCityInVisitedList(city2, user);

        // Then

        assertTrue(response);
        assertFalse(response2);
    }
}
