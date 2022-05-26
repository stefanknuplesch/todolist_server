package com.campus02.todolist.model;

import am.ik.yavi.core.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;

public record ValidationError(String property, String message) {
    public static ValidationError from(ConstraintViolation violation) {
       return new ValidationError(violation.name(), violation.message());
    }

    public static ValidationError from(String message) {
        return new ValidationError("", message);
    }

    public List<ValidationError> toList() {
        List<ValidationError> result = new ArrayList<>();
        result.add(this);
        return result;
    }
}
