package com.marvel.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Comics {

    @Id
    @Column(nullable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "characters_comics",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "comics_id"))
    private List<Characters> charactersList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "urls_id", referencedColumnName = "id")
    private Urls comicsUrls;

}
