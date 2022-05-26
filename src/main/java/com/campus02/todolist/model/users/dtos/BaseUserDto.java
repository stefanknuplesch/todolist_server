package com.campus02.todolist.model.users.dtos;

import com.campus02.todolist.model.users.User;

public abstract class BaseUserDto {
  public String email;
  public String password;
  public String name;

  public void mapTo(User user) {
    user.setEmail(email);
    user.setPassword(password);
    user.setName(name);
  }
}
