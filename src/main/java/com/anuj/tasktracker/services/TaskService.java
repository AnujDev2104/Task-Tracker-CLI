package com.anuj.tasktracker.services;

import com.anuj.tasktracker.models.Task;
import com.anuj.tasktracker.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo repo) {
        taskRepo = repo;
    }

    public List<Task> getAll() {
        return taskRepo.findAll();
    }

    public Task add(Task task) {
        return taskRepo.save(task);
    }

    public boolean delete(Long id) {
         if(taskRepo.existsById(id)){
             taskRepo.deleteById(id);
             return true;
         }
         return false;
    }

    public Optional<Task> update(Long id, Task task) {
        return taskRepo.findById(id).map(existing -> {
            existing.setDone(task.isDone());
            existing.setInProgress(task.isInProgress());
            existing.setDescription(task.getDescription());
            existing.setName((task.getName()));
            taskRepo.save(existing);
            return existing;
        });
    }
}
