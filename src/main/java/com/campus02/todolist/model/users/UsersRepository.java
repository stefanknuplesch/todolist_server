package com.campus02.todolist.model.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}