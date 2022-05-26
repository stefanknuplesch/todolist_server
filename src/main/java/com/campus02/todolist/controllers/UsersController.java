package com.campus02.todolist.controllers;

import com.campus02.todolist.model.users.User;
import com.campus02.todolist.model.users.UsersService;

import java.util.List;
import java.util.stream.Collectors;

import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  // WIRD DANN ENTFERNT - nur zum Testen!!!
  @GetMapping()
  public List<UserDto> getAllUsers() {
    return usersService.getAllUsers().stream().map(UserDto::from).collect(Collectors.toList());
  }

  // WIRD DANN ENTFERNT - nur zum Testen!!!
  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable int id) {
    return UserDto.from(this.usersService.getUserById(id));
  }

  @PostMapping()
  public UserDto registerUser(@RequestBody NewUserDto user) {
    return UserDto.from(this.usersService.registerUser(user));

  }
}
