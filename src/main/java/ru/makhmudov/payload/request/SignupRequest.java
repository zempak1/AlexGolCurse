package ru.makhmudov.payload.request;

import lombok.Data;
import ru.makhmudov.annotations.PasswordMatches;
import ru.makhmudov.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*Этот объект будем использовать, когда будем пытаться зарегистрировать нового пользователя*/

@Data

/*Кастомная аннотация, проверяет введенные пароли в форме на совпадение*/
@PasswordMatches
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;


}
