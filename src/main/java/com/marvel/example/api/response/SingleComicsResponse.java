package com.marvel.example.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleComicsResponse {
    private int id;
    private String title;
    private String isbn;
    private UrlsResponse urls;

    public SingleComicsResponse(String title, String isbn, UrlsResponse urls) {
        this.title = title;
        this.isbn = isbn;
        this.urls = urls;
    }
}
