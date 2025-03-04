package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.persistence.repository.CategoryCrudRepository;
import org.jhon.app.todolistapp.persistence.repository.TaskCrudRepository;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskCrudRepository taskCrudRepository;

    private final CategoryCrudRepository categoryCrudRepository;

    public TaskServiceImpl(TaskCrudRepository taskCrudRepository, CategoryCrudRepository categoryCrudRepository) {
        this.taskCrudRepository = taskCrudRepository;
        this.categoryCrudRepository = categoryCrudRepository;
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

    private Category findOneByGenre(Category category){
        Optional<Category> newCategory = categoryCrudRepository.findByGenre(category.getGenre());
//        if (newCategory.isPresent()){
//            return newCategory.get();
//        }
//        return categoryCrudRepository.save(category);
        return newCategory.orElseGet(() -> categoryCrudRepository.save(category));
    }

    @Override
    public Task createOne(Task task) {
        Category newCategory = this.findOneByGenre(task.getCategory());
        task.setCategory(newCategory);
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
