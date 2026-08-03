package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A02_OPERATORS;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class OperatorExample1 {

    public void learnReactor() throws ExecutionException, InterruptedException {

        // 1. just() : Creates a cold publisher (data is generated at the time of subscription)
        Flux<String> fruits = Flux.just("apple", "banana", "cherry", "orange", "grapes");
        // fruits.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 2. fromIterable() / fromStream() : Converts Java collections or streams into a Flux.
        Flux<String> fruits2 = Flux.fromIterable(List.of("apple", "banana", "cherry", "orange", "grapes"));
        // fruits2.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 3. range() : Creates a Flux of Integer values within a specified range.
        Flux<Integer> numbers = Flux.range(10, 10);
        // numbers.subscribe(number -> System.out.println("Number : " + number));

        // 4. empty() : Creates an empty Flux.
        Flux<String> emptyFlux = Flux.empty();
        // emptyFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 5. interval() : Emits sequential numbers at fixed time intervals (great for polling/tickers)
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1));
        // intervalFlux.subscribe(number -> System.out.println("Number : " + number));

        // 6. fromCallable() : Creates a Flux from a Callable (great for lazy initialization)
        Mono<String> result = Mono.fromCallable(() -> slowDatabaseCall());
        // result.subscribe(data -> System.out.println("Data : " + data));

        // 7. map() : Transforming Data. Maintains 1:1 relationship and is the fastest transformation operator.
        Flux<String> fruitUpperCase = fruits.map(fruit -> fruit.toUpperCase());
        // fruitUpperCase.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 8. flatMap() : Used when each item produces a new reactive stream
        Flux<String> flatMapFlux = fruits.flatMap(fruit -> Flux.just(fruit + "1", fruit + "2"));
        // flatMapFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 9. filter() : Filtering Data Streams
        Flux<String> filteredFlux = fruits.filter(fruit -> fruit.startsWith("a"));
        // filteredFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 10. take() & skip() : Limiting the stream. Useful for pagination in reactive repositories.
        Flux<String> takeFlux = fruits.skip(3).take(2);
        // takeFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 11. count() – Count total items. Operator waits for the stream to complete (onComplete) before emitting the final count.
        Mono<Long> countFlux = fruits.count();
        // countFlux.subscribe(count -> System.out.println("Count : " + count));

        // 12. sort() : Sorting elements. Buffers all elements in memory before sorting, be careful with large datasets.
        Flux<String> sortedFlux = fruits.sort();
        // Also take Comparator :  Flux<String> sortedFlux = fruits.sort(Comparator.reverseOrder());
        // sortedFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 13. reduce() : Aggregating values. Similar to Stream.reduce() but reactive
        Mono<String> reduceFlux = fruits.reduce((fruit1, fruit2) -> fruit1 + ", " + fruit2);
        // reduceFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 14. groupBy() : Grouping elements
        Flux<String> groupFlux = fruits.groupBy(fruit -> fruit.length())
                .flatMap(group -> group.collectList().map(list -> group.key() + " : " + list));
        // groupFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 15. concat() / concatWith() : Combining Sequentially (Guarantees order by waiting
        // for first stream to complete before starting next.)
        Flux<String> concatFlux = Flux.concat(fruits, fruits2);
        // concatFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 16. merge() / mergeWith() : Merging streams Does **NOT** guarantee order but
        // is faster as streams run in parallel.
        Flux<String> mergeFlux = Flux.merge(fruits, fruits2); // we can write also : fruits.mergeWith(emptyFlux);
        // mergeFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 17. zip() : Combining streams by zipping values from both streams
        Flux<String> zipFlux = Flux.zip(fruits, fruits2, (fruit1, fruit2) -> fruit1 + " is " + fruit2);
        // zipFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 18. collectList() : Most common way to convert Flux<T> → Mono<List<T>> : for REST API responses.
        Mono<List<String>> collectListFlux = fruits.collectList();
        // collectListFlux.subscribe(list -> System.out.println(list));


        /// Mono Specific Operators

        // 19. single() : Enforce exactly one item. Throws exception if more than one item
        // Mono<String> singleFlux = fruits.single(); -> ERROR
        Flux<String> testFlux  = Flux.just("Test");
        Mono<String> singleFlux = testFlux.single();
        // singleFlux.subscribe(fruit -> System.out.println("Fruit : " + fruit));

        // 20. block() – Blocking (Only for testing)
        String blockingMono = Mono.just("Hello").block();  // blocks the calling thread here
        // System.out.println("blockingMono : " + blockingMono);

        // 21. toFuture() : Convert to CompletableFuture. helps to convert reactive code with traditional async code.
        CompletableFuture<String> future = Mono.just("Hello").toFuture();
        // System.out.println("future : " + future.get());

        // 22. repeat() – Repeat emission : Returns a Flux
        Flux<String> repeatFlux = Mono.just("Hello").repeat(3); // Repeat 3 times
        // repeatFlux.subscribe(fruit -> System.out.println(repeatFlux));

        // 22. then() – Ignores previous result and continues — commonly used for fire and-forget operations after save.
        Mono<String> then = Mono.just("Process this")
                .doOnNext(data -> data = "Process this 2")
                .then(Mono.just("Final Result"));

        then.subscribe(data -> System.out.println("Then Data : " + data));

    }


    // Helpers
    private String slowDatabaseCall() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "This is Database data";
    }

}
