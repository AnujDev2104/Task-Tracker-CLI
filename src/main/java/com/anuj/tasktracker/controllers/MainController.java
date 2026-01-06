package com.anuj.tasktracker.controllers;
import com.anuj.tasktracker.models.Task;
import com.anuj.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MainController {
    private final TaskService service;
    @Autowired
    public MainController(TaskService service){
        this.service = service;
    }

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task){
        return service.add(task);
    }
}
