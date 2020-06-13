package kbui.com.pine.entities.task;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
public class TaskEntity {
  @Id
  @GeneratedValue
  private Long id;

  @Getter
  @Setter
  @Column(nullable = true)
  private Long parentId;

  @Getter
  @Setter
  private String messages;

  @Getter
  @Setter
  private Integer status;


  @Getter
  @Setter
  @CreationTimestamp
  private Date createdAt;
  
  @Getter
  @Setter
  @UpdateTimestamp
  private Date updatedAt;
}
