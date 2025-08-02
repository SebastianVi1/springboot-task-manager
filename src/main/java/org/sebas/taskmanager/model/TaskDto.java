package org.sebas.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskDto {
    String description;
    @JsonProperty("isCompleted")
    boolean isCompleted;

    public TaskDto(String description, boolean isCompleted){
        this.description = description;
        this.isCompleted = isCompleted;
    }
}
