package com.campus02.todolist.model.users.dtos;

import com.campus02.todolist.model.users.User;

public class UserInfoDto {
  public Integer id;
  public String email;
  public String name;

  public static UserInfoDto from(User user) {
    UserInfoDto result = new UserInfoDto();

    result.id = user.getId();
    result.email = user.getEmail();
    result.name = user.getName();

    return result;
  }
}
