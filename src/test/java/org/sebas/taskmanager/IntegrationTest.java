package org.sebas.taskmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sebas.taskmanager.model.Task;
import org.sebas.taskmanager.model.TaskDto;
import org.sebas.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TaskRepo taskRepo;

    @BeforeEach
    void setUp(){
        taskRepo.deleteAll(); //delete db before every test
    }

    @Test
    void shouldCreateATaskSuccessfully() throws Exception {
        TaskDto taskDto = new TaskDto("This is a integration test", true);
        String json = objectMapper.writeValueAsString(taskDto); // transform the object into a json

        mockMvc.perform(post("/api/add-task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());


        List<Task> list = taskRepo.findAll();
        assertThat(list)
                .size().isEqualTo(1);
        assertThat(list.get(0).getDescription())
                .startsWith("T")
                .endsWith("t")
                .isEqualToIgnoringCase("this is a integration test");

        assertThat(list.get(0).isCompleted()).isEqualTo(true);


    }

    @Test
    void shouldDeleteATaskSuccessfully() throws Exception {
        Task t = new Task(null, "TEST", false);
        Task task = taskRepo.save(t);

        assertThat(task)
                .isIn(taskRepo.findAll());
        mockMvc.perform(delete("/api/delete-task/{id}", task.getId()))
                .andExpect(status().isOk());


    }

    @Test
    void shouldReturnAllTaskWithWithStatusOk() throws Exception {
        Task task1 = new Task(null, "Learn Testing", false);
        Task task2 = new Task(null, "Learn Mockito", true);

        taskRepo.saveAll(List.of(task1, task2));
        assertThat(task1).isIn(taskRepo.findAll());
        mockMvc.perform(get("/api/get-tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Learn Testing"));

    }

    @Test
    void shouldCompleteAExistentTask() throws Exception {

        Task task1 = new Task(null, "Learn Testing", false);
        Task newTask = taskRepo.save(task1);

        assertThat(newTask)
                .isIn(taskRepo.findAll());
        String json = objectMapper.writeValueAsString(newTask);
        mockMvc.perform(put("/api/complete-task/{id}", newTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
        assertThat(taskRepo
                .findById(newTask
                    .getId())
                    .get()
                    .isCompleted())
                .isTrue();
    }
}
