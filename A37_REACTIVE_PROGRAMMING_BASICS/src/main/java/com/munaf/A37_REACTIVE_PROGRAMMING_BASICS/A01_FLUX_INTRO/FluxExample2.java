package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A01_FLUX_INTRO;

import org.reactivestreams.Subscription;
import org.springframework.stereotype.Component;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

@Component
public class FluxExample2 {

    Flux<String> fruites = Flux.just("Apple", "Banana", "Mango", "Kiwi", "Orange", "Grape", "Lemon", "Lime")
            .map((fruite) -> {
                if (fruite.equals("Grape")) throw new RuntimeException("Dont like Kiwi");
                return  fruite;
            })
            .onErrorResume(error -> Flux.just("Fallback1", "Fallback2"));

    public void learnReactor() {
        fruites.subscribe(
                new BaseSubscriber<String>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("<-- entered in hookOnSubscribe");
                        subscription.request(1); // request 1 item
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        System.out.println("<-- entered in hookOnNext");
                        System.out.println("Fruit : " + value);
                        request(1); // request 1 item

                        // if (value.equals("Mango")) cancel(); // After this hookOnCancel() will be called
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("<-- entered in hookOnComplete");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("<-- entered in hookOnError");
                        System.out.println("Error is : " + throwable.getMessage());
                    }

                    @Override
                    protected void hookOnCancel() {
                        System.out.println("<-- entered in hookOnCancel");
                    }

                    @Override
                    protected void hookFinally(SignalType type) { // call everytime no matter if canceled or error or completed
                        System.out.println("<-- entered in hookFinally");
                        System.out.println("Signal type is : " + type);
                    }
                }
        );

    }

}
