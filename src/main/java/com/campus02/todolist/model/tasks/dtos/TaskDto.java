package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;
import com.campus02.todolist.model.users.dtos.UserInfoDto;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

public class TaskDto {

  public UUID id;
  public String title;
  public String description;
  public boolean isPublic;
  public boolean isCompleted;
  public Integer originatorUserId;
  public Long lastModifiedTimestamp;

  public static TaskDto from(Task task) {
    TaskDto result = new TaskDto();
    result.id = task.getId();
    result.title = task.getTitle();
    result.description = task.getDescription();
    result.isPublic = task.isPublic();
    result.lastModifiedTimestamp = task.getLastModifiedTimestamp();
    result.isCompleted = task.isCompleted();
    result.originatorUserId = task.getOriginatorUser().getId();

    return result;
  }
  public static Task to(TaskDto dto) {
    Task result = new Task();
    result.setId(dto.id);
    result.setTitle(dto.title);
    result.setDescription(dto.description);
    result.setPublic(dto.isPublic);
    result.setCompleted(dto.isCompleted);
    result.setLastModifiedTimestamp(dto.lastModifiedTimestamp);
    result.setOriginatorUserId(dto.originatorUserId);

    return result;
  }
}
