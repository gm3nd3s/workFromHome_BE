package spotify.project.command;

import lombok.Builder;
import lombok.Data;
import spotify.project.models.City;
import spotify.project.models.User;

import java.time.LocalDate;

@Data
@Builder
public class CreateReviewDto {

	private Integer scoreAverage;
	private LocalDate localDate;

	private User user;
	//private String review;

	private City city;
}
