package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FluxExample1 {

    Flux<String> fruites = Flux.just("Apple", "Banana", "Mango", "Kiwi")
            .map((fruite) -> {
                if (fruite.equals("Kiwi")) throw new RuntimeException("Dont like Kiwi");
                return  fruite;
            });

    public void learnReactor() {
        fruites.subscribe(new Subscriber<>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;

                System.out.println("entered in onSubscribe");
                subscription.request(1);
                // subscription.request(Long.MAX_VALUE); // to stream all values
            }

            @Override
            public void onNext(String fruit) {
                System.out.println("entered in onNext");
                System.out.println("Fruit : " + fruit);

                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("entered in onError");
                System.out.println("Error is : " + throwable.getMessage());
            }

            @Override
            public void onComplete() { // onComplete only calls when all data in streamed
                System.out.println("entered in onComplete");
            }
        });

    }

}
