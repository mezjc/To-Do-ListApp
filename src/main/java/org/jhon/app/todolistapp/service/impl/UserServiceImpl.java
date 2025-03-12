package org.jhon.app.todolistapp.service.impl;

import org.jhon.app.todolistapp.dto.request.SaveUser;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.mapper.UserMapper;
import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.persistence.repository.UserCrudRepository;
import org.jhon.app.todolistapp.service.UserService;
import org.jhon.app.todolistapp.service.validator.PasswordValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserCrudRepository userCrudRepository;

    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<GetUser> findAll() {
        List<User>entities =  userCrudRepository.findAll();
        return UserMapper.toGetDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetUser> findAllByFirstname(String firstname) {
        List<User>entities = userCrudRepository.findByFirstnameContaining(firstname);
        return UserMapper.toGetDtoList(entities);

    }

    @Transactional(readOnly = true)
    @Override
    public GetUser findOneByFirstname(String name) {
        return userCrudRepository.findByFirstname(name)
                .map(each ->UserMapper.toGetDto(each))
                .orElseThrow(() -> new ObjectNotFoundException("[user:"+name+"]"));
    }

    @Transactional(readOnly = true)
    @Override
    public GetUser findOneByUsername(String username) {
        return UserMapper.toGetDto(this.findOneEntityByUsername(username));
    }

    @Transactional(readOnly = true)
    public User findOneEntityByUsername(String username) {
        return userCrudRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[user:"+username+"]"));
    }

    @Override
    public GetUser createOne(SaveUser saveUser) {
        System.out.println(" estos son los password" + saveUser.password() +" ----- " + saveUser.passwordRepeated());
        PasswordValidator.validatePassword(saveUser.password(), saveUser.passwordRepeated());

        User newUser = userCrudRepository.save(UserMapper.toEntity(saveUser));
        return UserMapper.toGetDto(newUser);
    }

    @Override
    public GetUser UpdateOneByUsername(String firstname, SaveUser saveUser) {
        PasswordValidator.validatePassword(saveUser.password(), saveUser.passwordRepeated());


        User oldUser = this.findOneEntityByUsername(firstname);
        UserMapper.updateEntity(oldUser, saveUser);
        return UserMapper.toGetDto(userCrudRepository.save(oldUser));
    }

    @Override
    public void deleteOneByUsername(String username) {

        int deletedRecords = userCrudRepository.deleteByUsername(username);
        if (deletedRecords != 1){
            throw new ObjectNotFoundException("[user:"+username+"]");
        }
    }

    @Override
    public void deleteAll() {
        userCrudRepository.deleteAll();
        throw  new RuntimeException();
    }
}
