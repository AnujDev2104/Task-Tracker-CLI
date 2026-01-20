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
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class MainController {
    private final TaskService service;

    @Autowired
    public MainController(TaskService service) {
        this.service = service;
    }

    @GetMapping("get-all")
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleated = service.delete(id);
        return deleated ? ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> updatedTask = service.update(id, task);
        return updatedTask.map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
