package com.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private String name;
    private Main main;
    private Weather[] weather;
    private Wind wind;
    private Clouds clouds;
    private Sys sys;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] weather) { this.weather = weather; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    public Sys getSys() { return sys; }
    public void setSys(Sys sys) { this.sys = sys; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private int pressure;
        private int humidity;

        // Getters and Setters
        public double getTemp() { return temp; }
        public void setTemp(double temp) { this.temp = temp; }

        public double getFeels_like() { return feels_like; }
        public void setFeels_like(double feels_like) { this.feels_like = feels_like; }

        public double getTemp_min() { return temp_min; }
        public void setTemp_min(double temp_min) { this.temp_min = temp_min; }

        public double getTemp_max() { return temp_max; }
        public void setTemp_max(double temp_max) { this.temp_max = temp_max; }

        public int getPressure() { return pressure; }
        public void setPressure(int pressure) { this.pressure = pressure; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String main;
        private String description;
        private String icon;

        // Getters and Setters
        public String getMain() { return main; }
        public void setMain(String main) { this.main = main; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private double speed;
        private int deg;

        // Getters and Setters
        public double getSpeed() { return speed; }
        public void setSpeed(double speed) { this.speed = speed; }

        public int getDeg() { return deg; }
        public void setDeg(int deg) { this.deg = deg; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds {
        private int all;

        // Getters and Setters
        public int getAll() { return all; }
        public void setAll(int all) { this.all = all; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        private String country;
        private long sunrise;
        private long sunset;

        // Getters and Setters
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }

        public long getSunrise() { return sunrise; }
        public void setSunrise(long sunrise) { this.sunrise = sunrise; }

        public long getSunset() { return sunset; }
        public void setSunset(long sunset) { this.sunset = sunset; }
    }
}