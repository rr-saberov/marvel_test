package com.marvel.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Thumbnail {

	@Id
    @Column(nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String path;
	private String extension;

	@OneToOne(mappedBy = "charactersThumbnail")
    private Characters characters;

    public Thumbnail(String path, String extension) {
        this.path = path;
        this.extension = extension;
    }
}
