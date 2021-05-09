package com.marvel.example.controllers;

import com.marvel.example.api.response.CharactersResponse;
import com.marvel.example.api.response.SingleCharacterResponse;
import com.marvel.example.api.response.ThumbnailResponse;
import com.marvel.example.exceptions.CharacterNotFoundException;
import com.marvel.example.service.CharactersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("characters data")
@RestController
@RequestMapping("/public/characters")
public class CharactersController {

	private Logger logger = Logger.getLogger(CharactersController.class);
	private CharactersService charactersService;

	@Autowired
	public CharactersController(CharactersService charactersService) {
		this.charactersService = charactersService;
	}

	@GetMapping
	@ApiOperation("method to get all characters")
	public ResponseEntity<CharactersResponse> getCharacters(@RequestParam(defaultValue = "0") Integer offset,
                                                            @RequestParam(defaultValue = "20") Integer limit) {
        logger.info("get all characters page");
		return ResponseEntity.ok(charactersService.getCharactersOrderById(offset, limit));
	}

	@GetMapping("/search")
    @ApiOperation("method to search characters by user query")
    public ResponseEntity<CharactersResponse> getCharactersByQuery(@RequestParam(defaultValue = "") String query,
                                                                   @RequestParam(defaultValue = "0") Integer offset,
                                                                   @RequestParam(defaultValue = "20") Integer limit) {
        logger.info("try to search characters with query: " + query);
	    return ResponseEntity.ok(charactersService.getCharactersByQuery(query, offset, limit));
    }

    @GetMapping("/{ID}")
    @ApiOperation("method to get character by id")
    public ResponseEntity<SingleCharacterResponse> getCharacterById(@PathVariable("ID") Integer id) {
        logger.info("try to get character with id: " + id);
	    return ResponseEntity.ok(charactersService.getCharacterById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<SingleCharacterResponse> saveCharacter(@RequestParam String name,
                                                                 @RequestParam String description,
                                                                 @RequestParam String path,
                                                                 @RequestParam String extension) {
	    charactersService.saveCharacters(name, description, path, extension);
	    return ResponseEntity.ok(new SingleCharacterResponse(name, description, new ThumbnailResponse(path, extension)));
    }

    @PutMapping("/update")
    public ResponseEntity<SingleCharacterResponse> updateCharacter(@RequestParam Integer id,
                                                                   @RequestParam String name,
                                                                   @RequestParam String description,
                                                                   @RequestParam String path,
                                                                   @RequestParam String extension) throws CharacterNotFoundException {
	    if (charactersService.getCharacterById(id) != null) {
            charactersService.saveCharacters(id, name, description, path, extension);
            logger.info("character update success");
            return ResponseEntity.ok(new SingleCharacterResponse(name, description, new ThumbnailResponse(path, extension)));
        } else {
	        logger.info("update fail");
	        throw new CharacterNotFoundException("character with id: " + id + " not found");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<SingleCharacterResponse> deleteCharacter(@RequestParam Integer id) throws CharacterNotFoundException {
	    if (charactersService.getCharacterById(id) != null) {
	        logger.info("character delete success");
            return ResponseEntity.ok(charactersService.deleteCharacters(id));
        } else {
	        logger.info("delete fail");
	        throw new CharacterNotFoundException("character with id: " + id + " not found");
        }
    }
}