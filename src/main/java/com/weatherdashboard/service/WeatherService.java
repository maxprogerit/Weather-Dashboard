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
                .onStatus(status -> status.is4xxClientError(), 
                    clientResponse -> clientResponse.bodyToMono(String.class)
                        .map(body -> {
                            if (clientResponse.statusCode().value() == 401) {
                                return new RuntimeException("API key is invalid or not yet activated. Please wait up to 2 hours for new keys to become active.");
                            } else if (clientResponse.statusCode().value() == 404) {
                                return new RuntimeException("City not found. Please check the spelling and try again.");
                            } else {
                                return new RuntimeException("Request failed: " + body);
                            }
                        }))
                .bodyToMono(WeatherData.class);
    }

    public Mono<ForecastData> getForecast(String city) {
        String url = String.format("%s/forecast?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), 
                    clientResponse -> clientResponse.bodyToMono(String.class)
                        .map(body -> {
                            if (clientResponse.statusCode().value() == 401) {
                                return new RuntimeException("API key is invalid or not yet activated. Please wait up to 2 hours for new keys to become active.");
                            } else if (clientResponse.statusCode().value() == 404) {
                                return new RuntimeException("City not found. Please check the spelling and try again.");
                            } else {
                                return new RuntimeException("Request failed: " + body);
                            }
                        }))
                .bodyToMono(ForecastData.class);
    }

    public Mono<WeatherData> getCurrentWeatherByCoordinates(double lat, double lon) {
        String url = String.format("%s/weather?lat=%s&lon=%s&appid=%s&units=metric", apiUrl, lat, lon, apiKey);
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), 
                    clientResponse -> clientResponse.bodyToMono(String.class)
                        .map(body -> {
                            if (clientResponse.statusCode().value() == 401) {
                                return new RuntimeException("API key is invalid or not yet activated. Please wait up to 2 hours for new keys to become active.");
                            } else {
                                return new RuntimeException("Location request failed: " + body);
                            }
                        }))
                .bodyToMono(WeatherData.class);
    }
}