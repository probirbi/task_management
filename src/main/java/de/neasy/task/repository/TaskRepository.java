package de.neasy.task.repository;

import de.neasy.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> getAllByAssignToId(long assignToId);
}
