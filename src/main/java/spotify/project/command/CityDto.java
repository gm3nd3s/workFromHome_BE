package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;

import java.io.Serializable;

/**
 * A DTO for the {@link City} entity
 */
@Data
@Builder
public class CityDto implements Serializable {
	private static final long serialVersionUID = 7156526077883281623L;
	@JsonIgnore
	private Integer id;
	private String name;
	private Integer averageScore;
}
