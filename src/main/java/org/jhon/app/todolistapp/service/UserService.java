package org.jhon.app.todolistapp.service;

import org.jhon.app.todolistapp.dto.request.SaveUser;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.persistence.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<GetUser> findAll();

    List<GetUser> findAllByFirstname(String firstname);

    GetUser findOneByFirstname(String findOneByFirstname);

    GetUser findOneByUsername(String username);

    GetUser createOne(SaveUser saveUser);

    GetUser UpdateOneByUsername(String firstname, SaveUser saveUser);

    void deleteOneByUsername(String username);

    void deleteAll();
}
