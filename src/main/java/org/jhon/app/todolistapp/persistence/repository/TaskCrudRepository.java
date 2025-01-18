package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCrudRepository extends JpaRepository<Task,Long> {

    List<Task> findByTitleContaining(String title);

    List<Task> findByCategoryContaining(String category);

    List<Task> findByCategoryAndTitleContaining (String category, String title);

    List<Task> findByUserId(Long userId);

    List<Task> findByUserUsername (String username);

}
