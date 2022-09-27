package spotify.project.command;

import lombok.Data;
import spotify.project.exception.Messages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PasswordDto {

	@NotBlank
	private String oldPassword;
	@Size(min = 6, max = 16, message = Messages.PASSWORD_SIZE)
	private String newPassword;
	@Size(min = 6, max = 16, message =  Messages.PASSWORD_SIZE)
	private String newPasswordConfirm;
}

