package com.test.b;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * A router for test handler
 *
 * @author jinyoung.park
 */
@Configuration
public class TestRouter {

    @Bean
    public RouterFunction<ServerResponse> testRoute(TestHandler testHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/test/{test}")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), testHandler::test);
    }
}
