# Weather Dashboard

A modern, responsive weather dashboard built with Java Spring Boot and OpenWeatherMap API. Get real-time weather information and 7-day forecasts for any city worldwide.

## Features

- **Real-time Weather Data**: Current temperature, humidity, wind speed, and conditions
- **7-Day Forecast**: Extended weather forecast with daily summaries
- **City Search**: Search weather information for any city globally
- **Location Detection**: Automatic weather display for user's current location
- **Dark Mode**: Toggle between light and dark themes
- **Responsive Design**: Works perfectly on desktop and mobile devices
- **Modern UI**: Beautiful glass-morphism design with Tailwind CSS

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.2.0, Spring WebFlux
- **Frontend**: Thymeleaf, Tailwind CSS, Vanilla JavaScript
- **API**: OpenWeatherMap API
- **Build Tool**: Maven
- **Testing**: JUnit 5, Spring Boot Test

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- OpenWeatherMap API key (free at [openweathermap.org](https://openweathermap.org/api))

## Getting Started

### 1. Clone the Repository

```bash
git clone <repository-url>
cd weather-dashboard
```

### 2. Get OpenWeatherMap API Key

1. Visit [OpenWeatherMap](https://openweathermap.org/api)
2. Sign up for a free account
3. Generate your API key
4. Copy the API key for the next step

### 3. Configure API Key

Open `src/main/resources/application.properties` and replace `your_api_key_here` with your actual API key:

```properties
openweathermap.api.key=your_actual_api_key_here
```

### 4. Build and Run

```bash
# Build the project
mvn clean compile

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 5. Access the Dashboard

Open your web browser and navigate to `http://localhost:8080` to start using the weather dashboard.

## API Endpoints

The application provides REST API endpoints for programmatic access:

- `GET /api/weather/current?city={cityName}` - Get current weather for a city
- `GET /api/weather/forecast?city={cityName}` - Get 7-day forecast for a city
- `GET /api/weather/current/coordinates?lat={lat}&lon={lon}` - Get weather by coordinates

### Example API Usage

```bash
# Get current weather for London
curl "http://localhost:8080/api/weather/current?city=London"

# Get forecast for New York
curl "http://localhost:8080/api/weather/forecast?city=New York"
```

## Project Structure

```
weather-dashboard/
├── src/
│   ├── main/
│   │   ├── java/com/weatherdashboard/
│   │   │   ├── WeatherDashboardApplication.java  # Main application class
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java           # Web controller
│   │   │   │   └── WeatherApiController.java     # REST API controller
│   │   │   ├── model/
│   │   │   │   ├── WeatherData.java              # Weather data model
│   │   │   │   └── ForecastData.java             # Forecast data model
│   │   │   └── service/
│   │   │       └── WeatherService.java           # Weather service layer
│   │   └── resources/
│   │       ├── application.properties             # Configuration
│   │       ├── static/js/
│   │       │   └── weather.js                    # Frontend JavaScript
│   │       └── templates/
│   │           └── index.html                    # Main HTML template
│   └── test/
│       └── java/com/weatherdashboard/
│           └── service/
│               └── WeatherServiceTest.java       # Unit tests
├── pom.xml                                       # Maven configuration
└── README.md                                     # This file
```

## Features in Detail

### Weather Search
- Search for weather information by city name
- Automatic location detection using browser geolocation
- Error handling for invalid city names

### Current Weather Display
- Temperature in Celsius
- "Feels like" temperature
- Humidity percentage
- Wind speed in km/h
- Weather conditions with icons
- City name and country

### 7-Day Forecast
- Daily weather predictions
- High and low temperatures
- Weather conditions and icons
- Organized in responsive grid layout

### Dark Mode
- Toggle between light and dark themes
- Preference saved to browser localStorage
- Respects system dark mode preference

## Development

### Running Tests

```bash
mvn test
```

### Development Mode

For development with auto-reload:

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

### Building for Production

```bash
mvn clean package
java -jar target/weather-dashboard-0.0.1-SNAPSHOT.jar
```

## Configuration

Key configuration options in `application.properties`:

```properties
# OpenWeatherMap API Configuration
openweathermap.api.key=your_api_key_here
openweathermap.api.url=https://api.openweathermap.org/data/2.5

# Server Configuration
server.port=8080

# Logging Configuration
logging.level.com.weatherdashboard=DEBUG

# Thymeleaf Configuration
spring.thymeleaf.cache=false
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## Learning Objectives

This project is perfect for learning:

- **API Integration**: Working with external REST APIs
- **Spring Boot**: Modern Java web application development
- **Reactive Programming**: Using Spring WebFlux and Mono/Flux
- **Frontend Development**: JavaScript, CSS, responsive design
- **Testing**: Unit testing with JUnit and Spring Boot Test
- **Error Handling**: Graceful error handling in web applications

## License

This project is open source and available under the [MIT License](LICENSE).

## Support

If you encounter any issues or have questions:

1. Check the [Issues](../../issues) page
2. Create a new issue with detailed information
3. Contribute to discussions and help others

---

**Happy Weather Tracking! 🌤️**