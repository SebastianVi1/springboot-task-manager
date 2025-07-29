package org.sebas.taskmanager.todo;

import org.junit.jupiter.api.Test;
import org.sebas.taskmanager.model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TodoJunitTest {

    @Test
    void shouldCreateNewTodo(){

        var test = new Task(1L, "TEST", true);
        assertEquals("TEST", test.getDescription(), "Task description is not TEST");

        var failedTest = new Task(2L, "DESCRIPTION", false);
        assertFalse(failedTest.isComplete(), "The task is true");
    }

}
