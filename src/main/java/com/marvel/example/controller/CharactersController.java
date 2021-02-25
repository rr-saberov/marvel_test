package com.marvel.example.controller;

import com.marvel.example.model.CharactersData;
import com.marvel.example.model.ComicsData;
import com.marvel.example.service.CharactersClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CharactersController {

	private CharactersClient charactersService;

	public CharactersController(CharactersClient charactersService) {
		this.charactersService = charactersService;
	}

	@ApiOperation(value = "Get marvel characters", notes = "Provides characters ids")
	@ApiResponse(code = 500, message = "Internal server error")
	@GetMapping("/characters")
	public Flux<CharactersData> getCharacters() {
		return charactersService.getCharacters();
	}

	@ApiOperation(value = "Get characters by ID", notes = "Provides characters by id")
	@ApiResponse(code = 500, message = "Internal server error")
	@GetMapping("/characters/{characterId}")
	public Mono<CharactersData> getCharactersById(@PathVariable("characterId") int id) {
		return charactersService.getCharactersById(id);
	}

	@ApiOperation(value = "Get comics with character", notes = "Provides characters by id")
	@ApiResponse(code = 500, message = "Internal server error")
	@GetMapping("characters/{characterId}/comics")
	public Flux<ComicsData> getComicsWithCharacters(@PathVariable("characterId") int id) {
		return charactersService.getComicsWithCharacters(id);
	}
}
