package com.weatherdashboard.service;

import com.weatherdashboard.model.WeatherData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        weatherService = new WeatherService(WebClient.builder());
        ReflectionTestUtils.setField(weatherService, "apiKey", "test-api-key");
        ReflectionTestUtils.setField(weatherService, "apiUrl", "https://api.openweathermap.org/data/2.5");
    }

    @Test
    void weatherService_ShouldBeCreated() {
        // Test that the service can be instantiated
        assertNotNull(weatherService);
    }

    @Test
    void weatherDataModel_ShouldWorkCorrectly() {
        // Test the WeatherData model
        WeatherData weatherData = new WeatherData();
        weatherData.setName("London");
        
        assertEquals("London", weatherData.getName());
        assertNotNull(weatherData);
    }

    @Test
    void weatherDataMain_ShouldWorkCorrectly() {
        // Test the nested Main class
        WeatherData.Main main = new WeatherData.Main();
        main.setTemp(20.5);
        main.setHumidity(65);
        
        assertEquals(20.5, main.getTemp());
        assertEquals(65, main.getHumidity());
    }
}