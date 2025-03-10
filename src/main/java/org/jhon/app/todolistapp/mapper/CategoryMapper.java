package org.jhon.app.todolistapp.mapper;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;

public class CategoryMapper {

    public static GetTask.GetCategory toGetTaskCategoryDto(Category entity){
      if (entity == null)return null;

      return new GetTask.GetCategory(
              entity.getGenre()
      );

    }

    public static GetUser.GetUserTask toGetUserTaskDto(Task entity){
        if (entity == null)return null;

        String genre = entity.getCategory() != null
                ?entity.getCategory().getGenre()
                :null;

        return new GetUser.GetUserTask(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getState(),
                entity.getCreatedAt(),
                genre
        );

    }

    public static SaveTask.SaveCategory toGetSaveTaskCategory (Category entity){
        if (entity == null)return null;

        return new SaveTask.SaveCategory(
                entity.getGenre()
        );
    }

    public static Category toGetEntity (String dto){
        if (dto == null)return null;

        Category newCategory = new Category();

        newCategory.setGenre(dto);

        System.out.println("esto es del mapper " + newCategory);
        return newCategory;
    }

}
