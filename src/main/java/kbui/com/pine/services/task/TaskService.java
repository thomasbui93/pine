package kbui.com.pine.services.task;

import java.util.List;
import java.util.NoSuchElementException;
import kbui.com.pine.entities.task.TaskEntity;
import kbui.com.pine.respositories.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {
  @Autowired TaskRepository taskRepository;

  public List<TaskEntity> getAll(Integer page, Integer size) {
    Pageable paging = PageRequest.of(page - 1, size);
    Page<TaskEntity> tasks = taskRepository.findAll(paging);
    return tasks.getContent();
  }

  public TaskEntity getOne(Long id) {
    try {
      return taskRepository.findById(id).get();
    } catch (NoSuchElementException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found.", exc);
    } catch (Exception err) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error.", err);
    }
  }

  public TaskEntity updateOne(Long id, TaskEntity entity) {
    TaskEntity task = taskRepository.findById(id).get();
    task.setStatus(entity.getStatus());
    taskRepository.save(task);
    return task;
  }

  public void removeOne(Long id) {
    taskRepository.deleteById(id);
  }

  public TaskEntity createOne(TaskEntity task) {
    return taskRepository.save(task);
  }
}
