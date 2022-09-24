package spotify.project.command;

import lombok.Builder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Data

public class CountryDto {
    @Id
    private Integer id;
    private String href;
    private String name;
}
