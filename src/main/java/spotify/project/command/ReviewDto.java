package spotify.project.command;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class ReviewDto implements Serializable {
	private static final long serialVersionUID = 7156526077883281623L;

	@JsonIgnore
	private Integer id;
	private String cityName;
	private Integer scoreAverage;
	private LocalDate localDate;
}
