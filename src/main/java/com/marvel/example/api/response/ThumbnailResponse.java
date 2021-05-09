package com.marvel.example.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThumbnailResponse {
    private String path;
    private String extension;
}
