package spotify.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import spotify.project.command.CreateCityDto;
import spotify.project.command.CreateUserDto;
import spotify.project.command.UserConverter;
import spotify.project.command.UserDto;
import spotify.project.models.City;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;
import spotify.project.services.*;
import org.hamcrest.Matchers.*;

import javax.transaction.Transactional;
import java.lang.reflect.Executable;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes=GroupProjectWithExternalApiApplication.class)
class GroupProjectWithExternalApiApplicationTests {

      CityServiceImpl cityService;

      @Test
      void testGetCityByNameShouldReturnCreateCityDto(){
            //Arrange
            String cityName = "Sofia";
            String expectedResltCityName = "Sofia";
            //Act
            CreateCityDto cityResult = cityService.getCityDto(cityName);
            //Assert
            Assertions.assertEquals(cityName, cityResult.getName());
      }






}
