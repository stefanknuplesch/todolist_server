package com.campus02.todolist.model;

import am.ik.yavi.core.ConstraintViolations;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessLogicViolationException extends RuntimeException {

    private final List<ValidationError> errors;

    public BusinessLogicViolationException(ConstraintViolations violations) {
        super();
        errors = violations.violations().stream().map(ValidationError::from).collect(Collectors.toList());
    }

    public List<ValidationError> getErrors() { return errors; }
}