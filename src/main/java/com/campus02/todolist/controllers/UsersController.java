package com.campus02.todolist.controllers;

import com.campus02.todolist.model.users.UsersService;
import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserCredentialsDto;
import com.campus02.todolist.model.users.dtos.UserInfoDto;
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
  @ResponseStatus(HttpStatus.CREATED)
  public UserInfoDto registerUser(@RequestBody NewUserDto user) {
    return UserInfoDto.from(this.usersService.registerUser(user));
  }

  @PostMapping("/login")
  public UserInfoDto loginUser(@RequestBody UserCredentialsDto credentials) {
    var user = this.usersService.loginUser(credentials);
    if (user.isPresent()) {
      return UserInfoDto.from(user.get());
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login fehlgeschlagen! Ungültige Email/Passwort-Kombination!");
    }
  }
}
