package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;
import spotify.project.models.Review;
import spotify.project.models.Role;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class UserDto implements Serializable {
	private static final long serialVersionUID = 7156526077883281623L;

	@JsonIgnore
	private Long id;
	private String name;
	private String username;
	@JsonIgnore
	private String password;
	private List<Role> roles;
	private List<CityDto> citiesVisited;
	private City livingCity;
	private List<ReviewDto> cityReview;
}
