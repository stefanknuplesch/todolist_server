package com.campus02.todolist.model.users.dtos;

import com.campus02.todolist.model.users.User;

public class UserDto extends BaseUserDto {

  public Integer id;

  public static UserDto from(User user) {
    UserDto result = new UserDto();

    result.id = user.getId();
    result.email = user.getEmail();
    result.password = user.getPassword();
    result.name = user.getName();

    return result;
  }
}
