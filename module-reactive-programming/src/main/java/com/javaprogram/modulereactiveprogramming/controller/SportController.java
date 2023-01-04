package com.javaprogram.modulereactiveprogramming.controller;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javaprogram.modulereactiveprogramming.entity.Sport;
import com.javaprogram.modulereactiveprogramming.service.SportService;
import com.javaprogram.modulereactiveprogramming.setup.Data;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sport")
@RequiredArgsConstructor
public class SportController {

    private final SportService sportService;

    private final RestTemplate restTemplate;

    @GetMapping
    public Mono<Sport> findSportByName(@RequestParam String q) {
        return sportService.findSportByName(q);
    }

    @GetMapping("/all")
    public Flux<Sport> findAllSport() {
        return sportService.findAllSports();
    }

    @PostMapping("/{sportName}")
    public Mono<Sport> addSport(@PathVariable("sportName") String name) {
        return sportService.addSport(name);
    }

    @GetMapping("/load")
    public Mono<Void> load(@RequestParam("from") String url) {
        return Flux.fromStream(restTemplate
                        .getForEntity(url, Data.class)
                        .getBody()
                        .getData().stream()
                        .map(r -> r.getAttributes().getName())
                        .filter(Objects::nonNull)
                        .distinct())
                .flatMap(sportService::addSport)
                .then();
    }
}
