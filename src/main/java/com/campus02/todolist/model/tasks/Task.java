package com.campus02.todolist.model.tasks;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import com.campus02.todolist.model.users.User;

import javax.persistence.*;


@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false, length = 32)
  private String title;
  @Column(nullable = false, length = 256)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id", name = "originator_user_id")
  private User originatorUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(referencedColumnName = "id", name = "last_modified_user_id")
  private User lastModifiedUser;

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public User getLastModifiedUser() {
    return lastModifiedUser;
  }

  public Long getLastModifiedTimestamp() {
    return lastModifiedTimestamp;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public void setLastModifiedInfo(User user) {
    lastModifiedTimestamp = System.currentTimeMillis();
    lastModifiedUser = user;
  }

  @Override
  public String toString() {
    return "Task{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", originatorUser=" + originatorUser +
            ", lastModifiedUser=" + lastModifiedUser +
            ", lastModifiedTimestamp=" + lastModifiedTimestamp +
            ", isPublic=" + isPublic +
            ", isCompleted=" + isCompleted +
            '}';
  }
}