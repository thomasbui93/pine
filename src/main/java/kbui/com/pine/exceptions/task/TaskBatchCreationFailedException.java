package kbui.com.pine.exceptions.task;

public class TaskBatchCreationFailedException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766939L;

  public TaskBatchCreationFailedException() {
    super("Task batch creation failed.");
  }
}
