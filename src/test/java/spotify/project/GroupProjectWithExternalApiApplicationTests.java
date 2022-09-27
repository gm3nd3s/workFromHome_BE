package spotify.project;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import spotify.project.command.CreateUserDto;
import spotify.project.command.UserConverter;
import spotify.project.command.UserDto;
import spotify.project.models.City;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.repositories.RoleRepository;
import spotify.project.repositories.UserRepository;
import spotify.project.services.*;

import java.lang.reflect.Executable;
import java.util.List;

@SpringBootTest
class GroupProjectWithExternalApiApplicationTests {

}
