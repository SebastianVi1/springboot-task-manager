package org.sebas.taskmanager.todo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sebas.taskmanager.controller.TaskController;
import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.ReplaceWithMock;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskRepo repo;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindAllTodos() throws Exception {
        var tasks = List.of(
                new Task(1L, "TEST!", false),
                new Task(2L, "second TEST", true)

        );
        Mockito.when(repo.findAll()).thenReturn(tasks);
        mockMvc.perform(get("/api/get-tasks"))
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(tasks))
                );

    }
}
