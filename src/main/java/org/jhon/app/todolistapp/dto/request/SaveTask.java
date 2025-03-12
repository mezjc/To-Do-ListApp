package org.jhon.app.todolistapp.dto.request;

import jakarta.validation.constraints.*;
import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;

public record SaveTask(
        @Min(value = 0, message = "The user ID must be a positive number ")
        @NotNull Long userId,
        @Size(min = 4, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblack}") String title,
        @Size(min = 4, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblack}")
        String description,
        State state,
        @Size(min = 4, max = 255, message = "{generic.size}")
        @NotBlank(message = "{generic.notblack}")
        String category
) implements Serializable {

    public static record  SaveCategory(
            String genre
    ) implements Serializable{}
}
