package com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.A03_ERROR_HANDELING;

import com.munaf.A37_REACTIVE_PROGRAMMING_BASICS.util.BusinessException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ErrorHandlingMono {

    public void learnReactor() {

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


        Mono<String> mono = Mono.fromCallable(() -> callableMethod(true));

        mono
                // .onErrorComplete()
                // .onErrorResume(error -> Mono.just("FallbackMono"))
                // .onErrorReturn("DefaultFallBack")
                // .onErrorMap(err -> new BusinessException("This exception converted to BusinessException"))
                .subscribe(
                        data -> System.out.println("Data : " + data),
                        error -> System.out.println("Error : " + error),
                        () -> System.out.println("Completed")
                );

    }

    public String callableMethod(boolean error) {
        if (error) throw new RuntimeException("This is error in mono");
        return "Hello";
    }

}
