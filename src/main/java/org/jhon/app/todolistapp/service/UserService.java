package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.persistence.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    List<User> findAllByName(String name);

    User findOneByUsername(String username);

    User createOne(User user);

    User UpdateOneByUsername(String username, User user);

    void deleteOneByUsername(String username);
}
