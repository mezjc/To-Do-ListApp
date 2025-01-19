package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.persistence.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    List<Task> findByTitle(String title);

    List<Task> findByCategory(String category);

    List<Task> findByCategoryAndTile(String category, String title);

    List<Task> findByUserId(Long userId);

    List<Task> findByUsername(String username);


    Task findOneById(Long id);

    Task createOne(Task task);

    Task updateOneById(Long id, Task task);

    void deleteOneById(Long id);


}
