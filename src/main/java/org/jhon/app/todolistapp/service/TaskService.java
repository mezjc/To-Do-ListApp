package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.persistence.entity.Task;

import java.util.List;

public interface TaskService {

    List<GetTask> findAll();

    List<GetTask> findByTitle(String title);

    List<GetTask> findByCategory(String category);

    List<GetTask> findByCategoryAndTile(String category, String title);

    List<GetTask> findByUserId(Long userId);

    List<GetTask> findByUsername(String username);


    GetTask findOneById(Long id);

    GetTask createOne(SaveTask saveDto);

    GetTask updateOneById(Long id, SaveTask saveDto);

    void deleteOneById(Long id);


}
