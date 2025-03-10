package org.jhon.app.todolistapp.dto.request;

import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;

public record SaveTask(
        long userId,
        String title,
        String description,
        State state,
        String category
) implements Serializable {

    public static record  SaveCategory(
            String genre
    ) implements Serializable{}
}
