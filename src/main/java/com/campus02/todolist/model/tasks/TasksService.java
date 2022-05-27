package com.campus02.todolist.model.tasks;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Task createTask(NewTaskDto newTask) {
        Task task = new Task();

        newTask.mapTo(task);

       /* ConstraintViolations violations = Task.validator.validate(task);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);*/

        this.tasksRepository.save(task);

        return task;
    }


}
