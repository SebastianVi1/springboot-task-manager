package org.sebas.taskmanager.controller;

import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {

    private TaskService service;

    @Autowired
    public void setService(TaskService service){
        this.service = service;
    }

    @GetMapping("/get-tasks")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @DeleteMapping("/delete-task/{id}")
    public void deleteTask(@PathVariable int id){
       service.deleteTask(id);
    }

    @PostMapping("/add-task")
    public ResponseEntity addTask(@RequestBody Task task){
        return service.addTask(task);
    }

    @PostMapping("/delete-all")
    public void deleteAll(){
        service.deleteAll();
    }

    @PutMapping("/complete-task/{id}")
    public void completeTask(@RequestBody Task task){
        service.completeTask(task);
    }
}
