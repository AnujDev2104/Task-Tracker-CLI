package com.anuj.tasktracker.services;

import com.anuj.tasktracker.models.Task;
import com.anuj.tasktracker.repo.TaskRepo;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepo repo;

    public TaskService(TaskRepo repo){
        this.repo = repo;
    }
    public Task add(Task task){
        return repo.save(task);
    }
}
