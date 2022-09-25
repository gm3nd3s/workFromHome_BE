package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;

@Data
public class UrbanAreaDto {

	private LinksDto links;

	@JsonGetter("_links")
	public LinksDto getLinks() {
		return links;
	}
}
