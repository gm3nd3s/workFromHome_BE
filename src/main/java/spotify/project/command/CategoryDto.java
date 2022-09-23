package spotify.project.command;

import lombok.Data;
import spotify.project.models.Category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
public class CategoryDto implements Serializable {

	private String name;
	private Integer score_out_of_10;
}
