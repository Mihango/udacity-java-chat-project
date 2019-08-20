package com.udacity.course3.reviews.config;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ValidationException.class,
            UnrecognizedPropertyException.class})
    ResponseEntity<Error> handleValidationErrors(Exception e) {
        return new ResponseEntity<>(new Error("valid properties required"), HttpStatus.BAD_REQUEST);
    }
}
