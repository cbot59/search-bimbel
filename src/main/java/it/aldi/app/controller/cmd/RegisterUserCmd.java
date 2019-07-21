package it.aldi.app.controller.cmd;

import it.aldi.app.util.RegexConstant;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserCmd {

    @NotNull
    @Size(min = 6)
    @Pattern(regexp = RegexConstant.USERNAME_REGEX,
        message = "Username should only contain alphabet (may separated by dot(.)")
    private String username;

    @NotNull
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Name should only contain alphabet")
    private String name;

    @NotNull
    @Size(min = 6, message = "Password length should be at least six characters")
    private String password;

    @NotNull
    @Pattern(regexp = RegexConstant.EMAIL_REGEX)
    private String email;

    @NotNull
    private String roles;

    @NotNull
    @Size(min = 3, message = "Phone number should be at least three digits")
    private String phone;

    @NotNull(message = "Address should not be empty")
    private String address;
}
