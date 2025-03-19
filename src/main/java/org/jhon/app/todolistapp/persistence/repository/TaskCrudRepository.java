package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.Category;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskCrudRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {



    List<Task> findByUserUsername (String username);

}
