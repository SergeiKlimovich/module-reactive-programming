package com.javaprogram.modulereactiveprogramming.service;

import org.springframework.stereotype.Service;

import com.javaprogram.modulereactiveprogramming.backpressure.CustomBackPressureSubscriber;
import com.javaprogram.modulereactiveprogramming.entity.Sport;
import com.javaprogram.modulereactiveprogramming.exception.EntityAlreadyExistException;
import com.javaprogram.modulereactiveprogramming.repository.SportRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SportService {

    public static final String ERROR_MESSAGE = " is existed.";
    private final SportRepository sportRepository;

    public Mono<Sport> findSportByName(String name) {
        return sportRepository.findByName(name);
    }

    public Flux<Sport> findAllSports() {
        Flux<Sport> sportFlux = sportRepository.findAll();
        sportFlux.log().subscribe(new CustomBackPressureSubscriber());
        return sportFlux;
    }

    public Mono<Sport> addSport(String name) {
        return sportRepository
                .findByName(name)
                .hasElement()
                .flatMap(isNameExist -> isNameExist ? Mono.error(new EntityAlreadyExistException(ERROR_MESSAGE)) :
                        sportRepository.save(new Sport(name)));
    }
}
