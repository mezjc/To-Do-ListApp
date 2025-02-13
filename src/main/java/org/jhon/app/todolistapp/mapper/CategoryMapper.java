package org.jhon.app.todolistapp.mapper;

import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.persistence.entity.Category;

public class CategoryMapper {

    public static GetTask.GetCategory toGetTaskCategoryDto(Category entity){
        if (entity == null) return null;

        return new GetTask.GetCategory(
                entity.getId(),
                entity.getGenre()
        );

    }

    public static SaveTask.SaveCategory toSaveTaskCategoryDto(Category entity){
        if (entity == null) return null;

        return new SaveTask.SaveCategory(
                entity.getId()
        );

    }

    public static Category toGetEntityCategory(SaveTask.SaveCategory dto){
        if (dto == null) return null;

        Category newCategory = new Category();
        newCategory.setId(dto.genre());
        return newCategory;
    }
}
