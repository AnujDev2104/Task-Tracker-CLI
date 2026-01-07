package com.anuj.tasktracker.controllers;
import com.anuj.tasktracker.models.Task;
import com.anuj.tasktracker.services.TaskService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class MainController {
    private final TaskService service;
    @Autowired
    public MainController(TaskService service){
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
        Task task1 = service.add(task);
//        return ResponseEntity.ok(task1);
        /*
        * ResponseEntity.ok(task1) creates generic success code 200 OK
        * ResponseEntity.status(HttpStatus.CREATED).body(task1); returns
        * created 201 successful code.
        * */
        return ResponseEntity.status(HttpStatus.CREATED).body(task1);
    }

    @GetMapping("/headers")
    public ResponseEntity<String> getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache", "none");
        headers.add("Custom-Header", "Spring Boot");
        return ResponseEntity.ok().headers(headers).body("Custom-Headers");
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = service.getAll();
        return ResponseEntity.ok(tasks);
    }
}
