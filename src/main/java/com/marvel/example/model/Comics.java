package com.marvel.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comics {

    @Id
    @Column(name = "comics_id", nullable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;

    @Column(name = "character_id")
    private long characterId;

    @ManyToMany
    @JoinTable(name = "characters",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Characters> characters;
}
