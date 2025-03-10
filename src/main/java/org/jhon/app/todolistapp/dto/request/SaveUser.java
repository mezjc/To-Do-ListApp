package org.jhon.app.todolistapp.dto.request;

import java.io.Serializable;

public record SaveUser(
        String firstname,
        String lastname,
        String username,
        String password,
        String passwordRepeated
) implements Serializable {
}
