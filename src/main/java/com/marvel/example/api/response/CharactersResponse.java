package com.marvel.example.api.response;

import lombok.Data;

import java.util.List;

@Data
public class CharactersResponse {
    private long count;
    private List<SingleCharacterResponse> characters;
}
