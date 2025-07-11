package org.sebas.taskmanager.service;

import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepo repo;

    @Autowired
    public void setRepo(TaskRepo repo){
        this.repo = repo;
    }


    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    public void deleteTask(int id) {
        repo.deleteById(id);
    }

    public ResponseEntity addTask(Task task) {
        try{
            repo.save(task);
            return new ResponseEntity<Task>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }


    public void deleteAll() {
        repo.deleteAll();
    }

    public void completeTask(Task task) {
        repo.save(task);
    }
}
