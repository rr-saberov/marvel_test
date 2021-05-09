package com.marvel.example.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CharacterNotFoundException extends Exception {

    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
