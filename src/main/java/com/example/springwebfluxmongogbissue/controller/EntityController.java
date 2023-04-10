package com.example.springwebfluxmongogbissue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.springwebfluxmongogbissue.model.Entity;
import com.example.springwebfluxmongogbissue.repository.EntityRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EntityController {

    private final EntityRepository entityRepository;

    @GetMapping(path = "/entity/{id}")
    private Mono<Entity> get(@PathVariable String id) {
        return Mono.just(id)
                .doOnNext(v -> log.info("Request received"))
                .doOnNext(v -> log.info("Fetching entity"))
                .flatMap(entityRepository::findById)
                .doOnNext(v -> log.info("Fetched entity"))
                .switchIfEmpty(Mono.defer(() -> Mono.just(id)
                        .doOnNext(v -> log.info("Creating entity"))
                        .map(v -> Entity.builder()
                                .id(v)
                                .build())
                        .flatMap(entityRepository::save)
                        .doOnNext(v -> log.info("Created entity"))))
                .doOnNext(v -> log.info("Request processed"));
    }
}
