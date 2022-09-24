package spotify.project.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCountryDto {
    private String href;
    private String name;
}
