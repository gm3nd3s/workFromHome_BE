package spotify.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spotify.project.exception.Messages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
	@NotBlank(message = "name" + Messages.NOT_BLANK)
	public String name;
	@NotBlank(message = "username" + Messages.NOT_BLANK)
	public String username;
	@Size(min = 6, max = 16, message = Messages.PASSWORD_SIZE)
	public String password;

}
