package spotify.project.command;

import lombok.Builder;
import lombok.Data;


@Data
public class CreateCategoryDto {

	private String name;
	private Integer score_out_of_10;
}
