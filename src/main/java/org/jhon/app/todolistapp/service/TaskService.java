package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.request.TaskSearchCriteria;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.util.State;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    List<GetTask> findAll(TaskSearchCriteria searchCriteria);


    List<GetTask> findByUsername(String username);


    GetTask findOneById(Long id);

    GetTask createOne(SaveTask saveDto);

    GetTask updateOneById(Long id, SaveTask saveDto);

    void deleteOneById(Long id);


}
