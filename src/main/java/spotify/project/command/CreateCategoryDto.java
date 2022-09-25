package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;


@Data
public class CreateCategoryDto {

	private String name;
	private Integer score;

	@JsonGetter("score_out_of_10")
	public Integer getScore() {
		return score;
	}
}
