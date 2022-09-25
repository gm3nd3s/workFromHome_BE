package spotify.project.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDtoWithCategory {
    private String name;
    private Integer avg_score_0_to_10;
    private CategoryDto categoryDto;
}
