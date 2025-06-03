package com.org.test.validatenametest.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Hidden
@RestControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler({NotFoundRequestContentException.class,
            NotFoundSettingException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleValidationException(RuntimeException  exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({JsonProcessingException.class,
            IOException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleProcessException(RuntimeException  exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
