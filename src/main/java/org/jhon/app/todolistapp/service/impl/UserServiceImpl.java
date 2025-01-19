package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.persistence.repository.UserCrudRepository;
import org.jhon.app.todolistapp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }


    @Override
    public List<User> findAll() {
        return userCrudRepository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        return userCrudRepository.findByFirstnameContaining(name);
    }

    @Override
    public User findOneByUsername(String username) {
        return userCrudRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[user:"+username+"]"));
    }

    @Override
    public User createOne(User user) {
        return userCrudRepository.save(user);
    }

    @Override
    public User UpdateOneByUsername(String username, User user) {
        User oldUser = this.findOneByUsername(username);
        oldUser.setFirstname(user.getFirstname());
        oldUser.setLastname(user.getLastname());
        oldUser.setPassword(user.getPassword());
        return userCrudRepository.save(oldUser);
    }

    @Override
    public void deleteOneByUsername(String username) {

        int deletedRecords = userCrudRepository.deleteByUsername(username);
        if (deletedRecords != 1){
            throw new ObjectNotFoundException("[user:"+username+"]");
        }
    }
}
