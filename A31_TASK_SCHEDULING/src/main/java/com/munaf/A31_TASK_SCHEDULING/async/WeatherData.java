package com.munaf.A31_TASK_SCHEDULING.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class WeatherData {



    @Async
    public CompletableFuture<String> getCity() throws InterruptedException {
        System.out.println("City Task Started with thread name : " + Thread.currentThread().getName());
        Thread.sleep(2000);
        return CompletableFuture.completedFuture("Palanpur");
    }


    @Async
    public CompletableFuture<String> getDesc() throws InterruptedException {
        System.out.println("Desc Task Started with thread name : " + Thread.currentThread().getName());
        Thread.sleep(2000);
        return CompletableFuture.completedFuture("Sunny");
    }


    @Async
    public CompletableFuture<String> getTemp() throws InterruptedException {
        System.out.println("Temp Task Started with thread name : " + Thread.currentThread().getName());
        Thread.sleep(2000);
        return CompletableFuture.completedFuture("30");
    }


}
