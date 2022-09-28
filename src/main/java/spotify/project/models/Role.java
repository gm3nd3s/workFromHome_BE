package spotify.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = 7156526077883281623L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true, updatable = false)
	@JsonIgnore
	private Long id;
	@Column
	private String roleType;

}
