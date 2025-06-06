package org.jhon.app.todolistapp.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jhon.app.todolistapp.dto.request.SaveTask;
import org.jhon.app.todolistapp.dto.request.TaskSearchCriteria;
import org.jhon.app.todolistapp.dto.response.GetTask;
import org.jhon.app.todolistapp.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping
    public ResponseEntity<Page<GetTask>> findAll(@RequestParam(required = false) String category,
                                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseYear,
                                                 @RequestParam(required = false)  String state,
                                                 @RequestParam(required = false)  String title,
                                                 Pageable taskPageable){


        TaskSearchCriteria searchCriteria = new TaskSearchCriteria(category,releaseYear,state, title);
        Page<GetTask> tasks = taskService.findAll(searchCriteria, taskPageable);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTask> findByOne(@PathVariable Long id){

            return ResponseEntity.ok(taskService.findOneById(id));

    }

    @GetMapping("/user/{username}")
    public ResponseEntity<Page<GetTask>> findOneByName(@PathVariable String username,Pageable pageable){
            return ResponseEntity.ok(taskService.findByUsername(username, pageable)) ;

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
