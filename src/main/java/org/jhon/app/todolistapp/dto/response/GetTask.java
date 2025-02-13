package org.jhon.app.todolistapp.dto.response;

import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record GetTask(
        Long id,
        String username,
        String title,
        String desciption,
        State state,
        LocalDateTime createdAt,
        GetCategory category
)implements Serializable {

    public static record GetCategory(
            long id,
            String genre


    ) implements Serializable{}
}
