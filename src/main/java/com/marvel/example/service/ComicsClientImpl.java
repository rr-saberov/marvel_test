package com.marvel.example.service;

import com.marvel.example.model.CharactersData;
import com.marvel.example.model.ComicsData;
import com.marvel.example.utils.AppUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ComicsClientImpl implements ComicsClient {

    private WebClient webClient;

    @Value("${marvelApiPublicKey}")
    public String publicKey;
    @Value("${marvelApiPrivateKey}")
    public String privateKey;

    public ComicsClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://gateway.marvel.com:443/v1/public/").build();
    }

    @Cacheable(value = "comics")
    @Override
    public Flux<ComicsData> getComics() {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/comics")
                        .queryParam("ts",  AppUtil.timeStamp)
                        .queryParam("apikey",publicKey)
                        .queryParam("hash", AppUtil.MD5hash(publicKey, privateKey, AppUtil.timeStamp))
                        .build())
                .retrieve()
                .bodyToFlux(ComicsData.class).cache().retry();
    }

    @Override
    public Mono<ComicsData> getComicsById(int id) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/characters/" + id)
                        .queryParam("ts",  AppUtil.timeStamp)
                        .queryParam("apikey",publicKey)
                        .queryParam("hash", AppUtil.MD5hash(publicKey, privateKey, AppUtil.timeStamp))
                        .build())
                .retrieve()
                .bodyToMono(ComicsData.class).retry();
    }

    @Override
    public Flux<CharactersData> getCharsInComics(int id) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/comics/" + id + "/characters")
                        .queryParam("ts",  AppUtil.timeStamp)
                        .queryParam("apikey",publicKey)
                        .queryParam("hash", AppUtil.MD5hash(publicKey, privateKey,AppUtil.timeStamp))
                        .build())
                .retrieve()
                .bodyToFlux(CharactersData.class).retry();
    }
}
