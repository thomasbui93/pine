package kbui.com.pine.exceptions.task;

import kbui.com.pine.exceptions.general.GenericErrorException;

public class TaskNotFoundException extends GenericErrorException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskNotFoundException(Long id) {
    super(String.format("Task not found with %s", id));
  }
}
