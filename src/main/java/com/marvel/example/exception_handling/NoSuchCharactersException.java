package com.marvel.example.exception_handling;

public class NoSuchCharactersException extends RuntimeException {

    public NoSuchCharactersException(String message) {
        super(message);
    }
}
