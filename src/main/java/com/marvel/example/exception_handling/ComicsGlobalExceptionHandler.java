package com.marvel.example.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ComicsGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ComicsIncorrectData> handleException (
            NoSuchCharactersException exception) {
        ComicsIncorrectData incorrectData = new ComicsIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ComicsIncorrectData> handleException (
            Exception exception) {
        ComicsIncorrectData incorrectData = new ComicsIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}
