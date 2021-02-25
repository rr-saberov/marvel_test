package com.marvel.example.service;

import com.marvel.example.model.CharactersData;
import com.marvel.example.model.ComicsData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ComicsClient {
    public Flux<ComicsData> getComics();
    public Mono<ComicsData> getComicsById(int id);
    public Flux<CharactersData> getCharsInComics(int id);
}
