package com.marvel.example.model;

import lombok.Data;

@Data
public class Characters {
	private int id;
	private String name;
	private String description;
	private Thumbnail thumbnail;
}