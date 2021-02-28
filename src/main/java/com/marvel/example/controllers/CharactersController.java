package com.marvel.example.controllers;

import com.marvel.example.model.Characters;
import com.marvel.example.model.Comics;
import com.marvel.example.service.CharactersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("characters data")
@RestController
@RequestMapping("/v1/public/characters")
public class CharactersController {

	private Logger logger = Logger.getLogger(CharactersController.class);
	private CharactersService charactersService;

	@Autowired
	public CharactersController(CharactersService charactersService) {
		this.charactersService = charactersService;
	}

	@GetMapping
	@ApiOperation("method to get all characters")
	public List<Characters> characters() {
		return charactersService.getCharacters();
	}

	@GetMapping("/{characterId}")
	@ApiOperation("method to get character by id")
	public Characters characterById(@PathVariable("characterId") int id) {
		return charactersService.getCharacterById(id);
	}

	@GetMapping("/{characterId}/comics")
	@ApiOperation("method to get comics with character")
	public List<Comics> comicsWithCharacters(@PathVariable("characterId") int id) {
		return charactersService.getComicsWithChar(id);
	}

	@PostMapping("/save")
	@ApiOperation("method to save character")
	public Characters saveCharacter(@RequestBody Characters characters) {
		return charactersService.saveCharacter(characters);
	}

	@PutMapping("/update")
	@ApiOperation("method to update character")
	public Characters updateCharacter(@RequestBody Characters characters) {
		return charactersService.saveCharacter(characters);
	}

	@PostMapping("/delete")
	@ApiOperation("method to delete character")
	public void deleteCharacter(Integer id) {
		charactersService.deleteCharacter(id);
	}
}
