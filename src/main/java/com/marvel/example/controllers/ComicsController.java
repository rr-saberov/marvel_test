package com.marvel.example.controllers;

import com.marvel.example.api.response.ComicsResponse;
import com.marvel.example.api.response.SingleComicsResponse;
import com.marvel.example.api.response.UrlsResponse;
import com.marvel.example.exceptions.ComicsNotFoundException;
import com.marvel.example.service.ComicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("comics data")
@RestController
@RequestMapping("/public/comics")
public class ComicsController {

    private Logger logger = Logger.getLogger(ComicsController.class);
    private ComicsService comicsService;

    @Autowired
    public ComicsController(ComicsService comicsService) {
        this.comicsService = comicsService;
    }

    @GetMapping
    @ApiOperation("method to get all comics")
    public ResponseEntity<ComicsResponse> getComics(@RequestParam(defaultValue = "0") Integer offset,
                                                    @RequestParam(defaultValue = "20") Integer limit) {
        logger.info("get all comics page");
        return ResponseEntity.ok(comicsService.getComicsOrderById(offset, limit));
    }

    @GetMapping("/search")
    @ApiOperation("method to search comics by user query")
    public ResponseEntity<ComicsResponse> getComicsByQuery(@RequestParam(defaultValue = "") String query,
                                                           @RequestParam(defaultValue = "0") Integer offset,
                                                           @RequestParam(defaultValue = "20") Integer limit) {
        logger.info("try to search comics with query: " + query);
        return ResponseEntity.ok(comicsService.getComicsByQuery(query, offset, limit));
    }

    @GetMapping("/{ID}")
    @ApiOperation("method to get comics by id")
    public ResponseEntity<SingleComicsResponse> getComicsById(@PathVariable("ID") Integer id) {
        logger.info("try to get comics with id: " + id);
        return ResponseEntity.ok(comicsService.getComicsById(id));
    }

    @PostMapping("/save")
    @ApiOperation("method to save new comics")
    public ResponseEntity<SingleComicsResponse> saveComics(@RequestParam String title,
                                                           @RequestParam String isbn,
                                                           @RequestParam String type,
                                                           @RequestParam String url) {
        logger.info("comics with title: " + title + " saved");
        comicsService.saveComics(title, isbn, type, url);
        return ResponseEntity.ok(new SingleComicsResponse(title, isbn, new UrlsResponse(type, url)));
    }

    @PutMapping("/update")
    @ApiOperation("method to update comics")
    public ResponseEntity<SingleComicsResponse> updateComics(@RequestParam Integer id,
                                                             @RequestParam String title,
                                                             @RequestParam String isbn,
                                                             @RequestParam String type,
                                                             @RequestParam String url) throws ComicsNotFoundException {
        if (comicsService.getComicsById(id) != null) {
            comicsService.updateComics(id, title, isbn, type, url);
            logger.info("update success");
            return ResponseEntity.ok(new SingleComicsResponse(id, title, isbn, new UrlsResponse(type, url)));
        } else {
            logger.info("comics not found");
            throw new ComicsNotFoundException("Comics to update not found!");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("method to delete comics")
    public ResponseEntity<SingleComicsResponse> deleteComics(@RequestParam Integer id) throws ComicsNotFoundException {
        if (comicsService.getComicsById(id) != null) {
            logger.info("delete success");
            return ResponseEntity.ok(comicsService.deleteComics(id));
        } else {
            logger.info("comics not found");
            throw new ComicsNotFoundException("Comics to delete not found!");
        }
    }
}
