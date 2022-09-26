package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;

/**
 * A DTO for the {@link City} entity
 */
@Data
@Builder
public class CityDto {
	@JsonIgnore
	private Integer id;
	private String name;
	private Integer averageScore;
}
