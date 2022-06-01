package com.campus02.todolist.model.tasks.dtos;

import com.campus02.todolist.model.tasks.repository.TaskFetchInfo;

import java.util.UUID;

public class FetchTaskDto {
    public UUID id;
    public Long lastModifiedTimestamp;

    public static FetchTaskDto from(TaskFetchInfo tfi) {
        FetchTaskDto result = new FetchTaskDto();
        result.id = tfi.getId();
        result.lastModifiedTimestamp = tfi.getLastModifiedTimestamp();
        return result;
    }
}
