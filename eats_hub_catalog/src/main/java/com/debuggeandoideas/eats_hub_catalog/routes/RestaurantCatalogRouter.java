package com.debuggeandoideas.eats_hub_catalog.routes;

import com.debuggeandoideas.eats_hub_catalog.handlers.RestaurantCatalogHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RestaurantCatalogRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(RestaurantCatalogHandler handler) {

        return route()
                .path("/restaurants", builder -> builder
                        .GET(BY_NAME_URL, handler::getRestaurantByName)
                        .GET("", request -> {
                            if (request.queryParam("cousinType").isPresent()) {
                                return handler.getRestaurantsByCousinType(request);
                            }
                            if (request.queryParam("prices").isPresent()) {
                                return handler.getRestaurantBetweenPrice(request);
                            }
                            if (request.queryParam("city").isPresent()) {
                                return handler.getRestaurantsByCity(request);
                            } else {
                                return handler.getAllRestaurants(request);
                            }
                        })
                )
                .build();
    }

    private final static String BY_NAME_URL =  "/{name}";
}
