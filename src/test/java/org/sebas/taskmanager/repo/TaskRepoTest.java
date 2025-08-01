package org.sebas.taskmanager.repo;

import org.junit.jupiter.api.Test;
import org.sebas.taskmanager.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepoTest {

    @Autowired
    TaskRepo repo;

    //Not necessary just for practice
    @Test
    void shouldSaveATask(){
        var task = new Task(null, "Wash the dishes", true);

        var response = repo.save(task);
        Optional<Task> optionalTask = repo.findById(response.getId());
        assertThat(optionalTask).isPresent();

        // Verificar la descripción
        assertThat(optionalTask.get().getDescription())
                .startsWith("W")
                .isEqualToIgnoringCase("wash the dishes")
                .isNotNull();

        // Verificar el status de completado
        assertThat(optionalTask.get().isCompleted())
                .isTrue();

        // Verificar que el ID se generó correctamente
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void shouldSaveATaskWithCompletedStatusFalse(){
        var task = new Task(null, "Learn Spring Boot", false);

        var response = repo.save(task);
        Optional<Task> optionalTask = repo.findById(response.getId());
        assertThat(optionalTask).isPresent();

        // Verificar la descripción
        assertThat(optionalTask.get().getDescription())
                .isEqualTo("Learn Spring Boot")
                .isNotNull();

        // Verificar el status de completado
        assertThat(optionalTask.get().isCompleted())
                .isFalse();

        // Verificar que el ID se generó correctamente
        assertThat(response.getId()).isNotNull();
    }
}