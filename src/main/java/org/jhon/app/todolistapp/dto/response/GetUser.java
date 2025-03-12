package org.jhon.app.todolistapp.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record GetUser(
        String firstname,
        String lastname,
        String username,
        List<GetUserTask> tasks
) implements Serializable {

    public static record GetUserTask(
            long id,
            String title,
            String description,
            State state,
            @JsonProperty(value = "releaseYear")
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime createdAt,
            String category
    ) implements Serializable{
    }
}
