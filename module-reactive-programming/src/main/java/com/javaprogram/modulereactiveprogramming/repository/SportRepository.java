package com.javaprogram.modulereactiveprogramming.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.javaprogram.modulereactiveprogramming.entity.Sport;

import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, Integer> {

    Mono<Sport> findByName(String name);
}
