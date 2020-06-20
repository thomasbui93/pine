package kbui.com.pine.exceptions.task;

public class TaskDeletionFailedException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskDeletionFailedException(Long id) {
    super(String.format("Task removal failed with id %s", id));
  }
}
