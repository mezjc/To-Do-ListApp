package org.jhon.app.todolistapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SaveUser(
        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}", message = "{saveUser.username.pattern}") String firstname,
        @Size(min = 4, max = 255, message = "generic.size") @NotBlank(message = "generic.notblack") String lastname,
        @Pattern(regexp = "[a-zA-Z0-9-_]{8,255}" , message = "{saveUser.username.pattern}") String username,
        @Size(min = 6, max = 255, message = "generic.size") @NotBlank(message = "generic.notblack")  String password,
        @Size(min = 6, max = 255, message = "generic.size") @NotBlank(message = "generic.notblack")  String passwordRepeated
) implements Serializable {
}
