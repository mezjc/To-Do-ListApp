package org.jhon.app.todolistapp.Controller;

import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll(@RequestParam(required = false) String name){
        List<User>  users = null;

        if (StringUtils.hasText(name)){
            users = userService.findAllByName(name);
        }else {
            users = userService.findAll();
        }

        return users;
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findOneByUsername(username);
    }
}
