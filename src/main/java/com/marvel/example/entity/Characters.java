package com.marvel.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Characters {

	@Id
	@Column(nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "characters_comics",
            joinColumns = @JoinColumn(name = "comics_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Comics> comicsList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "thumbnail_id", referencedColumnName = "id")
    private Thumbnail charactersThumbnail;

}