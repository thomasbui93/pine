package kbui.com.pine.services.task;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import kbui.com.pine.entities.task.TaskEntity;
import kbui.com.pine.exceptions.general.InternalErrorException;
import kbui.com.pine.exceptions.task.TaskCreationFailedException;
import kbui.com.pine.exceptions.task.TaskDeletionFailedException;
import kbui.com.pine.exceptions.task.TaskNotFoundException;
import kbui.com.pine.exceptions.task.TaskUpdateFailedException;
import kbui.com.pine.respositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public TaskEntity getOne(Long id) throws TaskNotFoundException, InternalErrorException {
    try {
      return taskRepository.findById(id).get();
    } catch (NoSuchElementException exc) {
      throw new TaskNotFoundException(id);
    } catch (Exception err) {
      throw new InternalErrorException(err.getMessage());
    }
  }

  @CachePut(value = "task", key = "#id")
  public TaskEntity updateOne(Long id, TaskEntity entity) {
    try {
      TaskEntity task = taskRepository.findById(id).get();
      task.setStatus(entity.getStatus());
      taskRepository.save(task);
      return task;
    } catch (NoSuchElementException err) {
      throw new TaskNotFoundException(id);
    } catch (IllegalArgumentException err) {
      throw new TaskUpdateFailedException(id);
    } catch (Exception err) {
      throw new InternalErrorException(err.getMessage());
    }
  }

  @CacheEvict(value = "task", key = "#id")
  public void removeOne(Long id) {
    try {
      taskRepository.deleteById(id);
    } catch (IllegalArgumentException err) {
      throw new TaskDeletionFailedException(id);
    } catch (Exception err) {
      throw new InternalErrorException(err.getMessage());
    }
  }

  public TaskEntity createOne(TaskEntity task) {
    try {
      return taskRepository.save(task);
    } catch (IllegalArgumentException err) {
      throw new TaskCreationFailedException();
    } catch (Exception err) {
      throw new InternalErrorException(err.getMessage());
    }
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
