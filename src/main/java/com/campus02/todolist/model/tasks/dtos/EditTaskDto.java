package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

import java.util.Date;

public class EditTaskDto extends BaseTaskDto{


    public int originatorUserId;
    public Date lastModifiedTimestamp;
    public int lastModifiedUserId;
}
