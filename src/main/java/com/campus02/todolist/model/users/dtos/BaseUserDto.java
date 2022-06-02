package com.campus02.todolist.model.users.dtos;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
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

  public String getEmail() { return email; }

  public String getPassword() { return password; }

  public String getName() { return name; }


  public static final Validator<BaseUserDto> validator = ValidatorBuilder.<BaseUserDto>of()
          .constraint(BaseUserDto::getName, "Benutzername",
                  c -> c.notBlank().message("{0} ist erforderlich!")
                          .lessThanOrEqual(100).message("{0} darf maximal 100 Zeichen lang sein!"))

          .constraint(BaseUserDto::getEmail, "E-Mail Adresse",
                  c -> c.notBlank().message("{0} ist erforderlich!")
                          .lessThanOrEqual(100).message("0} darf maximal 100 Zeichen lang sein!")
                          .email().message("Ungültige {0}!"))

          .constraint(BaseUserDto::getPassword, "Passwort",
                  c -> c.notBlank().message("{0} ist erforderlich!")
                          .greaterThanOrEqual(8).message("{0} muss mindestens 8 Zeichen lang sein!"))
          .failFast(true)
          .build();
}
