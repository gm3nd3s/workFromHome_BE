package spotify.project.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CreateCityDto {

	private String name;
	private Integer avg_score_0_to_10;
	private List<CreateCategoryDto> categories;
	private String summary;
}
