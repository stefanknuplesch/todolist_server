package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.Task;
import com.campus02.todolist.model.tasks.repository.TaskFetchInfo;

import java.util.List;
import java.util.UUID;

public class SyncRequestDto {
    public List<TaskDto> toPersist;
    public List<UUID> toRetrieve;
    public List<UUID> toDelete;


}

