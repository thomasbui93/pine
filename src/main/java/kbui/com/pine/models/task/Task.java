package kbui.com.pine.models.task;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Value;

enum TaskStatus {
  TODO,
  IN_PROGRESS,
  DONE,
  FAILED,
  RETRYING
}

@Value
@AllArgsConstructor
public class Task {
  private Long id;
  private Optional<Task> parent;
  private List<String> messages;
  private TaskStatus status;
  private Date createdAt;
  private Date updateAt;
}
