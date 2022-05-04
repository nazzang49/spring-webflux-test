package com.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * A router for sample handler
 *
 * @author 박진영
 */
@Configuration
public class SampleRouter {

    @Bean
    public RouterFunction<ServerResponse> testRoute(SampleHandler sampleHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/{test}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), sampleHandler::test);
    }
}
