package spotify.project.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordDto {

	@NotBlank
	private String oldPassword;
	@Size(min = 6, max = 16, message = "password must be between 6 to 16 characters")
	private String newPassword;
	@Size(min = 6, max = 16, message = "password must be between 6 to 16 characters")
	private String newPasswordConfirm;
}

