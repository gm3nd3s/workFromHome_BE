package spotify.project.command;

import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;
import spotify.project.models.Role;

import java.util.List;

@Data
@Builder
public class UserDto {
	private Long id;
	private String name;
	private String username;
	private String password;
	private List<Role> roles;
	private List<City> citiesVisited;
	private City livingCity;
}
