package com.test.a;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Service
public class SampleService {

    public Flux<Integer> test() {
        return Flux.range(1, 10);
    }

    public Mono<List<Integer>> test1() {
        System.out.println("test1-came-in");
        return Flux.range(1, 10).collectList();
    }

    public Mono<List<Integer>> test2() {
        System.out.println("test2-came-in");
        return Flux.range(1, 10).collectList();
    }

    public Mono<List<Integer>> test3() {
        System.out.println("test3-came-in");
        return Flux.range(1, 10).collectList();
    }

    public Mono<HashMap<String, Object>> test4() {
        System.out.println("test4-came-in");
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        return Mono.just(stringObjectHashMap);
    }
}
