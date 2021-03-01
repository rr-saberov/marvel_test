package com.marvel.example.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CharactersGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CharactersIncorrectData> handleException (
            NoSuchCharactersException exception) {
        CharactersIncorrectData incorrectData = new CharactersIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CharactersIncorrectData> handleException (
            Exception exception) {
        CharactersIncorrectData incorrectData = new CharactersIncorrectData();
        incorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }
}
