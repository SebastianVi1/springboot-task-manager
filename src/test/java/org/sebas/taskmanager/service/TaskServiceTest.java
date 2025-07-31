package org.sebas.taskmanager.service;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.repo.TaskRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock // emulate the repo
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService underTest;


    @Test
    void shouldGetAllTasks() {
        var task = new Task(1L, "Mock", true);
        when(taskRepo.findAll()).thenReturn(List.of(
                task
        ));
        List<Task> result = underTest.getAllTasks();
        assertThat(result)
                .isNotNull();

        //check if the object in the list is the same
        assertThat(result.get(0))
                .isNotNull()
                .isEqualTo(task);

        // verify if the repo is used
        verify(taskRepo).findAll();
    }

    @Test
    void shouldDeleteATask(){
        doNothing().when(taskRepo).deleteById(1L);

        underTest.deleteTask(1L);
        verify(taskRepo).deleteById(1L);
    }


    @Test
    void shouldAddATask(){
        ArgumentCaptor<Task> taskCaptor;
        Task inputTask = new Task(1L, "Learn Mockito", false);
        when(taskRepo.save(any(Task.class))).thenReturn(inputTask);


        underTest.addTask(inputTask);


        taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepo).save(taskCaptor.capture());


        Task captured = taskCaptor.getValue();
        assertThat(captured)
                .isEqualTo(inputTask);
        assertThat(captured.getDescription())
                .isEqualToIgnoringCase("learn mockito");
        assertThat(captured.isCompleted()).isFalse();
    }

    @Test
    void shouldDeleteAll(){
        underTest.deleteAll();

        verify(taskRepo).deleteAll();
    }


}