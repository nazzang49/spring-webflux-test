package com.test;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.justOrEmpty;

@SpringBootTest
public class SampleTest {

    @Test
    @DisplayName("testReactorOperator")
    public void testReactorOperator() {

        // todo create and transform sequence
        Mono<Integer> integerMono = Mono.just(1);
        Mono<Integer> integerMono1 = Mono.justOrEmpty(Optional.ofNullable(1));

        // defer == lazy
        Mono<Integer> integerMono2 = integerMono1.switchIfEmpty(defer(() -> Mono.error(new RuntimeException("test"))));
        integerMono2.subscribe(integer -> System.out.println("TEST-DEFER : " + integer));

        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5)
                .flatMapSequential(integer -> Mono.just(integer.intValue() + 2));

        // disposable resource
//        Mono<Object> using = Mono.using(
//                null,
//                null,
//                null
//        );

        Flux<Object> handle = integerFlux.handle((integer, synchronousSink) -> Mono.just(integer));
        handle.subscribe(o -> System.out.println("HANDLE-TEST : " + o));

        integerFlux.subscribe(integer -> System.out.println("FLATMAP-SEQUENTIAL-TEST : " + integer));

        // flux to list
        Mono<List<Integer>> listMono = integerFlux.collectList();

        // flux to map
        Mono<Map<Object, Integer>> mapMono = integerFlux.collectMap(integer -> integer.intValue() + 2);

        // integer = integer + integer2
        Mono<Integer> reduce = integerFlux.reduce((integer, integer2) -> integer + integer2);
        reduce.subscribe(integer -> System.out.println("REDUCE-TEST : " + integer));

        // if type same
        Flux<Integer> integerFlux1 = integerFlux.concatWith(Flux.range(1, 5));
        integerFlux1.subscribe(integer -> System.out.println("CONCATWITH-TEST : " + integer));

        // if type diff
        Flux<Integer> integerFlux2 = integerFlux.zipWith(Flux.just("1", "2", "3", "4"), (integer, s) -> integer.intValue() + Integer.valueOf(s));
        integerFlux2.subscribe(integer -> System.out.println("ZIPWITH-TEST : " + integerFlux2));

        // interval emit publisher
        Flux<Integer> integerFlux3 = Flux.interval(Duration.ofSeconds(10)).flatMap(aLong -> integerFlux);
        integerFlux3.subscribe(integer -> System.out.println("INTERVAL-TEST : " + integer));

        // complete as mono
        Mono<Void> then = integerFlux.then();

        Flux<Integer> integerFlux4 = integerFlux.thenMany(integerFlux2);
        integerFlux4.subscribe(integer -> System.out.println("THENMANY-TEST : " + integer));

        Flux<Integer> integerFlux5 = integerFlux.delayUntil(integer -> Flux.just(1 + integer));
        integerFlux5.subscribe(integer -> System.out.println("DELAYUNTIL-TEST : " + integer));

        // todo peek into sequence


    }
}
