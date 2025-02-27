package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.persistence.repository.TaskCrudRepository;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskCrudRepository taskCrudRepository;

    public TaskServiceImpl(TaskCrudRepository taskCrudRepository) {
        this.taskCrudRepository = taskCrudRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findAll() {
        return taskCrudRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findByTitle(String title) {
        return taskCrudRepository.findByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findByCategory(String category) {
        return taskCrudRepository.findByCategoryGenreContaining(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findByCategoryAndTile(String category, String title) {
        return taskCrudRepository.findByCategoryGenreAndTitleContaining(category, title);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findByUserId(Long userId) {
        return taskCrudRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Task> findByUsername(String username) {
        return taskCrudRepository.findByUserUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public Task findOneById(Long id) {
        return taskCrudRepository
                .findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("[task:"+ Long.toString(id) +"]"));

    }

    @Override
    public Task createOne(Task task) {
        return taskCrudRepository.save(task);
    }

    @Override
    public Task updateOneById(Long id, Task newTask) {
        Task oldTask = this.findOneById(id);
        oldTask.setDescription(newTask.getDescription());
        oldTask.setState(newTask.getState());
        oldTask.setTitle(newTask.getTitle());
        oldTask.setCategory(newTask.getCategory());
        return taskCrudRepository.save(oldTask);
    }

    @Override
    public void deleteOneById(Long id) {
        Task task = this.findOneById(id);
        taskCrudRepository.delete(task);
    }
}
