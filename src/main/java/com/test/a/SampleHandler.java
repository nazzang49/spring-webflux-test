package com.test.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;

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
        Mono<HashMap<String, Object>> hashMapMono = sampleService.test4();
        return sampleService.test()
                .collectList()
                .flatMap(o -> {
                    // trigger as return
                    return hashMapMono.flatMap(stringObjectHashMap -> {
                        stringObjectHashMap.put("test-key1", "test-value1");
                        return Mono.just(stringObjectHashMap);
                    });
                })
                .flatMap(o -> {
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(o);
                });

//        return sampleService.test()
//                .flatMap(o -> {
//                    Mono<List<Integer>> listMono1 = sampleService.test1();
//                    Mono<List<Integer>> listMono2 = sampleService.test2();
//                    Mono<List<Integer>> listMono3 = sampleService.test3();
//                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(o), List.class);
//                });

//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(test, List.class);
    }
}
