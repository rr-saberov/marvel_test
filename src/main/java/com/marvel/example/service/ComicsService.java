package com.marvel.example.service;

import com.marvel.example.api.response.CharactersResponse;
import com.marvel.example.api.response.ComicsResponse;
import com.marvel.example.api.response.SingleComicsResponse;
import com.marvel.example.api.response.UrlsResponse;
import com.marvel.example.entity.Comics;
import com.marvel.example.entity.Urls;
import com.marvel.example.repository.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComicsService {

    private final ComicsRepository comicsRepository;

    @Autowired
    public ComicsService(ComicsRepository comicsRepository) {
        this.comicsRepository = comicsRepository;
    }

    public ComicsResponse getComicsOrderById(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        ComicsResponse comicsResponse = new ComicsResponse();
        Page<Comics> comicsPage =
                comicsRepository.getAllComics(nextPage);
        comicsResponse.setCount(comicsPage.getTotalElements());
        comicsResponse.setComics(comicsPage.get().map(this::convertToResponse).collect(Collectors.toList()));
        return comicsResponse;
    }

    public ComicsResponse getComicsByQuery(String query, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        ComicsResponse comicsResponse = new ComicsResponse();
        Page<Comics> comicsPage =
                comicsRepository.searchComics(query, nextPage);
        comicsResponse.setCount(comicsPage.getTotalElements());
        comicsResponse.setComics(comicsPage.get().map(this::convertToResponse).collect(Collectors.toList()));
        return comicsResponse;
    }

    public SingleComicsResponse getComicsById(Integer id) {
        return convertToResponse(comicsRepository.getComicsById(id));
    }

    //TODO !!!
    public CharactersResponse getCharactersInComics(Integer offset, Integer limit, Integer id) {
        return null;
    }

    public void saveComics(String title, String isbn, String type, String url) {
        comicsRepository.save(convertToComics(title, isbn, type, url));
    }

    public void updateComics(Integer id, String title, String isbn, String type, String url) {
        comicsRepository.save(convertToComics(id, title, isbn, type, url));
    }

    public SingleComicsResponse deleteComics(Integer id) {
        SingleComicsResponse singleComicsResponse = convertToResponse(comicsRepository.getComicsById(id));
        comicsRepository.deleteById(id);
        return singleComicsResponse;
    }

    private Comics convertToComics(String title, String isbn, String type, String url) {
        Comics comics = new Comics();
        comics.setTitle(title);
        comics.setIsbn(isbn);
        comics.setComicsUrls(new Urls(type, url));
        return comics;
    }

    private Comics convertToComics(Integer id, String title, String isbn, String type, String url) {
        Comics comics = new Comics();
        comics.setId(id);
        comics.setTitle(title);
        comics.setIsbn(isbn);
        comics.setComicsUrls(new Urls(type, url));
        return comics;
    }

    private SingleComicsResponse convertToResponse(Comics comics) {
        SingleComicsResponse singleComicsResponse = new SingleComicsResponse();
        UrlsResponse urlsResponse = new UrlsResponse();
        urlsResponse.setType(comics.getComicsUrls().getType());
        urlsResponse.setUrl(comics.getComicsUrls().getUrl());
        singleComicsResponse.setId(comics.getId());
        singleComicsResponse.setIsbn(comics.getIsbn());
        singleComicsResponse.setTitle(comics.getTitle());
        singleComicsResponse.setUrls(urlsResponse);
        return singleComicsResponse;
    }
}

