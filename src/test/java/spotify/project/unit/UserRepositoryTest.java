package spotify.project.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import spotify.project.models.City;
import spotify.project.models.Review;
import spotify.project.models.User;
import spotify.project.repositories.CityRepository;
import spotify.project.repositories.ReviewRepository;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static spotify.project.mocks.MockedCitys.getMockedCities;
import static spotify.project.mocks.MockedCitys.getMockedCity1;
import static spotify.project.mocks.MockedReviews.getMockedReview1;
import static spotify.project.mocks.MockedRoles.getMockedRole1;
import static spotify.project.mocks.MockedUsers.*;

@DataJpaTest
public class UserRepositoryTest {


    @Autowired
    UserRepository userRepositoryTest;

    @BeforeEach
    public void tearDown(){
        userRepositoryTest.deleteAll();
    }


    @Test
    public void test_getUserByName_shouldSucceed(){

        // Given

        User user = getMockedUser0();
        User user1 = getMockedUser1();

        userRepositoryTest.save(user);

        // When

        User response = userRepositoryTest.findByUsername(user.getName()).get();
        final Executable action = () -> userRepositoryTest.findByUsername(user1.getUsername()).get();

        User expected = user;

        // Then

        assertThrows(NoSuchElementException.class, action);
        assertNotNull(response);
        assertEquals(expected, response);

    }
}
