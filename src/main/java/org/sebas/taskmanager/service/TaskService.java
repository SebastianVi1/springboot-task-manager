package org.sebas.taskmanager.service;

import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.model.TaskDto;
import org.sebas.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepo repo;

    public TaskService(TaskRepo taskRepo) {
        this.repo = taskRepo;
    }




    public ResponseEntity<List<Task>> getAllTasks() {

        List<Task> taskList = repo.findAll();

        return ResponseEntity.ok(taskList);

    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }

    public ResponseEntity<Task> addTask(TaskDto task) {
        try {
            Task tas = new Task();
            tas.setDescription(task.getDescription());
            tas.setCompleted(task.isCompleted());
            Task task1 = repo.save(tas);
            return ResponseEntity.ok(task1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    public void deleteAll() {
        repo.deleteAll();
    }

    public void completeTask(Task task) {
        task.setCompleted(true);
        repo.save(task);
    }
}
