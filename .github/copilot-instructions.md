# Weather Dashboard - Copilot Instructions

## Project Overview
This is a Weather Dashboard application built with Java Spring Boot, OpenWeatherMap API, and Tailwind CSS. It provides real-time weather information and 7-day forecasts for cities worldwide.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.2.0, Spring WebFlux
- **Frontend**: Thymeleaf, Tailwind CSS, Vanilla JavaScript
- **API**: OpenWeatherMap API
- **Build Tool**: Maven with wrapper
- **Testing**: JUnit 5

## Development Guidelines

### Running the Application
- Use the VS Code task "Spring Boot: Run Application" or run `.\mvnw.cmd spring-boot:run`
- Application runs on http://localhost:8080
- Requires OpenWeatherMap API key in `application.properties`

### Project Structure
- Controllers in `src/main/java/com/weatherdashboard/controller/`
- Services in `src/main/java/com/weatherdashboard/service/`
- Models in `src/main/java/com/weatherdashboard/model/`
- Frontend assets in `src/main/resources/static/`
- Templates in `src/main/resources/templates/`

### Key Features
- Real-time weather data with city search
- 7-day weather forecast display
- Dark mode support with localStorage persistence
- Responsive design with Tailwind CSS
- REST API endpoints for programmatic access

### Development Commands
- Build: `.\mvnw.cmd clean compile`
- Test: `.\mvnw.cmd test`
- Run: `.\mvnw.cmd spring-boot:run`

### API Configuration
Remember to set your OpenWeatherMap API key in `src/main/resources/application.properties`:
```
openweathermap.api.key=your_actual_api_key_here
```