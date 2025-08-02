package org.sebas.taskmanager.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.model.TaskDto;
import org.sebas.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TaskService taskService; //We simulate the service.

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnOkWhenRequestTasks() throws Exception {
            mockMvc.perform(get("/api/get-tasks"))
                    .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOkAddingATask() throws Exception {
        TaskDto taskDto = new TaskDto("TEst", true);
        mockMvc.perform(post("/api/add-task")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk());
    }


    @Test
    void shoudReturnOkWithDeletingATask() throws Exception {
        mockMvc.perform(delete("/api/delete-task/{id}", 1 ))
                .andExpect(status().isOk());
    }

}
