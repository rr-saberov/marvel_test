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
public class Characters {

	@Id
	@Column(name = "character_id", nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private String path;
	private String extension;

	@Column(name = "comics_id")
	private long comicsId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Comics",
			joinColumns = @JoinColumn(name = "character_id"),
			inverseJoinColumns = @JoinColumn(name = "comics_id"))
	private List<Comics> comics;
}