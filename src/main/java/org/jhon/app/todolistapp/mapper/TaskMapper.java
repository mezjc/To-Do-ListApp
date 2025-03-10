package org.jhon.app.todolistapp.mapper;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.persistence.entity.Task;

import java.util.List;

public class TaskMapper {

    public static GetTask toGetDto(Task entity){
        if (entity == null)return null;

        GetTask dto = new GetTask(
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getState(),
                entity.getCreatedAt(),
               entity.getCategory().getGenre()
        );

        return dto;
    }

    public static List<GetTask> toGetDtoList(List<Task> entities){
        if (entities == null)return null;

        return entities.stream()
                .map(e -> TaskMapper.toGetDto(e))
                .toList();

    }

    public static Task toEntity(SaveTask saveDto){
        if (saveDto == null)return null;

        Task newTask = new Task();
        newTask.setUserId(saveDto.userId());
        newTask.setTitle(saveDto.title());
        newTask.setDescription(saveDto.description());
        newTask.setState(saveDto.state());
        newTask.setCategory(CategoryMapper.toGetEntity(saveDto.category()));
        
        return newTask;
    }

    public static GetUser.GetUserTask toGetDtoUser(Task entity) {
        if (entity == null) return null;

        GetUser.GetUserTask dto = new GetUser.GetUserTask(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getState(),
                entity.getCreatedAt(),
                entity.getCategory().getGenre()
        );
        return dto;
    }

    public static List<GetUser.GetUserTask> toGetListDtoUser (List<Task> entities){
        if (entities == null)return null;
        return entities.stream().map(each -> toGetDtoUser(each)).toList();
    }

    public static void updateEntity(Task oldTask, SaveTask saveDto) {
        if (oldTask == null || saveDto == null)return;
        oldTask.setUserId(saveDto.userId());
        oldTask.setTitle(saveDto.title());
        oldTask.setDescription(saveDto.description());
        oldTask.setState(saveDto.state());
        oldTask.setCategory(CategoryMapper.toGetEntity(saveDto.category()));
    }
}
