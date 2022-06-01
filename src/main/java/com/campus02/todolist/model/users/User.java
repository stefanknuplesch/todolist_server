package com.campus02.todolist.model.users;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.CharSequenceConstraint;
import am.ik.yavi.core.Validator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonValue
  private Integer id;
  @Column(nullable = false, length = 128)
  private String email;
  @Column(nullable = false, length = 64)
  private String password;
  @Column(nullable = false, length = 128)
  private String name;

  public static final Validator<User> validator = ValidatorBuilder.<User>of()
          .constraint(User::getName, "name", CharSequenceConstraint::notBlank)
          .constraint(User::getEmail, "email", CharSequenceConstraint::email)
          .constraint(User::getPassword, "password", u -> u.greaterThanOrEqual(8)
                  .password(p -> p.alphabets().numbers().build()))
          .build();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
