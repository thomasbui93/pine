package kbui.com.pine.services.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kbui.com.pine.entities.task.TaskEntity;
import kbui.com.pine.respositories.task.TaskRepository;

@Service
public class TaskService {
  @Autowired
  TaskRepository taskRepository;

  public List<TaskEntity> getTasks(Integer page, Integer size) {
    Pageable paging = PageRequest.of(page - 1, size);
    Page<TaskEntity> tasks = taskRepository.findAll(paging);
    return tasks.getContent();
  }

  public TaskEntity getTask(Long id) {
    return taskRepository.findById(id).get();
  }

  public TaskEntity updateTask(Long id, TaskEntity entity) {
    TaskEntity task = taskRepository.findById(id).get();
    task.setStatus(entity.getStatus());
    taskRepository.save(task);
    return task;
  }

  public void removeTask(Long id) {
    taskRepository.deleteById(id);
  }

  public TaskEntity createTask(TaskEntity task) {
    return taskRepository.save(task);
  }
}
