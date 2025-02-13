package org.jhon.app.todolistapp.mapper;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.persistence.entity.Task;

import java.util.List;

public class TaskMapper {

    public static GetTask toGetDto(Task entity){
        if (entity == null) return null;

        return new GetTask(
                entity.getId(),
                entity.getUser().getUsername(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getState(),
                entity.getCreatedAt(),
                CategoryMapper.toGetTaskCategoryDto(entity.getCategory())
        );
    }
    public static GetUser.GetTaskUser toGetTaskUser(Task entity){
        if (entity == null) return null;

        return new GetUser.GetTaskUser(
                entity.getId(),
                entity.getTitle(),
                entity.getState(),
                entity.getCreatedAt(),
                entity.getCategory().getGenre()

        );
    }

    public static List<GetTask> toGetDtoList(List<Task> entities){
        if (entities == null) return null;

        return entities.stream()
                .map( each -> TaskMapper.toGetDto(each))
                .toList();
    }

    public static List<GetUser.GetTaskUser> toGetTaskUserList(List<Task> entities){
        if (entities == null) return null;

        return entities.stream()
                .map( each -> TaskMapper.toGetTaskUser(each))
                .toList();
    }



    public static Task toEntity(SaveTask saveDto){
        if (saveDto == null) return null;

        Task newTask= new Task();
        newTask.setUserId(saveDto.userId());
        newTask.setTitle(saveDto.title());
        newTask.setDescription(saveDto.description());
        newTask.setState(saveDto.state());
        newTask.setCategory(CategoryMapper.toGetEntityCategory(saveDto.category()));

        return newTask;

    }

    public static void updateEntity(Task oldTask, SaveTask saveDto){
        if (oldTask == null || saveDto == null) return;
        oldTask.setDescription(saveDto.description());
        oldTask.setState(saveDto.state());
        oldTask.setTitle(saveDto.title());
        oldTask.setCategory(CategoryMapper.toGetEntityCategory(saveDto.category()));
    }
}
