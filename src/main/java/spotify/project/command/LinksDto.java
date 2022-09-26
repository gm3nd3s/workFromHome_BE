package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

import java.util.List;

@Data
public class LinksDto {

	private List<CityUrbanAreaDto> cities;

	@JsonGetter("ua:item")
	public List<CityUrbanAreaDto> getCities() {
		return cities;
	}
}
