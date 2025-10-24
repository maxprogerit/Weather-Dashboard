// Weather Dashboard JavaScript

class WeatherDashboard {
    constructor() {
        this.initializeEventListeners();
        this.initializeTheme();
        this.updateCurrentDate();
    }

    initializeEventListeners() {
        const searchBtn = document.getElementById('search-btn');
        const citySearch = document.getElementById('city-search');
        const themeToggle = document.getElementById('theme-toggle');

        searchBtn.addEventListener('click', () => this.searchWeather());
        citySearch.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                this.searchWeather();
            }
        });
        themeToggle.addEventListener('click', () => this.toggleTheme());

        // Get user location on page load
        this.getCurrentLocationWeather();
    }

    initializeTheme() {
        const isDark = localStorage.getItem('theme') === 'dark' || 
                      (!localStorage.getItem('theme') && window.matchMedia('(prefers-color-scheme: dark)').matches);
        
        if (isDark) {
            document.documentElement.classList.add('dark');
            document.getElementById('theme-icon').className = 'fas fa-moon';
        } else {
            document.getElementById('theme-icon').className = 'fas fa-sun';
        }
    }

    toggleTheme() {
        const html = document.documentElement;
        const themeIcon = document.getElementById('theme-icon');
        
        if (html.classList.contains('dark')) {
            html.classList.remove('dark');
            themeIcon.className = 'fas fa-sun';
            localStorage.setItem('theme', 'light');
        } else {
            html.classList.add('dark');
            themeIcon.className = 'fas fa-moon';
            localStorage.setItem('theme', 'dark');
        }
    }

    updateCurrentDate() {
        const now = new Date();
        const options = { 
            weekday: 'long', 
            year: 'numeric', 
            month: 'long', 
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        };
        const dateStr = now.toLocaleDateString('en-US', options);
        
        const dateElement = document.getElementById('current-date');
        if (dateElement) {
            dateElement.textContent = dateStr;
        }
    }

    async getCurrentLocationWeather() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                async (position) => {
                    const { latitude, longitude } = position.coords;
                    await this.getWeatherByCoordinates(latitude, longitude);
                },
                (error) => {
                    console.log('Geolocation error:', error);
                    // Default to a popular city if geolocation fails
                    this.searchWeatherByCity('New York');
                }
            );
        } else {
            // Fallback to default city
            this.searchWeatherByCity('New York');
        }
    }

    async getWeatherByCoordinates(lat, lon) {
        this.showLoading();
        try {
            const response = await fetch(`/api/weather/current/coordinates?lat=${lat}&lon=${lon}`);
            if (!response.ok) {
                throw new Error('Weather data not found');
            }
            const weatherData = await response.json();
            this.displayCurrentWeather(weatherData);
            await this.getForecastByCity(weatherData.name);
        } catch (error) {
            this.showError('Unable to fetch weather data for your location');
        } finally {
            this.hideLoading();
        }
    }

    async searchWeather() {
        const city = document.getElementById('city-search').value.trim();
        if (!city) {
            this.showError('Please enter a city name');
            return;
        }
        await this.searchWeatherByCity(city);
    }

    async searchWeatherByCity(city) {
        this.showLoading();
        try {
            const response = await fetch(`/api/weather/current?city=${encodeURIComponent(city)}`);
            if (!response.ok) {
                throw new Error('City not found');
            }
            const weatherData = await response.json();
            this.displayCurrentWeather(weatherData);
            await this.getForecastByCity(city);
        } catch (error) {
            this.showError('City not found. Please check the spelling and try again.');
        } finally {
            this.hideLoading();
        }
    }

    async getForecastByCity(city) {
        try {
            const response = await fetch(`/api/weather/forecast?city=${encodeURIComponent(city)}`);
            if (!response.ok) {
                throw new Error('Forecast data not available');
            }
            const forecastData = await response.json();
            this.displayForecast(forecastData);
        } catch (error) {
            console.error('Forecast error:', error);
        }
    }

    displayCurrentWeather(data) {
        document.getElementById('city-name').textContent = `${data.name}, ${data.sys.country}`;
        document.getElementById('temperature').textContent = `${Math.round(data.main.temp)}°C`;
        document.getElementById('feels-like').textContent = `${Math.round(data.main.feels_like)}°C`;
        document.getElementById('humidity').textContent = `${data.main.humidity}%`;
        document.getElementById('wind-speed').textContent = `${Math.round(data.wind.speed * 3.6)} km/h`;
        document.getElementById('weather-description').textContent = data.weather[0].description;
        
        const iconUrl = `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
        document.getElementById('weather-icon').src = iconUrl;

        document.getElementById('current-weather').classList.remove('hidden');
        document.getElementById('error-message').classList.add('hidden');
    }

    displayForecast(data) {
        const container = document.getElementById('forecast-container');
        container.innerHTML = '';

        // Group forecast data by day (take one forecast per day, preferably around noon)
        const dailyForecasts = this.groupForecastByDay(data.list);

        dailyForecasts.slice(0, 7).forEach(forecast => {
            const forecastCard = this.createForecastCard(forecast);
            container.appendChild(forecastCard);
        });

        document.getElementById('forecast-section').classList.remove('hidden');
    }

    groupForecastByDay(forecasts) {
        const grouped = {};
        
        forecasts.forEach(forecast => {
            const date = new Date(forecast.dt * 1000);
            const dateKey = date.toDateString();
            
            if (!grouped[dateKey]) {
                grouped[dateKey] = forecast;
            } else {
                // Prefer forecasts around noon (12:00)
                const currentHour = new Date(grouped[dateKey].dt * 1000).getHours();
                const newHour = date.getHours();
                
                if (Math.abs(newHour - 12) < Math.abs(currentHour - 12)) {
                    grouped[dateKey] = forecast;
                }
            }
        });

        return Object.values(grouped);
    }

    createForecastCard(forecast) {
        const date = new Date(forecast.dt * 1000);
        const dayName = date.toLocaleDateString('en-US', { weekday: 'short' });
        const dayDate = date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });

        const card = document.createElement('div');
        card.className = 'bg-white/20 backdrop-blur-md rounded-2xl p-4 border border-white/30 text-center';
        
        card.innerHTML = `
            <p class="text-white font-semibold mb-2">${dayName}</p>
            <p class="text-white/80 text-sm mb-3">${dayDate}</p>
            <img src="https://openweathermap.org/img/wn/${forecast.weather[0].icon}.png" 
                 alt="${forecast.weather[0].description}" 
                 class="w-12 h-12 mx-auto mb-2">
            <p class="text-white font-bold text-lg">${Math.round(forecast.main.temp)}°C</p>
            <p class="text-white/80 text-sm capitalize">${forecast.weather[0].description}</p>
            <div class="mt-2 text-xs text-white/70">
                <p>H: ${Math.round(forecast.main.temp_max)}°C</p>
                <p>L: ${Math.round(forecast.main.temp_min)}°C</p>
            </div>
        `;

        return card;
    }

    showLoading() {
        document.getElementById('loading').classList.remove('hidden');
        document.getElementById('current-weather').classList.add('hidden');
        document.getElementById('forecast-section').classList.add('hidden');
        document.getElementById('error-message').classList.add('hidden');
    }

    hideLoading() {
        document.getElementById('loading').classList.add('hidden');
    }

    showError(message) {
        document.getElementById('error-text').textContent = message;
        document.getElementById('error-message').classList.remove('hidden');
        document.getElementById('current-weather').classList.add('hidden');
        document.getElementById('forecast-section').classList.add('hidden');
    }
}

// Initialize the weather dashboard when the page loads
document.addEventListener('DOMContentLoaded', () => {
    new WeatherDashboard();
});