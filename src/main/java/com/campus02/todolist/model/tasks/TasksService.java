package com.campus02.todolist.model.tasks;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.tasks.dtos.EditTaskDto;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.users.User;
import com.campus02.todolist.model.users.UsersRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final UsersRepository usersRepository;

    public TasksService(TasksRepository tasksRepository, UsersRepository usersRepository) {
        this.tasksRepository = tasksRepository;
        this.usersRepository = usersRepository;
    }

    public Task getTask(int id){
        return this.tasksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The requested task does not exist!"));
    }

    public List<Task> getAllTasks(int originatorUserId){
        return this.tasksRepository.findByOriginatorUserIdOrIsPublicIsTrue(originatorUserId);
    }

    public Task createTask(NewTaskDto newTask, int userId) {
        User clientUser = this.usersRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("The requested user does not exist!"));

        Task task = new Task();
        newTask.mapTo(task);
        task.setOriginatorUser(clientUser);
        task.setLastModifiedInfo(clientUser);

       /* ConstraintViolations violations = Task.validator.validate(task);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);*/

        //System.out.println(task.toString());
        this.tasksRepository.save(task);
        return task;
    }

    public Task editTask(EditTaskDto editTaskDto, int id, int userId){
        Task task = this.tasksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The requested task does not exist!"));
        User clientUser = this.usersRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("The requested user does not exist!"));

        editTaskDto.mapTo(task);
        task.setLastModifiedInfo(clientUser);

       /* ConstraintViolations violations = Task.validator.validate(task);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);*/

        //last modified user id and timestamp setzen

        this.tasksRepository.save(task);
        return task;
    }

    public Task deleteTask(int id){
        Task task = this.tasksRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("The requested task does not exist!"));
        this.tasksRepository.deleteById(id);
        return task;
    }

}
