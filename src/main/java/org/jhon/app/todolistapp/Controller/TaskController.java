package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.ApiError;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.exception.InvalidPasswordException;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping
    public ResponseEntity<List<GetTask>> findAll(@RequestParam(required = false) String category,
                                                 @RequestParam(required = false) String title){

        List<GetTask> tasks = null;
        if (StringUtils.hasText(category) && StringUtils.hasText(title)){
            tasks = taskService.findByCategoryAndTile(category, title);
        } else if (StringUtils.hasText(category)) {
            tasks = taskService.findByCategory(category);
        } else if (StringUtils.hasText(title)) {
            tasks = taskService.findByTitle(title);
        }else {
            tasks = taskService.findAll();
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTask> findByOne(@PathVariable Long id){

            return ResponseEntity.ok(taskService.findOneById(id));

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<GetTask>> findOneByName(@PathVariable String username){
            return ResponseEntity.ok(taskService.findByUsername(username)) ;

    }

    @PostMapping
    public ResponseEntity<GetTask> createOne(@Valid @RequestBody SaveTask saveDto,
                                             HttpServletRequest request){
        GetTask taskCreated = taskService.createOne(saveDto);

        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + taskCreated.id());

        return ResponseEntity.created(newLocation).body(taskCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetTask> updateOneById(@PathVariable Long id,@Valid @RequestBody SaveTask saveDto){



            GetTask updateTask = taskService.updateOneById(id, saveDto);
            return ResponseEntity.ok(updateTask);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable Long id){


            taskService.deleteOneById(id);
            return ResponseEntity.noContent().build();

    }


}
