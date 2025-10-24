package com.weatherdashboard.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {
    private String cod;
    private int message;
    private int cnt;
    private ForecastItem[] list;
    private City city;

    // Getters and Setters
    public String getCod() { return cod; }
    public void setCod(String cod) { this.cod = cod; }

    public int getMessage() { return message; }
    public void setMessage(int message) { this.message = message; }

    public int getCnt() { return cnt; }
    public void setCnt(int cnt) { this.cnt = cnt; }

    public ForecastItem[] getList() { return list; }
    public void setList(ForecastItem[] list) { this.list = list; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastItem {
        private long dt;
        private WeatherData.Main main;
        private WeatherData.Weather[] weather;
        private WeatherData.Clouds clouds;
        private WeatherData.Wind wind;
        private String dt_txt;

        // Getters and Setters
        public long getDt() { return dt; }
        public void setDt(long dt) { this.dt = dt; }

        public WeatherData.Main getMain() { return main; }
        public void setMain(WeatherData.Main main) { this.main = main; }

        public WeatherData.Weather[] getWeather() { return weather; }
        public void setWeather(WeatherData.Weather[] weather) { this.weather = weather; }

        public WeatherData.Clouds getClouds() { return clouds; }
        public void setClouds(WeatherData.Clouds clouds) { this.clouds = clouds; }

        public WeatherData.Wind getWind() { return wind; }
        public void setWind(WeatherData.Wind wind) { this.wind = wind; }

        public String getDt_txt() { return dt_txt; }
        public void setDt_txt(String dt_txt) { this.dt_txt = dt_txt; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City {
        private String name;
        private String country;

        // Getters and Setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
}