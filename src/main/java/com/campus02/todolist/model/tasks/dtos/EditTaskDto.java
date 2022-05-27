package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;

public class EditTaskDto extends BaseTaskDto{

    public int id;


    public static TaskDto from(Task task){

       TaskDto result = new TaskDto();


      return result;
    }
}
