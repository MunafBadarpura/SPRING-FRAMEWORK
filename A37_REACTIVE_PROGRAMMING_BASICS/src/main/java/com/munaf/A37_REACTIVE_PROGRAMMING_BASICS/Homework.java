package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Component
public class Homework {

    public void learnReactor() {

        /// FlatMap
//        Flux<String> names = Flux.just("Alice", "Bob", "Charlie");
//
//        names.flatMap(name ->
//                Flux.just(name, String.valueOf(name.length()))
//        ).subscribe(System.out::println);


        /// Merge and Concat

        Flux<Integer> flux1 = Flux.just(1, 2, 3).delayElements(Duration.ofMillis(500));
        Flux<Integer> flux2 = Flux.just(10, 20, 30).delayElements(Duration.ofMillis(300));

        // Flux.concat(flux1, flux2).subscribe(System.out::println); // Slow but maintain order
        // Flux.merge(flux1, flux2).subscribe(System.out::println); // Fast but doesn't maintain order


        /// Zip
//        Flux<String> athelets = Flux.just("Lebron", "Kobe", "Curry");
//        Flux<Integer> scores = Flux.just(100, 90, 80);
//
//        athelets.zipWith(scores, (name, score) -> name + " Scored " + score)
//                .subscribe(
//                        (data) -> System.out.println(data)
//                );


        ///  Reactive Number Processor
        Mono<List<Integer>> procesror = Flux.range(1, 20)
                .doOnSubscribe(subscription -> System.out.println("Started"))
                .filter(number -> number % 2 == 0) // even numbers only
                .map((number) -> number * 10)
                .flatMap(number -> Flux.just(number).delayElements(Duration.ofMillis(500)))
                .doOnNext(item -> System.out.println("Processing : " + item))
                .skip(2)
                .doOnComplete(() -> System.out.println("Flux Completed"))
                .collectList()
                .onErrorReturn(List.of(999));

    }

}


