package com.test.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.util.List;

/**
 * A handler for sample request - response
 *
 * @author jinyoung.park
 */
@Component
public class SampleHandler {

    @Autowired
    private SampleService sampleService;

    public Mono<ServerResponse> test(ServerRequest serverRequest) {
        Mono<List<Integer>> test = sampleService.test();
        test.subscribe(o -> System.out.println("test : " + o));

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(test, List.class);
    }
}
