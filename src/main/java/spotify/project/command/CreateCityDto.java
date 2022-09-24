package spotify.project.command;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class CreateCityDto {

	private String name;
	private List<CategoryDto> categories;
	private String summary;
}
