package com.campus02.todolist.controllers;

import com.campus02.todolist.model.BusinessLogicViolationException;
import com.campus02.todolist.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@ControllerAdvice
public class BusinessLogicServicesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessLogicViolationException.class)
    protected ResponseEntity<List<ValidationError>> handleBusinessLogicViolation(BusinessLogicViolationException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<List<ValidationError>> handleEntityNotFound(EntityNotFoundException exception) {
        return new ResponseEntity<>(ValidationError.from(exception.getMessage()).toList(), HttpStatus.NOT_FOUND);
    }
}