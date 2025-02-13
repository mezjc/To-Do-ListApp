package org.jhon.app.todolistapp.dto.request;

import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;

public record SaveTask(
        long userId,
        String title,
        String description,
        State state,
        SaveCategory category

) implements Serializable {

    public static record SaveCategory(
            long genre
    ) implements Serializable{

    }
}
