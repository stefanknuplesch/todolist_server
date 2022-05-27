package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class TaskDto extends BaseTaskDto {

  public int id;

  public static TaskDto from(Task task) {
    TaskDto result = new TaskDto();
    result.id = task.getId();
    result.title = task.getTitle();
    result.description = task.getDescription();
    result.originatorUserId = task.getOriginatorUserId();
   /* result.isPublic = task.getIsPublic();
    result.timestamp = task.getTimestamp();*/



    return result;
  }
}
