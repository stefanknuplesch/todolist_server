package com.campus02.todolist.controllers;

import com.campus02.todolist.model.tasks.TasksService;
import com.campus02.todolist.model.tasks.dtos.EditTaskDto;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
import org.springframework.web.bind.annotation.*;

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

/* @DeleteMapping("/{id}")
  public TaskDto deleteTask (@PathVariable int id){

    return TaskDto.from(this.taskService.deleteTask(id));
 }*/

 @PutMapping("/{id}")
    public TaskDto editTask (@RequestBody EditTaskDto task, @PathVariable int id){
      return TaskDto.from(this.taskService.editTask(task, id));
 }

  /*@PostMapping("/login")
  public UserDto loginUser(@RequestBody LoginUserDto credentials) {
    User user = this.usersService.loginUser(credentials);
    if (user != null) {
      return UserDto.from(this.usersService.loginUser(credentials));
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed with given credentials.");
    }
  }*/
}
