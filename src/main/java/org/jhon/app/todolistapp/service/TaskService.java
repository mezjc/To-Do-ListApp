package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.request.TaskSearchCriteria;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<GetTask> findAll(TaskSearchCriteria searchCriteria, Pageable pageable);


    List<GetTask> findByUsername(String username);


    GetTask findOneById(Long id);

    GetTask createOne(SaveTask saveDto);

    GetTask updateOneById(Long id, SaveTask saveDto);

    void deleteOneById(Long id);


}
