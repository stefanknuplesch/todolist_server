package com.campus02.todolist.controllers;

import com.campus02.todolist.model.tasks.TasksService;
import com.campus02.todolist.model.tasks.dtos.EditTaskDto;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskOverviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService taskService;

    public TasksController(TasksService taskService) {
        this.taskService = taskService;
    }

    // GET TASKS
    @GetMapping("/{id}")
    public TaskDto getTask (@PathVariable Integer id, @RequestHeader Integer userId){
        return TaskDto.from(this.taskService.getTask(id));
    }

    @GetMapping()
    public List<TaskOverviewDto> getUsersTasks (@RequestHeader Integer userId){
        return this.taskService.getAllTasks(userId).stream().map(TaskOverviewDto::from).collect(Collectors.toList());
    }

    // INSERT
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask (@RequestBody NewTaskDto task, @RequestHeader Integer userId) {
        return TaskDto.from(this.taskService.createTask(task, userId));
    }

    // UPDATE
    @PutMapping("/{id}")
    public TaskDto editTask (@RequestBody EditTaskDto task, @PathVariable Integer id, @RequestHeader Integer userId){
        return TaskDto.from(this.taskService.editTask(task, id, userId));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public TaskDto deleteTask (@PathVariable Integer id, @RequestHeader Integer userId){
        return TaskDto.from(this.taskService.deleteTask(id));
    }

}
