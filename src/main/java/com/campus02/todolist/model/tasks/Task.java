package com.campus02.todolist.model.tasks;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import com.campus02.todolist.model.users.User;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

  @PrePersist
  public void fillIdIfNull() {
    if (id != null)
      return;

    this.setId(UUID.randomUUID());
  }

  @Id
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
  private UUID id;
  @Column(nullable = false, length = 64)
  private String title;
  @Column(nullable = false, length = 1024)
  private String description;
  @JoinColumn(name = "originator_user_id", insertable = false, updatable = false)
  @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
  private User originatorUser;

  @Column(name = "originator_user_id")
  private Integer originatorUserId;
  @Column
  private Long lastModifiedTimestamp;
  @Column
  private boolean isPublic;
  @Column
  private boolean isCompleted;

  public static final Validator<Task> validator = ValidatorBuilder.<Task>of()
          .constraint(Task::getTitle, "name", CharSequenceConstraint::notBlank)
          .constraint(Task::getDescription, "description", CharSequenceConstraint::notBlank)
          .build();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public User getOriginatorUser() {
    return originatorUser;
  }

  public void setOriginatorUser(User originatorUser) {
    this.originatorUser = originatorUser;
  }

  public Integer getOriginatorUserId() {
    return originatorUserId;
  }

  public void setOriginatorUserId(Integer originatorUserId) {
    this.originatorUserId = originatorUserId;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean aPublic) {
    isPublic = aPublic;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean completed) {
    isCompleted = completed;
  }

  public void setLastModifiedTimestamp(Long lastModifiedTimestamp) {
    this.lastModifiedTimestamp = lastModifiedTimestamp;
  }
  public Long getLastModifiedTimestamp() {
    return lastModifiedTimestamp;
  }

  @Override
  public String toString() {
    return "Task{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", originatorUser=" + originatorUser +
            ", lastModifiedTimestamp=" + lastModifiedTimestamp +
            ", isPublic=" + isPublic +
            ", isCompleted=" + isCompleted +
            '}';
  }
}