package com.campus02.todolist.model.tasks.repository;

import java.util.UUID;

public interface TaskFetchInfo {
    UUID getId();
    Long getLastModifiedTimestamp();
}
