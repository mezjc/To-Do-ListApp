package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(@RequestParam(required = false) String username){
        List<User>  users = null;

        if (StringUtils.hasText(username)){
            users =  userService.findAllByFirstname(username);
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

    @PostMapping
    public ResponseEntity<User> createOne(@RequestBody User user,
                                          HttpServletRequest request){
        User createdUser = userService.createOne(user);
        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + user.getUsername());

        return ResponseEntity.created(newLocation).body(createdUser);
    }

    @PutMapping("/{username}")
    public  ResponseEntity<User> updateOneById(@PathVariable String username,
                                               @RequestBody User user){
        try {
            User updateUser = userService.UpdateOneByUsername(username, user);
            return ResponseEntity.ok(updateUser);
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteOneByUseranme(@PathVariable String username){

        try {
            userService.deleteOneByUsername(username);
            return ResponseEntity.noContent().build();
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.noContent().build();
        }
    }
}
