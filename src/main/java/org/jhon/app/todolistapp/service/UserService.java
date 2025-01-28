package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    List<User> findAllByFirstname(String firstname);

    User findOneByFirstname(String findOneByFirstname);

    User findOneByUsername(String username);

    User createOne(User user);

    User UpdateOneByUsername(String firstname, User user);

    void deleteOneByUsername(String username);
}
