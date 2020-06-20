package kbui.com.pine.exceptions.task;

public class TaskCreationFailedException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskCreationFailedException() {
    super("Task creation failed.");
  }
}
