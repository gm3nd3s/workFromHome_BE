package spotify.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import spotify.project.models.User;
import spotify.project.repositories.UserRepository;

import javax.transaction.Transactional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class RepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void testUserRepository() {
        //Arrange
        User user = User.builder()
                .name("name")
                .username("username")
                .password("password")
                .build();

        String expectedUsername = user.getUsername();
        //Act
        String result = userRepository.save(user).getUsername();
        //Assert

        Assertions.assertEquals(expectedUsername, result);
    }
}
