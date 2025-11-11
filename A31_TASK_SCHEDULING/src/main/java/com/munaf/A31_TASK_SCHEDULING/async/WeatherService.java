package com.munaf.A31_TASK_SCHEDULING.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class WeatherService {

    private final WeatherData weatherData;

    public WeatherService(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public Weather getWeather() {
        try {
            Long start = System.currentTimeMillis();

            System.out.println("getWeather() Started with thread name : " + Thread.currentThread().getName());

            CompletableFuture<String> cityFuture = weatherData.getCity();
            CompletableFuture<String> descFuture = weatherData.getDesc();
            CompletableFuture<String> tempFuture = weatherData.getTemp();

            CompletableFuture.allOf(cityFuture, descFuture, tempFuture).join(); // start all 3 tasks with separate new 3 threads

            Weather weather = new Weather(cityFuture.get(), descFuture.get(), tempFuture.get());

            Long end = System.currentTimeMillis();

            System.out.println("Total Time Taken : " + (end - start));
            return weather;

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }



     // @Async don't work if we create in same class

//    @Async
//    public CompletableFuture<String> getCity() throws InterruptedException {
//        System.out.println("City Task Started with thread name : " + Thread.currentThread().getName());
//        Thread.sleep(2000);
//        return CompletableFuture.completedFuture("Palanpur");
//    }
//
//
//    @Async
//    public CompletableFuture<String> getDesc() throws InterruptedException {
//        System.out.println("Desc Task Started with thread name : " + Thread.currentThread().getName());
//        Thread.sleep(2000);
//        return CompletableFuture.completedFuture("Sunny");
//    }
//
//
//    @Async
//    public CompletableFuture<String> getTemp() throws InterruptedException {
//        System.out.println("Temp Task Started with thread name : " + Thread.currentThread().getName());
//        Thread.sleep(2000);
//        return CompletableFuture.completedFuture("30");
//    }


}
