package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.sql.Delete;
import org.jhon.app.todolistapp.dto.request.SaveUser;
import org.jhon.app.todolistapp.dto.response.GetUser;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.User;
import org.jhon.app.todolistapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<GetUser>> findAll(@RequestParam(required = false) String username,
                                                 Pageable pageable){

        Page<GetUser> users = userService.findAll(pageable);


        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GetUser> findByUsername(@PathVariable String username){

            return ResponseEntity.ok(userService.findOneByUsername(username)) ;


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
            GetUser updateUser = userService.UpdateOneByUsername(username, saveDto);
            return ResponseEntity.ok(updateUser);

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteOneByUseranme(@PathVariable String username){


            userService.deleteOneByUsername(username);
            return ResponseEntity.noContent().build();

    }

}
