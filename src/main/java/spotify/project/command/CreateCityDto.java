package spotify.project.command;

import lombok.Data;

import java.util.List;

@Data
public class CreateCityDto {

	private String name;
	private Integer average_score;
	private List<CreateCategoryDto> categories;
	private String summary;
}
