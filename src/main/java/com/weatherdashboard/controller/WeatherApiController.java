package com.weatherdashboard.controller;

import com.weatherdashboard.model.WeatherData;
import com.weatherdashboard.model.ForecastData;
import com.weatherdashboard.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherApiController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/current")
    public Mono<WeatherData> getCurrentWeather(@RequestParam String city) {
        return weatherService.getCurrentWeather(city);
    }

    @GetMapping("/forecast")
    public Mono<ForecastData> getForecast(@RequestParam String city) {
        return weatherService.getForecast(city);
    }

    @GetMapping("/current/coordinates")
    public Mono<WeatherData> getCurrentWeatherByCoordinates(
            @RequestParam double lat, 
            @RequestParam double lon) {
        return weatherService.getCurrentWeatherByCoordinates(lat, lon);
    }
}