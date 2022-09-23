package spotify.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {
    @NotBlank(message = "name can not be blank")
    public String name;
    @NotBlank(message = "username can not be blank")
    public String username;
    @Size(min = 6, max = 16, message = "password must be between 6 to 16 characters")
    public String password;

}
