package spotify.project.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateCategoryDto {

	private String name;
	private Integer scoreOutOfTen;
}
