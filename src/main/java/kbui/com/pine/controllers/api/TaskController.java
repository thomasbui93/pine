package kbui.com.pine.controllers.api;

import java.util.List;
import kbui.com.pine.entities.task.TaskEntity;
import kbui.com.pine.exceptions.task.TaskBatchCreationFailedException;
import kbui.com.pine.exceptions.task.TaskCreationFailedException;
import kbui.com.pine.exceptions.task.TaskDeletionFailedException;
import kbui.com.pine.exceptions.task.TaskNotFoundException;
import kbui.com.pine.exceptions.task.TaskUpdateFailedException;
import kbui.com.pine.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  @Autowired TaskService taskService;

  @GetMapping("")
  public List<TaskEntity> getAll(
      @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
      @RequestParam(value = "size", defaultValue = "20", required = false) Integer size) {
    return taskService.getAll(page, size);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public TaskEntity getOne(@PathVariable Long id) throws ResponseStatusException {
    try {
      return taskService.getOne(id);
    } catch (TaskNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
  }

  @PostMapping("")
  public TaskEntity createOne(@RequestBody TaskEntity taskData) throws ResponseStatusException {
    try {
      return taskService.createOne(taskData);
    } catch (TaskCreationFailedException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, ex.getMessage());
    }
  }

  @PostMapping("/batch")
  public List<TaskEntity> createBatch(@RequestBody TaskEntity taskData) throws ResponseStatusException {
    try {
      return taskService.createBatch(taskData);
    } catch (TaskBatchCreationFailedException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, ex.getMessage());
    }
  }

  @PutMapping("/{id}")
  public TaskEntity updateOne(@PathVariable Long id, @RequestBody TaskEntity taskData) throws ResponseStatusException {
    try {
      return taskService.updateOne(id, taskData);
    } catch (TaskNotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    } catch (TaskUpdateFailedException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, ex.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteOne(@PathVariable Long id) throws ResponseStatusException {
    try {
      taskService.removeOne(id);
    } catch (TaskDeletionFailedException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, ex.getMessage());
    }
  }
}
