package com.campus02.todolist.model.tasks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {
    List<Task> findByOriginatorUserIdOrIsPublicIsTrue(int originatorUserId);
}