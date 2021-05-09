package com.marvel.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Urls {

    @Id
    @Column(nullable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String url;

    @OneToOne(mappedBy = "comicsUrls")
    private Comics comics;

    public Urls(String type, String url) {
        this.type = type;
        this.url = url;
    }
}
