package com.campus02.todolist.model.tasks;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {


    @Override
    Optional<Task> findById(Integer integer);


    @Override
    Iterable<Task> findAll();

    @Override
    void deleteById(Integer integer);


}