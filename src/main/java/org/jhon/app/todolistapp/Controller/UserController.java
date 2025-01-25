package org.jhon.app.todolistapp.Controller;

import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String name){
        List<User>  users = null;

        if (StringUtils.hasText(name)){
            users = userService.findAllByName(name);
        }else {
            users = userService.findAll();
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){

        try {

            return ResponseEntity.ok(userService.findOneByUsername(username)) ;
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }
}
