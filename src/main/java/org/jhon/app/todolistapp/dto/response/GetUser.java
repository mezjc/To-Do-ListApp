package org.jhon.app.todolistapp.dto.response;

import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record GetUser(

        String firstname,
        String lastname,
        String username,
        LocalDateTime createdAt,
        List<GetTaskUser> taskUsers

) implements Serializable {

    public static record GetTaskUser(
            long id,
            String title,
            State state,
            LocalDateTime createdAt,
            String category

    ) implements Serializable {}
}
