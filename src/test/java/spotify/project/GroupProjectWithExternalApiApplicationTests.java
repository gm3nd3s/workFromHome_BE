package spotify.project;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import spotify.project.command.CreateUserDto;
import spotify.project.command.UserDto;
import spotify.project.models.Role;
import spotify.project.models.User;
import spotify.project.services.UserServiceImpl;

import java.lang.reflect.Executable;
import java.util.List;

@SpringBootTest
class GroupProjectWithExternalApiApplicationTests {

	@Test
	void contextLoads() {
	}

	class UserServiceTest {
		UserServiceImpl userService;
		@Test
		void registerUser() {
			//Arrange
			CreateUserDto createUserDto = CreateUserDto.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();

			UserDto userDtoExpextedResult = UserDto.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			//Act
			UserDto userDtoResult = userService.registerUser(createUserDto);
			//Assert
			Assertions.assertEquals(userDtoExpextedResult, userDtoResult);
		}

		@Test
		void saveRole(){
			//Arrange
			Role role = Role.builder()
					.roleType("roleType")
					.build();
			//Act
			Role roleResult = userService.saveRole(role);
			//Assert
			Assertions.assertEquals(role, roleResult);
		}

		@Test
		void saveRoleToUser() {
			//Arrange
			Role role = Role.builder()
					.roleType("roleType")
					.build();
			User user = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			User expectedUserResult = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.roles(List.of(role))
					.build();
			//Act
			User result = userService.addRoleToUser(user.getUsername(), role.getRoleType());
			//Assert
			Assertions.assertEquals(expectedUserResult, result);
		}

		@Test
		void findUserByUsername() {
			//Arrange
			User user = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			User expectedUserResult = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			//Act
			User result = userService.findUserByUsername(user.getUsername());
			//Assert
			Assertions.assertEquals(expectedUserResult, result);
		}

		@Test
		void findRoleByRoleType() {
			//Arrange
			Role role = Role.builder()
					.roleType("roleType")
					.build();
			Role expectedRoleResult = Role.builder()
					.roleType("roleType")
					.build();
			//Act
			Role result = userService.findRoleByRoleType(role.getRoleType());
			//Assert
			Assertions.assertEquals(expectedRoleResult, result);
		}

		@Test
		void getAllUsers() {
			//Arrange
			UserDto user = UserDto.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			UserDto user2 = UserDto.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			List<UserDto> expectedUserResult = List.of(user, user2);
			//Act
			List<UserDto> result = userService.getUsers();
			//Assert
			Assertions.assertEquals(expectedUserResult, result);
		}

		@Test
		void checkIfUserExistsWithoutAddingUserMustBeFalse() {
			//Arrange
			User user = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();
			boolean expectedUserResult = false;
			//Act
			boolean result = userService.userExists(user.getUsername());
			//Assert
			Assertions.assertTrue(expectedUserResult == result);
		}
		@Test
		void checkIfUserExistsMustBeTrue() {
			//Arrange
			User user = User.builder()
					.name("name")
					.password("password")
					.username("username")
					.build();

			boolean expectedUserResult = true;
			userService.registerUser(CreateUserDto.builder()
					.name("name")
					.password("password")
					.username("username")
					.build());
			//Act
			boolean result = userService.userExists(user.getUsername());
			//Assert
			Assertions.assertTrue(expectedUserResult == result);
		}
	}



}
