package com.marvel.example.service;

import com.marvel.example.model.Characters;
import com.marvel.example.model.Comics;
import com.marvel.example.repository.ComicsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicsService {

    private final Logger logger = Logger.getLogger(CharactersService.class);
    private final ComicsRepository comicsRepository;

    @Autowired
    public ComicsService(ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    public List<Comics> getAllComics() {
        return comicsRepository.retrieveAll();
    }

    public Comics getComicsById(Integer id) {
        return comicsRepository.searchComicsById(id);
    }

    public List<Characters> getComicsWithCharacter(Integer id) {
        return comicsRepository.searchCharactersInComics(id);
    }

    public Comics saveComics(Comics comics) {
        return comicsRepository.addComics(comics);
    }

    public void deleteComics(Integer id) {
        comicsRepository.removeComicsById(id);
    }
}

