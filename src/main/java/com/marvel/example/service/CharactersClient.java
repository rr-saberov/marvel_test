package com.marvel.example.service;

import com.marvel.example.model.CharactersData;

import com.marvel.example.model.ComicsData;
import com.marvel.example.model.ComicsWithChars;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CharactersClient {
	public Flux<CharactersData> getCharacters();
	public Mono<CharactersData> getCharactersById(int id);
	public Flux<ComicsData> getComicsWithCharacters(int id);
}
