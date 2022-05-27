package com.campus02.todolist.controllers;

import com.campus02.todolist.model.tasks.TasksService;
import com.campus02.todolist.model.tasks.dtos.NewTaskDto;
import com.campus02.todolist.model.tasks.dtos.TaskDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
