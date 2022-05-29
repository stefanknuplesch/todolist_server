package com.campus02.todolist.model.tasks;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.tasks.dtos.EditTaskDto;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    public Task editTask(EditTaskDto editTaskDto, int id){
        Task task = this.tasksRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("The requested task does not exist!");
        editTaskDto.mapTo(task);

       /* ConstraintViolations violations = Task.validator.validate(task);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);*/

        //last modified user id and timestamp setzen

        this.tasksRepository.save(task);
        return task;
    }

    public Task deleteTask(int id){
        Task task = this.tasksRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("The requested task does not exist!");
        this.tasksRepository.deleteById(id);
        return task;
    }

    public Task getTask(int id){
        Task task = this.tasksRepository.findById(id).orElse(null);
        if (task == null) throw new EntityNotFoundException("The requested task does not exist!");
        return task;
    }

    public List<Task> getAllTasks(int originatorUserId){
        return (List<Task>) this.tasksRepository.findByOriginatorUserIdOrIsPublicIsTrue(originatorUserId);
    }


}
