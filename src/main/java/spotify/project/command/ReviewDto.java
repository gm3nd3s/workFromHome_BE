package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReviewDto {

	@JsonIgnore
	private Integer id;
	private String cityName;
	private Integer scoreAverage;
	private LocalDate localDate;
}
