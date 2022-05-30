package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;
import com.campus02.todolist.model.users.dtos.UserInfoDto;

import java.util.Date;

public class TaskDto extends BaseTaskDto {

  public Integer id;
  public UserInfoDto originatorUser;
  public Long lastModifiedTimestamp;
  public UserInfoDto lastModifiedUser;

  public static TaskDto from(Task task) {
    TaskDto result = new TaskDto();
    result.id = task.getId();
    result.title = task.getTitle();
    result.description = task.getDescription();
    result.isPublic = task.getIsPublic();
    result.lastModifiedTimestamp = task.getLastModifiedTimestamp();
    result.isCompleted = task.getIsCompleted();
    var ou = task.getOriginatorUser();
    result.originatorUser = ou != null ? UserInfoDto.from(ou) : null;
    var lmu = task.getLastModifiedUser();
    result.lastModifiedUser = lmu != null ? UserInfoDto.from(lmu) : null;

    return result;
  }
}
