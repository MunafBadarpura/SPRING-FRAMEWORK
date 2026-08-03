package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FluxExample3 {

    Flux<String> fruites = Flux.just("Apple", "Banana", "Mango", "Kiwi", "Orange", "Grape", "Lemon", "Lime")
            .map((fruite) -> {
                if (fruite.equals("Grape")) throw new RuntimeException("Dont like Kiwi");
                return  fruite;
            });

    public void learnReactor() {
        fruites.subscribe( // Request all values
                fruit -> System.out.println("Fruit : " + fruit), // this is onNext
                err -> System.out.println("Error is : " + err.getMessage()), // this is onError
                () -> System.out.println("Completed") // this is onComplete
        );

    }

}

/*

// Reactor internally creates something conceptually like:

Subscriber<String> subscriber = new Subscriber<>() {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String value) {
        System.out.println("Fruit : " + value);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Error is : " + t.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
};

* */