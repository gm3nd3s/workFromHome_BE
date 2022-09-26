package spotify.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spotify.project.models.Category;

import java.io.Serializable;

/**
 * A DTO for the {@link Category} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {

	private String name;
	private Integer score_out_of_10;
}
