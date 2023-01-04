package com.javaprogram.modulereactiveprogramming.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.javaprogram.modulereactiveprogramming.entity.Sport;
import com.javaprogram.modulereactiveprogramming.service.SportService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SportHandler {

    public static final String SPORT_NAME_PARAMETER = "q";
    public static final String SPORT_NAME_PATH_VARIABLE = "sportName";

    private final SportService sportService;

    public Mono<ServerResponse> findSportByName(ServerRequest serverRequest) {
        return serverRequest
                .queryParam(SPORT_NAME_PARAMETER)
                .map(sportName -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(sportService.findSportByName(sportName), Sport.class))
                .orElseGet(() -> ServerResponse
                        .status(400)
                        .build());
    }

    public Mono<ServerResponse> addSport(ServerRequest serverRequest) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sportService.addSport(serverRequest.pathVariable(SPORT_NAME_PATH_VARIABLE)), Sport.class);
    }
}
