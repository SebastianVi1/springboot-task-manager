package org.sebas.taskmanager.repo;

import org.sebas.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {


}
