package com.munaf.A30_TOOL_CALLING.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient webClient;

    private final ChatClient vertexChatClint;


    public WeatherController(WebClient webClient, @Qualifier("vertexChatClint") ChatClient vertexChatClint) {
        this.webClient = webClient;
        this.vertexChatClint = vertexChatClint;
    }


    @Tool(name = "get_current_weather", description = "This is the tool for getting current weather for a given city.")
    public String getCurrentWeather(@ToolParam(description = "city name") String city) {
        System.out.println("getCurrentWeather tool called for city: " + city);

        try {
            Object weatherData = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/current.json")
                            .queryParam("key", apiKey)
                            .queryParam("q", city)
                            .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Object>() {})
                    .block();  // use async if needed

            if (weatherData == null) return "No weather data found for " + city;
            return weatherData.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching weather data for " + city;
        }


    }

    // informational tool
    @Tool(name = "get_current_time", description = "This is the tool for getting current date and time.")
    public String getCurrentTime() {
        System.out.println("getCurrentTime tool called");
        return Instant.now().toString();
    }


    // action tool
    @Tool(name = "set_alarm", description = "This is the tool for setting an alarm.")
    public void setAlarm(@ToolParam(description = "time in ISO-8601 format") String time) {
        System.out.println("alarm value: " + time);
        LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println("Alarm set for: " + dateTime);
    }


    @GetMapping("/{userQuery}")
    public String getWeather(@PathVariable String userQuery) {
        return vertexChatClint
                .prompt()
                .user(userQuery)
                .system("Use tools if needed. else respond with general knowledge.")
                .tools(this)
                .call()
                .content();
    }

}
