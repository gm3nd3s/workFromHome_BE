package spotify.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	private Integer id;
	private String name;
	private Integer scoreOutOfTen;
}
