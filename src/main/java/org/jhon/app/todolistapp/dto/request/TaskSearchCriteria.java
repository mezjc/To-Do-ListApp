package org.jhon.app.todolistapp.dto.request;

import java.time.LocalDate;

public record TaskSearchCriteria(
        String category,
        LocalDate releaseYear,
        String state,
        String title

) {
}
