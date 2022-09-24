package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import spotify.project.models.Category;
import spotify.project.models.City;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link City} entity
 */
@Data
@Builder
public class CityDto{

	private Integer id;
	private String name;
	private Integer avg_score_0_to_10;
}
