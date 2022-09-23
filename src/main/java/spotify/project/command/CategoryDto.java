package spotify.project.command;

import lombok.Builder;
import lombok.Data;
import spotify.project.models.Category;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@Builder
public class CategoryDto implements Serializable {

	@Id
	private Integer id;
	private String name;
	private Integer score_out_of_10;
}
