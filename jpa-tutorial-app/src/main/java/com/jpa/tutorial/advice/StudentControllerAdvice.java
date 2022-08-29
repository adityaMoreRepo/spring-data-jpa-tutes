package com.jpa.tutorial.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class StudentControllerAdvice {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String > studentNotFoundHandler(){
        return new ResponseEntity<>("No Such Subject Exists", HttpStatus.NOT_FOUND  );
    }

}
