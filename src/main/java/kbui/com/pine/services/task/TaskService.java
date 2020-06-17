package kbui.com.pine.services.task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import kbui.com.pine.entities.task.TaskEntity;
import kbui.com.pine.respositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class TaskService {
  @Autowired TaskRepository taskRepository;

  @Cacheable(value = "tasks", key = "#page-#size")
  public List<TaskEntity> getAll(Integer page, Integer size) {
    Pageable paging = PageRequest.of(page - 1, size);
    Page<TaskEntity> tasks = taskRepository.findAll(paging);
    return tasks.getContent();
  }

  @Cacheable(value = "task", key = "#id")
  public TaskEntity getOne(Long id) {
    try {
      return taskRepository.findById(id).get();
    } catch (NoSuchElementException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found.", exc);
    } catch (Exception err) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error.", err);
    }
  }

  @CachePut(value = "task", key = "#id")
  public TaskEntity updateOne(Long id, TaskEntity entity) {
    TaskEntity task = taskRepository.findById(id).get();
    task.setStatus(entity.getStatus());
    taskRepository.save(task);
    return task;
  }

  @CacheEvict(value = "task", key = "#id")
  public void removeOne(Long id) {
    taskRepository.deleteById(id);
  }

  public TaskEntity createOne(TaskEntity task) {
    return taskRepository.save(task);
  }

  public List<TaskEntity> createBatch(TaskEntity taskData) {
    TaskEntity task = this.createOne(taskData);
    List<TaskEntity> tasks = new ArrayList<TaskEntity>();
    String[] messages = task.getMessages().split(" ");
    for (String message : messages) {
      TaskEntity subTask = new TaskEntity();
      subTask.setMessages(message);
      subTask.setParentId(task.getId());
      tasks.add(subTask);
    }
    tasks = this.taskRepository.saveAll(tasks);
    tasks.add(task);
    return tasks;
  }
}
