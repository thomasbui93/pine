package kbui.com.pine.entities.task;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class TaskEntity {
  @Id @GeneratedValue @Getter private Long id;

  @Getter
  @Setter
  @Column(nullable = true)
  private Long parentId;

  @Getter
  @Setter
  @Column(nullable = false, updatable = false)
  protected String messages;

  @Setter
  @Getter
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(32) default 'TODO'")
  private TaskStatus status;

  @Getter @Setter @CreationTimestamp private Timestamp createdAt;

  @Getter @Setter @UpdateTimestamp private Timestamp updatedAt;
}
