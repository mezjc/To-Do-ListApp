package org.jhon.app.todolistapp.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.el.util.Validation;
import org.jhon.app.todolistapp.util.State;

import java.io.Serializable;
import java.time.LocalDateTime;

public record GetTask(
        long id,
        long userId,
        String title,
        String description,
        State state,
        @JsonProperty(value = "releaseYear")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        String category

) implements Serializable {

    public static record GetCategory(

        String gene

    ) implements Serializable{

    }
}
