package spotify.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer averageScore;
	private Integer userScore;

	@OneToMany(cascade = {CascadeType.ALL},
			mappedBy = "city")
	@JsonIgnore
	private List<Category> categoriesList;

	@OneToMany(cascade = {CascadeType.ALL},
			mappedBy = "livingCity")
	@JsonIgnore
	private List<User> users;
}
