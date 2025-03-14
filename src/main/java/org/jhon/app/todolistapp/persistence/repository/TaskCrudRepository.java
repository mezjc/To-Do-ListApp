package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskCrudRepository extends JpaRepository<Task,Long> {

    List<Task> findByTitleContaining(String title);

    List<Task> findByCategoryGenreContaining(String category);

    List<Task> findByCategoryGenreAndTitleContaining (String category, String title);

    List<Task> findByUserId(Long usersId);

    List<Task> findByUserUsername (String username);

}
