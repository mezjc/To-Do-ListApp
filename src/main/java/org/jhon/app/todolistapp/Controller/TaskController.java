package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<Task>> findAll(@RequestParam(required = false) String category,
                                              @RequestParam(required = false) String title){

        List<Task> tareas = null;
        if (StringUtils.hasText(category) && StringUtils.hasText(title)){
            tareas = taskService.findByCategoryAndTile(category, title);
        } else if (StringUtils.hasText(category)) {
            tareas = taskService.findByCategory(category);
        } else if (StringUtils.hasText(title)) {
            tareas = taskService.findByTitle(title);
        }else {
            tareas = taskService.findAll();
        }

        return ResponseEntity.ok(tareas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findByOne(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.findOneById(id)) ;
        }catch (ObjectNotFoundException exception){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Task> createOne(@RequestBody Task task, HttpServletRequest request){
        Task taskCreated = taskService.createOne(task);

        String baseUrl = request.getRequestURL().toString();
        URI newLocation = URI.create(baseUrl + "/" + taskCreated.getId());

        return ResponseEntity.created(newLocation).body(taskCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateOneById(@PathVariable Long id, @RequestBody Task task){


        try {

        Task updateTask = taskService.updateOneById(id, task);
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
