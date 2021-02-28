package com.marvel.example.model;

import com.sun.istack.NotNull;
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
	@Column(nullable = false, columnDefinition = "serial")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "thumbnail_id")
	private Thumbnail thumbnail;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "characters_comics",
			joinColumns = @JoinColumn(name = "character_id"),
			inverseJoinColumns = @JoinColumn(name = "comics_id"))
	private List<Comics> comics;
}