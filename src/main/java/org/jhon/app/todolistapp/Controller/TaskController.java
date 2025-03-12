package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.exception.ObjectNotFoundException;
import org.jhon.app.todolistapp.persistence.entity.Task;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
        try {
            System.out.println("entra al carch" + id);
            return ResponseEntity.ok(taskService.findOneById(id)) ;
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<GetTask>> findOneByName(@PathVariable String username){
        try {
            return ResponseEntity.ok(taskService.findByUsername(username)) ;
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

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


        try {

            GetTask updateTask = taskService.updateOneById(id, saveDto);
            return ResponseEntity.ok(updateTask);
        }catch (ObjectNotFoundException exception){
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable Long id){
        try {

            taskService.deleteOneById(id);
            return ResponseEntity.noContent().build();
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
    

}
