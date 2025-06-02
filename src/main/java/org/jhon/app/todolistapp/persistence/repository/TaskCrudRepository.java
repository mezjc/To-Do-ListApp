package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TaskCrudRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {

    Page<Task> findByUserUsername (String username, Pageable pageable);

}
