package com.campus02.todolist.model.users;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.users.dtos.EditUserDto;
import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAllUsers() {
        return StreamSupport
                .stream(this.usersRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User getUserById(int userId) {
        User user = this.usersRepository.findById(userId).orElse(null);

        if (user == null)
            throw new EntityNotFoundException("The requested user does not exist.");

        return user;
    }

    public User registerUser(NewUserDto newUser) {
        User user = new User();

        newUser.mapTo(user);

        ConstraintViolations violations = User.validator.validate(user);
        if (!violations.isValid())
            throw new BusinessLogicViolationException(violations);

        this.usersRepository.save(user);

        return user;
    }

    public User getUserByMailAndPassword(UserDto userToLogin) {
        User user = new User();

        userToLogin.mapTo(user);

        /*
        TODO:
        Implement login check and return user
        IF not found ==> Login failed! ==> RETURN NULL
         */

        return user;
    }
}
