package org.jhon.app.todolistapp.mapper;

import org.jhon.app.todolistapp.dto.request.SaveUser;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.persistence.entity.User;

import java.util.List;

public class UserMapper {

    public static GetUser toGetDto(User entity){
        if (entity == null) return null;

        return new GetUser(
            entity.getFirstname(),
            entity.getLastname(),
            entity.getUsername(),
            TaskMapper.toGetListDtoUser(entity.getTasks())
        );
    }

    public static List<GetUser> toGetDtoList(List<User> entities){
        if (entities == null) return null;

        return entities.stream().map(each -> UserMapper.toGetDto(each)).toList();

    }

    public static User toEntity(SaveUser saveDto){
        if (saveDto == null) return null;

        User newUser = new User();
        newUser.setFirstname(saveDto.firstname());
        newUser.setLastname(saveDto.lastname());
        newUser.setUsername(saveDto.username());
        newUser.setPassword(saveDto.password());

        return newUser;
    }

    public static void updateEntity(User oldUser, SaveUser saveUser) {
        if (oldUser == null || saveUser == null)return;

        oldUser.setFirstname(saveUser.firstname());
        oldUser.setLastname(saveUser.lastname());
        oldUser.setPassword(saveUser.password());
        
    }
}
