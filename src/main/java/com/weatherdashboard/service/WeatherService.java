package com.weatherdashboard.service;

import com.weatherdashboard.model.WeatherData;
import com.weatherdashboard.model.ForecastData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<WeatherData> getCurrentWeather(String city) {
        String url = String.format("%s/weather?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherData.class);
    }

    public Mono<ForecastData> getForecast(String city) {
        String url = String.format("%s/forecast?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ForecastData.class);
    }

    public Mono<WeatherData> getCurrentWeatherByCoordinates(double lat, double lon) {
        String url = String.format("%s/weather?lat=%s&lon=%s&appid=%s&units=metric", apiUrl, lat, lon, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherData.class);
    }
}