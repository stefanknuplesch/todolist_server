package com.campus02.todolist.model.users;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserCredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User registerUser(NewUserDto newUser) {
        User user = new User();

        newUser.mapTo(user);

        ConstraintViolations violations = User.validator.validate(user);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);

        if (this.usersRepository.existsByEmail(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "An user with the given email already exists.");

        this.usersRepository.save(user);

        return user;
    }

    public Optional<User> loginUser(UserCredentialsDto credentials) {
        return this.usersRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
    }
}
