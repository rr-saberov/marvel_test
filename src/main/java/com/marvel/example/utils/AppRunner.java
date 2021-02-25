package com.marvel.example.utils;

import com.marvel.example.service.CharactersClient;
import com.marvel.example.service.ComicsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private CharactersClient charactersService;
	private ComicsClient comicsService;

	public AppRunner(CharactersClient charactersService, ComicsClient comicsService) {
		this.charactersService = charactersService;
		this.comicsService = comicsService;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Loading characters from marvel api and caching");
		charactersService.getCharacters().subscribe();
		comicsService.getComics().subscribe();
	}
}
