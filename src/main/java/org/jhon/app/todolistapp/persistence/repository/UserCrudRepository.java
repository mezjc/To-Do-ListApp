package org.jhon.app.todolistapp.persistence.repository;

import org.jhon.app.todolistapp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<User, Long> {

    List<User> findByFirstnameContaining(String firstname);

    Optional<User> findByFirstname(String firstname);

    Optional<User> findByUsername (String username);

    @Modifying
    @Transactional
    int deleteByUsername(String username);
}
