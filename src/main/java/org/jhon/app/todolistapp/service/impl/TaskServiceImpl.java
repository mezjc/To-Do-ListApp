package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.mapper.CategoryMapper;
import org.jhon.app.todolistapp.mapper.TaskMapper;
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
    public List<GetTask> findAll() {
        List<Task>entities = taskCrudRepository.findAll();

        return TaskMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetTask> findByTitle(String title) {
        List<Task> entities = taskCrudRepository.findByTitleContaining(title);

        return TaskMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetTask> findByCategory(String category) {
        List<Task> entities = taskCrudRepository.findByCategoryGenreContaining(category);
        return TaskMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetTask> findByCategoryAndTile(String category, String title) {
        List<Task> entities = taskCrudRepository.findByCategoryGenreAndTitleContaining(category, title);
        return TaskMapper.toGetDtoList(entities);

    }

    @Transactional(readOnly = true)
    @Override
    public List<GetTask> findByUserId(Long userId) {
        List<Task> entities = taskCrudRepository.findByUserId(userId);
        return TaskMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetTask> findByUsername(String username) {
        List<Task> entities = taskCrudRepository.findByUserUsername(username);
        return TaskMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public GetTask findOneById(Long id) {
        return TaskMapper.toGetDto(this.findOneEntityById(id));

    }


    @Transactional(readOnly = true)
    public Task findOneEntityById(Long id) {
        System.out.println(taskCrudRepository.findById(id));

        return taskCrudRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("[task:"+ Long.toString(id) +"]"));


    }


    public Category findOneByGenre(String category){

        Optional<Category> newCategory = categoryCrudRepository.findByGenre(category);
        if (newCategory.isPresent()){
            return newCategory.get();
        }
        Category newCategory2  = new Category();
        newCategory2.setGenre(category);
        return categoryCrudRepository.save(newCategory2);

//        return categoryCrudRepository.findByGenre(category)
//                .orElseGet(() -> categoryCrudRepository.save(new Category(category)));
    }

    @Override
    public GetTask createOne(SaveTask saveDto) {

        Category newCategory = this.findOneByGenre(saveDto.category());

        Task newTask = TaskMapper.toEntity(saveDto);

        newTask.setCategory(newCategory);

        newTask =  taskCrudRepository.save(newTask);

        return TaskMapper.toGetDto(newTask);
    }

    @Override
    public GetTask updateOneById(Long id, SaveTask saveDto) {
        Task oldTask = this.findOneEntityById(id);
        TaskMapper.updateEntity(oldTask, saveDto);

        return TaskMapper.toGetDto(taskCrudRepository.save(oldTask));
    }

    @Override
    public void deleteOneById(Long id) {
        Task task = this.findOneEntityById(id);
        taskCrudRepository.delete(task);
    }
}
