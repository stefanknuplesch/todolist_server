package com.campus02.todolist.model.users.dtos;

import com.campus02.todolist.model.users.User;

public class LoginUserDto {
  public int id;
  public String email;
  public String name;

  public static LoginUserDto from(User user) {
    LoginUserDto result = new LoginUserDto();

    result.id = user.getId();
    result.email = user.getEmail();
    result.name = user.getName();

    return result;
  }
}
