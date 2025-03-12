package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.sql.Delete;
import org.jhon.app.todolistapp.dto.request.SaveUser;
import org.jhon.app.todolistapp.dto.response.GetUser;
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
    public ResponseEntity<List<GetUser>> findAll(@RequestParam(required = false) String username){
        List<GetUser>  users = null;

        if (StringUtils.hasText(username)){
            users =  userService.findAllByFirstname(username);
        }else {
            users = userService.findAll();
        }

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUser> findByUsername(@PathVariable String username){

        try {

            return ResponseEntity.ok(userService.findOneByUsername(username)) ;
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<GetUser> createOne(@Valid @RequestBody SaveUser saveDto,
                                          HttpServletRequest request){
        GetUser createdUser = userService.createOne(saveDto);
        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + saveDto.username());

        return ResponseEntity.created(newLocation).body(createdUser);
    }

    @PutMapping("/{username}")
    public  ResponseEntity<GetUser> updateOneById(@PathVariable String username,
                                               @Valid @RequestBody SaveUser saveDto){
        try {
            GetUser updateUser = userService.UpdateOneByUsername(username, saveDto);
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
