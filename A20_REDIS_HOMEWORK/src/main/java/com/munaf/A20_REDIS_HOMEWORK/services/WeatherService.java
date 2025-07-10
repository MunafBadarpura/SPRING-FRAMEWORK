package com.munaf.A20_REDIS_HOMEWORK.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    @Qualifier("weatherRestClient")
    private RestClient restClient;

    @Value("${weather.api.key}")
    private String weatherApiKey;

    @Cacheable(cacheNames = "weatherCache", key = "#location", unless = "#result == null")
    public Object getCurrentWeather(String location) {
        log.info("Calling weather api for location : " + location);

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                                .queryParam("q", location)
                                .queryParam("key", weatherApiKey)
                                .build())

                .retrieve()
                .body(Object.class);
    }
}
