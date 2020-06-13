package kbui.com.pine.respositories.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kbui.com.pine.entities.task.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
