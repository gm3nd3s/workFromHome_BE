package spotify.project.command;

import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;

/**
 * A DTO for the {@link City} entity
 */
@Data
@Builder
public class CityDto {

	private Integer id;
	private String name;
	private Integer averageScore;
}
