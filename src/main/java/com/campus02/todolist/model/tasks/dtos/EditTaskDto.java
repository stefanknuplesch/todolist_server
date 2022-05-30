package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.users.dtos.UserInfoDto;

import java.util.Date;

public class EditTaskDto extends BaseTaskDto{

    public UserInfoDto originatorUser; // oder einfach nur ID ?
    public Long lastModifiedTimestamp;
    public UserInfoDto lastModifiedUser; // oder einfach nur ID ?
}
