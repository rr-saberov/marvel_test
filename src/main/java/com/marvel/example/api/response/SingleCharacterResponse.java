package com.marvel.example.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SingleCharacterResponse {
    private int id;
    private String name;
    private String description;
    private ThumbnailResponse thumbnail;

    public SingleCharacterResponse(String name, String description, ThumbnailResponse thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
