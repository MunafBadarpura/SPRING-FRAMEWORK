package com.munaf.A31_TASK_SCHEDULING.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get")
    public Weather getWeather() {
        System.out.println("New Thread Started with name : " + Thread.currentThread().getName());

        Weather weather = weatherService.getWeather();

        return weather;
    }


}
