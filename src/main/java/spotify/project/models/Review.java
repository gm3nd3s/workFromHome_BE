package spotify.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cityName;
	private Integer scoreAverage;
	private LocalDate localDate;

	@ManyToOne
	@JsonIgnore
	private User user;
	//private String review;

	@ManyToOne
	@JsonIgnore
	private City city;
}
