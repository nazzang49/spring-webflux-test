package com.test.a;

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
 * @author jinyoung.park
 */
@Configuration
public class SampleRouter {

    @Bean
    public RouterFunction<ServerResponse> sampleRoute(SampleHandler sampleHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/sample/{test}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), sampleHandler::test);
    }
}
