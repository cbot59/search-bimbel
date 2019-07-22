package it.aldi.app.controller.cmd;

import it.aldi.app.util.RegexConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditStudentProfileCmd {

    @NotNull
    @Pattern(regexp = RegexConstant.NAME_REGEX, message = "Name should only contain alphabet")
    private String name;

    @NotNull
    @Size(min = 3, message = "Phone number should be at least three digits")
    private String phone;

    @NotNull(message = "Address should not be empty")
    private String address;
}
