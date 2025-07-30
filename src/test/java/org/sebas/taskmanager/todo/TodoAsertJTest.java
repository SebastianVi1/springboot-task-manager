package org.sebas.taskmanager.todo;

import org.junit.jupiter.api.Test;
import org.sebas.taskmanager.model.Task;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TodoAsertJTest {

    @Test
    void sholdCreateATodo(){
        var test = new Task(1L, "TEST", true);

        var test2 = new Task(1L, "TEST", true);

        assertThat(test.getDescription())
                .startsWith("T")
                .endsWith("T")
                .contains(("ES"))
                .isEqualToIgnoringCase("test");

        assertThat(test.getId())
                .isPositive()
                .isEqualTo(1);

    }


}
