package com.marvel.example.service;

import com.marvel.example.model.ComicsData;
import com.marvel.example.model.ComicsWithChars;
import com.marvel.example.utils.AppUtil;
import com.marvel.example.model.CharactersData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

//import com.jm.marvel.util.CharactersUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CharactersClientImpl implements CharactersClient {

	private WebClient webClient;

	@Value("${marvelApiPublicKey}")
	public String publicKey;
	@Value("${marvelApiPrivateKey}")
	public String privateKey;

	public CharactersClientImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://gateway.marvel.com:443/v1/public/").build();
	}

	@Cacheable(value = "characters")
	@Override
	public Flux<CharactersData> getCharacters() {

		return this.webClient.get()
               .uri(uriBuilder -> uriBuilder.path("/characters")
	                .queryParam("ts",  AppUtil.timeStamp)
	                .queryParam("apikey",publicKey)
	                .queryParam("hash", AppUtil.MD5hash(publicKey, privateKey,AppUtil.timeStamp))
	                .build())
	                .retrieve()
	                .bodyToFlux(CharactersData.class).cache().retry();
	}

	@Override
	public Mono<CharactersData> getCharactersById(int id) {
		return this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/characters/" + id)
	                .queryParam("ts",  AppUtil.timeStamp)
	                .queryParam("apikey",publicKey)
	                .queryParam("hash", AppUtil.MD5hash(publicKey, privateKey,AppUtil.timeStamp))
	                .build())
					.retrieve()
					.bodyToMono(CharactersData.class).retry();
	}

	@Override
	public Flux<ComicsData> getComicsWithCharacters(int id) {
		return this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/characters/" + id + "/comics")
						.queryParam("ts",  AppUtil.timeStamp)
						.queryParam("apikey",publicKey)
						.queryParam("hash", AppUtil.MD5hash(publicKey, privateKey,AppUtil.timeStamp))
						.build())
				.retrieve()
				.bodyToFlux(ComicsData.class).retry();
	}
}
