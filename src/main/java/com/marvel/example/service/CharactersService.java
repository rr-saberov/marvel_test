package com.marvel.example.service;

import com.marvel.example.model.Characters;
import com.marvel.example.model.Comics;
import com.marvel.example.repository.CharactersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersService {

    private final CharactersRepository charactersRepository;

    @Autowired
    public CharactersService(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    public List<Characters> getCharacters() {
        return charactersRepository.retrieveAll();
    }

    public Characters getCharacterById(Integer id) {
        return charactersRepository.searchCharacterById(id);
    }

    public List<Comics> getComicsWithChar(Integer id) {
        return charactersRepository.searchComicsWithCharacter(id);
    }

    public Characters saveCharacter(Characters characters) {
        return charactersRepository.saveCharacter(characters);
    }

    public void deleteCharacter(Integer id) {
        charactersRepository.removeCharacterById(id);
    }
}
