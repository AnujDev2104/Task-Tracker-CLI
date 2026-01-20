package com.anuj.tasktracker.services;

import com.anuj.tasktracker.models.Task;
import com.anuj.tasktracker.repo.TaskRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public  Optional<List<Task>> getAllDoneTask() {
        List<Task> allTask = getAll();
        List<Task> doneTask = new ArrayList<>();
        for(Task task : allTask){
            if(task.isDone()) doneTask.add(task);
        }
        return Optional.of(doneTask);
    }

    public Optional<List<Task>> getNotDoneTasks() {
        List<Task> allTasks = getAll();
        List<Task> notDoneTasks = new ArrayList<>();
        for(Task task : allTasks){
            if(!task.isDone()) notDoneTasks.add(task);
        }
        return Optional.of(notDoneTasks);
    }
}
