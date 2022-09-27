package spotify.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spotify.project.exception.Messages;
import spotify.project.models.City;
import spotify.project.models.User;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto {

	@NotNull(message = "scoreAverage" + Messages.NOT_BLANK)
	@Max(value = 10, message = Messages.SCORE_SIZE)
	private Integer scoreAverage;
	private LocalDate localDate;

	private User user;
	//private String review;

	private City city;
}
