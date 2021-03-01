package com.marvel.example.controllers;

import com.marvel.example.exception_handling.NoSuchComicsException;
import com.marvel.example.model.Characters;
import com.marvel.example.model.Comics;
import com.marvel.example.service.ComicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("comics data")
@RestController
@RequestMapping("/v1/public/comics")
public class ComicsController {

    private Logger logger = Logger.getLogger(CharactersController.class);
    private ComicsService comicsService;

    @Autowired
    public ComicsController(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @GetMapping
    @ApiOperation("method to get all comics")
    public List<Comics> comics() {
        return comicsService.getAllComics();
    }

    @GetMapping("/{comicsId}")
    @ApiOperation("method to get comics by id")
    public Comics comicsById(@PathVariable("comicsId") int id) {
        if (comicsService.getComicsById(id) == null) {
            throw new NoSuchComicsException("There is no comics with ID = " + id + " in Database");
        }
        return comicsService.getComicsById(id);
    }

    @GetMapping("/{comicsId}/comics")
    @ApiOperation("method to get comics with character")
    public List<Characters> comicsWithCharacters(@PathVariable("comicsId") int id) {
        if (comicsService.getComicsById(id) == null) {
            throw new NoSuchComicsException("There is no comics with ID = " + id + " in Database");
        }
        return comicsService.getComicsWithCharacter(id);
    }

    @PostMapping("/saveComics")
    @ApiOperation("method to save comics")
    public Comics saveComics(@RequestBody Comics comics) {
        return comicsService.saveComics(comics);
    }

    @PutMapping("/update")
    @ApiOperation("method to update comics")
    public Comics updateComics(@RequestBody Comics comics) {
        return comicsService.saveComics(comics);
    }

    @PostMapping("/deleteComics")
    @ApiOperation("method to delete comics")
    public void deleteComics(Integer id) {
        comicsService.deleteComics(id);
    }
}