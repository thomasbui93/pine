package kbui.com.pine.exceptions.task;

import kbui.com.pine.exceptions.general.InternalErrorException;

public class TaskNotFoundException extends InternalErrorException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskNotFoundException(Long id) {
    super(String.format("Task not found with %s", id));
  }
}
