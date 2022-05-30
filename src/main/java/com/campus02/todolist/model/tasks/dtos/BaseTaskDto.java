package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;


public abstract class BaseTaskDto {

  public String title;
  public String description;
  public boolean isPublic;
  public boolean isCompleted;

  public void mapTo(Task task) {
    task.setTitle(title);
    task.setDescription(description);
    task.setIsPublic(isPublic);
    task.setIsCompleted(isCompleted);
  }
}
