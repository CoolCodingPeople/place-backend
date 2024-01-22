package com.nighthawk.spring_portfolio.mvc.weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
public class WeatherAPIController {

    @GetMapping("/current")
    public ResponseEntity<WeatherData> getCurrentWeather() {
        try {
            // Replace with the actual latitude, longitude, and OpenWeatherMap API key
            String lat = "32.715736";
            String lon = "-117.161087";
            String apiKey = "777d2b06a33946bf47eba273e42a3b7e";

            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric";
            RestTemplate restTemplate = new RestTemplate();
            WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);

            return new ResponseEntity<>(weatherData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
