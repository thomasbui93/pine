package kbui.com.pine.exceptions.task;

public class TaskUpdateFailedException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskUpdateFailedException(Long id) {
    super(String.format("Task update failed with id %s", id));
  }
}
