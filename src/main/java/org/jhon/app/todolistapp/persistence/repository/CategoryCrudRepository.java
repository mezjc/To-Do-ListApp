package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryCrudRepository extends JpaRepository<Category, Long> {


    List<Category> findByTaskUserUsername(String username);

    Optional<Category> findByGenre(String genre);
}
