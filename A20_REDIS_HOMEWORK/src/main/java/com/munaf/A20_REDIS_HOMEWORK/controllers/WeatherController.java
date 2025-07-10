package com.munaf.A20_REDIS_HOMEWORK.controllers;

import com.munaf.A20_REDIS_HOMEWORK.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("weather")
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/current")
    public Object getCurrentWeather(@RequestParam(defaultValue = "India", required = false) String location) {
        return weatherService.getCurrentWeather(location);
    }



}
