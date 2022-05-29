package com.campus02.todolist.controllers;

import com.campus02.todolist.model.tasks.TasksService;
import com.campus02.todolist.model.tasks.dtos.EditTaskDto;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
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

  @PostMapping()
  public TaskDto createTask (@RequestBody NewTaskDto task) {
    return TaskDto.from(this.taskService.createTask(task));
  }

@DeleteMapping("/{id}")
  public TaskDto deleteTask (@PathVariable int id){
    return TaskDto.from(this.taskService.deleteTask(id));
 }

 @GetMapping("/{id}")
 public TaskDto getTask (@PathVariable int id){
      return TaskDto.from(this.taskService.getTask(id));
 }

 @GetMapping()
 public List<TaskDto> getUsersTasks (@RequestParam int userId){
     return taskService.getAllTasks(userId).stream().map(TaskDto::from).collect(Collectors.toList());
 }


 @PutMapping("/{id}")
    public TaskDto editTask (@RequestBody EditTaskDto task, @PathVariable int id){
      return TaskDto.from(this.taskService.editTask(task, id));
 }

}
