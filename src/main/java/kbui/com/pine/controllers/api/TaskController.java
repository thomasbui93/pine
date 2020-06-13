package kbui.com.pine.controllers.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kbui.com.pine.services.task.TaskService;
import kbui.com.pine.entities.task.TaskEntity;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  @Autowired 
  TaskService taskService;

  @GetMapping("")
  public List<TaskEntity> getAll(
    @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
    @RequestParam(value = "size", defaultValue = "20", required = false) Integer size
  ) {
    return taskService.getTasks(page, size);
  }

  @GetMapping("/{id}")
  public TaskEntity getOne(
    @RequestParam(value = "id", defaultValue = "1", required = false) Long id
  ) {
    return taskService.getTask(id);
  }

  @PostMapping("")
  public TaskEntity createOne(@RequestBody TaskEntity taskData) {
    return taskService.createTask(taskData);
  }
}
