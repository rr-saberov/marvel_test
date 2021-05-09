package com.marvel.example.api.response;

import lombok.Data;

import java.util.List;

@Data
public class ComicsResponse {
    private long count;
    private List<SingleComicsResponse> comics;
}
