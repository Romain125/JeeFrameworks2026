package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<Void> handleException(){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
