package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A03_ERROR_HANDELING;

import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ErrorHandlingFlux {

    public void learnReactor() {

        Flux<String> fruites = Flux.just("Apple", "Orange", "Grape", "Banana");
        Flux<String> fruitesWithError = fruites.map(fruite -> {
            if(fruite.equals("Grape")) {
                throw new RuntimeException("Grape is not a fruit");
            }
            return fruite;
        });

        // 1. onErrorComplete
        // : onComplete() will be called and error will be ignored

        // 2. onErrorResume
        // : Data streaming will continue with fallback data
        // : onComplete() will be called and error will be ignored after fallback data completes

        // 3. onErrorReturn
        // : Data streaming will continue with fallback data (Single data)
        // : onComplete() will be called and error will be ignored after fallback data completes

        // 4. onErrorMap
        // : Error will be converted to another type
        // : onError() will be called

        // 5. doOnError
        // : Side-effect operator (logging, metrics, cleanup, etc.)
        // : Does NOT handle or recover from the error
        // : Original error continues downstream
        // : Subscriber receives onError()


        fruitesWithError
                // .onErrorComplete()
                // .onErrorResume(error -> Flux.just("Fallback1", "Fallback2"))
                // .onErrorReturn("DefaultFallBack")
                // .onErrorMap(err -> new BusinessException("This exception converted to BusinessException"))
                .doOnError((err) -> System.out.println("Error on doOnError : " + err))
                .subscribe(
                        fruite -> System.out.println("Fruit : " + fruite),
                        error -> System.out.println("Error : " + error),
                        () -> System.out.println("Completed")
                );

    }

}
//        | Operator            | Recovers? | Emits Fallback? | Subscriber gets                 |
//        | ------------------- | --------- | --------------- | ------------------------------- |
//        | `onErrorComplete()` | ✅ Yes     | ❌ No            | `onComplete()`                  |
//        | `onErrorResume()`   | ✅ Yes     | ✅ Flux/Mono     | `onComplete()` (after fallback) |
//        | `onErrorReturn()`   | ✅ Yes     | ✅ One value     | `onComplete()`                  |
//        | `onErrorMap()`      | ❌ No      | ❌ No            | `onError(mappedException)`      |
//        | `doOnError()`       | ❌ No      | ❌ No            | `onError(originalException)`    |
