package com.marvel.example.controller;

//import com.jm.marvel.integration.CharactersClient;
import com.marvel.example.model.CharactersData;
import com.marvel.example.service.ComicsClient;
import com.marvel.example.model.Comics;
import com.marvel.example.model.ComicsData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ComicsController {

    private ComicsClient comicsService;

    public ComicsController(ComicsClient comicsService) {
        this.comicsService = comicsService;
    }

    @ApiOperation(value = "Get comics")
    @ApiResponse(code = 500, message = "Internal server error")
    @GetMapping("/comics")
    public Flux<ComicsData> getComics() {
        return comicsService.getComics();
    }

    @ApiOperation(value = "Get comics by ID")
    @ApiResponse(code = 500, message = "Internal server error")
    @GetMapping("/comics/{comicsId}")
    public Mono<ComicsData> getComicsById(@PathVariable("comicsId") int id) {
        return comicsService.getComicsById(id);
    }

    @ApiOperation(value = "Get characters in comics")
    @ApiResponse(code = 500, message = "Internal server error")
    @GetMapping("/comics/{comicsId}/characters")
    public Flux<CharactersData> getCharsInComics(@PathVariable("comicsId") int id) {
        return comicsService.getCharsInComics(id);
    }
}
