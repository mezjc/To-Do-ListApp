package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.request.TaskSearchCriteria;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.mapper.TaskMapper;
import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.persistence.repository.CategoryCrudRepository;
import org.jhon.app.todolistapp.persistence.repository.TaskCrudRepository;
import org.jhon.app.todolistapp.persistence.specification.FindAllTaskSpecification;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public Page<GetTask> findAll(TaskSearchCriteria searchCriteria, Pageable pageable) {
        FindAllTaskSpecification taskSpecification = new FindAllTaskSpecification(searchCriteria);
        Page<Task>entities = taskCrudRepository.findAll(taskSpecification, pageable);

        return entities.map(entity -> TaskMapper.toGetDto(entity));
    }


    @Transactional(readOnly = true)
    @Override
    public Page<GetTask> findByUsername(String username,Pageable pageable) {
        Page<Task> entities = taskCrudRepository.findByUserUsername(username,pageable);

        return entities.map(each -> TaskMapper.toGetDto(each));
    }

    @Transactional(readOnly = true)
    @Override
    public GetTask findOneById(Long id) {
        return TaskMapper.toGetDto(this.findOneEntityById(id));

    }


    @Transactional(readOnly = true)
    public Task findOneEntityById(Long id) {

        return taskCrudRepository.findById(id)
                //.orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404), "User not found "+ id));
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
