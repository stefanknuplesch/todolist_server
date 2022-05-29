package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


public class TaskDto extends BaseTaskDto {

  public int id;
  public int originatorUserId;
  public Date lastModifiedTimestamp;

  public int lastModifiedUserId;



  public static TaskDto from(Task task) {
    TaskDto result = new TaskDto();
    result.id = task.getId();
    result.title = task.getTitle();
    result.description = task.getDescription();
    result.originatorUserId = task.getOriginatorUserId();
    result.isPublic = task.getIsPublic();
    result.lastModifiedTimestamp = task.getLastModifiedTimestamp();
    result.lastModifiedUserId = task.getLastModifiedUserId();
    result.isCompleted = task.getIsCompleted();


    return result;
  }
}
