package com.javaprogram.modulereactiveprogramming.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.javaprogram.modulereactiveprogramming.handler.SportHandler;

@Configuration
public class SportRouter {
    public static final String FIND_SPORT_ROUTE = "/route/api/v1/sport";
    public static final String ADD_SPORT_ROUTE = "/route/api/v1/sport/{sportName}";

    @Bean
    public RouterFunction<ServerResponse> route(SportHandler sportHandler) {
        RequestPredicate getSportRoute = RequestPredicates.GET(FIND_SPORT_ROUTE);
        RequestPredicate postSportRoute = RequestPredicates.POST(ADD_SPORT_ROUTE);
        return RouterFunctions.route(getSportRoute, sportHandler::findSportByName)
                .andRoute(postSportRoute, sportHandler::addSport);
    }

}
