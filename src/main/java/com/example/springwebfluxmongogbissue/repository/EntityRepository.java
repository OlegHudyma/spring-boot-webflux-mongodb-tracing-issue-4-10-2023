package com.example.springwebfluxmongogbissue.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.springwebfluxmongogbissue.model.Entity;

public interface EntityRepository extends ReactiveMongoRepository<Entity, String> {

}
