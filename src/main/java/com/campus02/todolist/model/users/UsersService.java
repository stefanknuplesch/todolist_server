package com.campus02.todolist.model.users;

import am.ik.yavi.core.ConstraintViolations;
import com.campus02.todolist.model.users.dtos.BaseUserDto;
import com.campus02.todolist.model.users.dtos.NewUserDto;
import com.campus02.todolist.model.users.dtos.UserCredentialsDto;
import com.campus02.todolist.model.users.dtos.UserInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User registerUser(NewUserDto newUser) {
        ConstraintViolations violations = BaseUserDto.validator.validate(newUser);
        if (!violations.isValid())
        {
            var violation = StreamSupport.stream(violations.spliterator(), false).findFirst().orElse(null);
            String msg = violation != null ? String.format(violation.message(), violation.args()) : "Unerwarteter Fehler";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
        }
        User user = new User();
        newUser.mapTo(user);

        if (this.usersRepository.existsByEmail(user.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Es existiert bereits ein Benutzer mit dieser E-Mail Adresse!");

        this.usersRepository.save(user);

        return user;
    }

    public UserInfoDto loginUser(UserCredentialsDto credentials) {
        var user = this.usersRepository.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());
        if (user.isPresent()) {
            return UserInfoDto.from(user.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login fehlgeschlagen! Ungültige Email/Passwort-Kombination!");
        }
    }
}
