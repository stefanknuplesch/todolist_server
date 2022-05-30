package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

import java.util.Date;

public class TaskOverviewDto extends BaseTaskDto {

  public int id;
  public long lastModifiedTimestamp;
  public int lastModifiedUserId;

  public static TaskOverviewDto from(Task task) {
    TaskOverviewDto result = new TaskOverviewDto();
    result.id = task.getId();
    result.title = task.getTitle();
    result.isPublic = task.getIsPublic();
    var lmu = task.getLastModifiedUser();
    result.lastModifiedUserId = lmu != null ? lmu.getId() : 0;
    result.lastModifiedTimestamp = task.getLastModifiedTimestamp();
    result.isCompleted = task.getIsCompleted();

    return result;
  }
}
