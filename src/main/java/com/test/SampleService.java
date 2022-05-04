package com.test;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SampleService {

    public Mono<List<Integer>> test() {
        return Flux.range(1, 10).collectList();
    }
}
