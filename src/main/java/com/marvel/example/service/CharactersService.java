package com.marvel.example.service;

import com.marvel.example.api.response.CharactersResponse;
import com.marvel.example.api.response.SingleCharacterResponse;
import com.marvel.example.api.response.ThumbnailResponse;
import com.marvel.example.entity.Characters;
import com.marvel.example.entity.Thumbnail;
import com.marvel.example.repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CharactersService {

    private final CharactersRepository charactersRepository;

    @Autowired
    public CharactersService(CharactersRepository charactersRepository) {
        this.charactersRepository = charactersRepository;
    }

    public CharactersResponse getCharactersOrderById(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        CharactersResponse charactersResponse = new CharactersResponse();
        Page<Characters> charactersPage =
                charactersRepository.getAllCharacters(nextPage);
        charactersResponse.setCount(charactersPage.getTotalElements());
        charactersResponse.setCharacters(charactersPage.get().map(this::convertToResponse).collect(Collectors.toList()));
        return charactersResponse;
    }

    public CharactersResponse getCharactersByQuery(String query, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        CharactersResponse charactersResponse = new CharactersResponse();
        Page<Characters> charactersPage =
                charactersRepository.searchCharacters(query, nextPage);
        charactersResponse.setCount(charactersPage.getTotalElements());
        charactersResponse.setCharacters(charactersPage.get().map(this::convertToResponse).collect(Collectors.toList()));
        return charactersResponse;
    }

    public SingleCharacterResponse getCharacterById(Integer id) {
        return convertToResponse(charactersRepository.getCharactersById(id));
    }

    public void saveCharacters(String name, String description, String path, String extension) {
        charactersRepository.save(convertToCharacters(name, description, path, extension));
    }

    public void saveCharacters(int id, String name, String description, String path, String extension) {
        charactersRepository.save(convertToCharacters(id, name, description, path, extension));
    }

    public SingleCharacterResponse deleteCharacters(int id) {
        SingleCharacterResponse singleCharacterResponse = convertToResponse(charactersRepository.getCharactersById(id));
        charactersRepository.deleteById(id);
        return singleCharacterResponse;
    }

    private SingleCharacterResponse convertToResponse(Characters characters) {
        SingleCharacterResponse singleCharacterResponse = new SingleCharacterResponse();
        ThumbnailResponse thumbnailResponse = new ThumbnailResponse();
        thumbnailResponse.setExtension(characters.getCharactersThumbnail().getExtension());
        thumbnailResponse.setPath(characters.getCharactersThumbnail().getPath());
        singleCharacterResponse.setId(characters.getId());
        singleCharacterResponse.setDescription(characters.getDescription());
        singleCharacterResponse.setName(characters.getName());
        singleCharacterResponse.setThumbnail(thumbnailResponse);
        return singleCharacterResponse;
    }

    private Characters convertToCharacters(String name, String description, String path, String extension) {
        Characters characters = new Characters();
        characters.setName(name);
        characters.setDescription(description);
        characters.setCharactersThumbnail(new Thumbnail(path, extension));
        // setComicsList??
        return characters;
    }

    private Characters convertToCharacters(int id, String name, String description, String path, String extension) {
        Characters characters = new Characters();
        characters.setId(id);
        characters.setName(name);
        characters.setDescription(description);
        characters.setCharactersThumbnail(new Thumbnail(path, extension));
        return characters;
    }
}
