package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class BaseTaskDto {

  public int id;
  public String title;
  public String description;
  public int originatorUserId;

  public int lastModifiedUserId;

  public boolean isPublic;

  public boolean isCompleted;

  public Timestamp timestamp;

  public LocalDateTime lastModifiedTime;

  public void mapTo(Task task) {
    task.setId(id);
    task.setTitle(title);
    task.setDescription(description);
    task.setOriginatorUserId(originatorUserId);
   /* task.setLastModifiedUserId(lastModifiedUserId);
    task.setIsPublic(isPublic);
    task.setIsCompleted(isCompleted);
    task.setTimestamp(timestamp);*/
  }
}
