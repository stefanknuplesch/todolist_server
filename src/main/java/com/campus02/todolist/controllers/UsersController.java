package com.campus02.todolist.controllers;

import com.campus02.todolist.model.users.User;
import com.campus02.todolist.model.users.UsersService;

import com.campus02.todolist.model.users.dtos.LoginUserDto;
import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @PostMapping()
  public UserDto registerUser(@RequestBody NewUserDto user) {
    return UserDto.from(this.usersService.registerUser(user));
  }

  @PostMapping("/login")
  public UserDto loginUser(@RequestBody LoginUserDto credentials) {
    User user = this.usersService.loginUser(credentials);
    if (user != null) {
      return UserDto.from(this.usersService.loginUser(credentials));
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed with given credentials.");
    }
  }
}
