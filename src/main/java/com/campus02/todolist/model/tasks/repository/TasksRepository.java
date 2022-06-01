package com.campus02.todolist.model.tasks.repository;

import com.campus02.todolist.model.tasks.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TasksRepository extends CrudRepository<Task, UUID> {
    List<TaskFetchInfo> findByOriginatorUser_IdOrIsPublicIsTrue(Integer id);
}

