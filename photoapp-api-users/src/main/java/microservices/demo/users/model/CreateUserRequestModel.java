package microservices.demo.users.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUserRequestModel {

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must be at least 2 characaters")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must be at least 2 characaters")
    private String lastName;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must be >= 8 and <= 16 characaters")
    private String password;
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

}
