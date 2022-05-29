package com.campus02.todolist.model.tasks;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false, length = 32)
  private String title;
  @Column(nullable = false, length = 256)
  private String description;
  @Column(nullable = false)
  private int originatorUserId;

  @Column
  private int lastModifiedUserId;

  @Column
  private Date lastModifiedTimestamp;

  @Column
  private boolean isPublic;

  @Column
  private boolean isCompleted;



  public static final Validator<Task> validator = ValidatorBuilder.<Task>of()
          .constraint(Task::getTitle, "name", CharSequenceConstraint::notBlank)
          .constraint(Task::getDescription, "description", CharSequenceConstraint::notBlank)
          .build();


  public List<Task> getTasks(){

    List<Task> tasklist = null;



    return tasklist;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public int getOriginatorUserId() {
    return originatorUserId;
  }

  public void setOriginatorUserId(int originatorUserId) {
    this.originatorUserId = originatorUserId;
  }

  public int getLastModifiedUserId() {
    return lastModifiedUserId;
  }

  public void setLastModifiedUserId(int lastModifiedUserId) {
    this.lastModifiedUserId = lastModifiedUserId;
  }

  public Date getLastModifiedTimestamp() {
    return lastModifiedTimestamp;
  }

  public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
    this.lastModifiedTimestamp = lastModifiedTimestamp;
  }

  public boolean getIsPublic() {
    return isPublic;
  }

  public void setIsPublic(boolean ispublic) {
    this.isPublic = ispublic;
  }


  public boolean getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(boolean completedBoolean) {
    this.isCompleted = completedBoolean;
  }
}


