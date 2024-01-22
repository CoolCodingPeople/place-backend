package com.nighthawk.spring_portfolio.mvc.weather;

import java.util.List;

/**
 * 
 */

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherData {
    private String name; // Location name
    private Main main; // Weather details
    private List<WeatherDescription> weather; // Weather descriptions

    
    // Getters and setters for the above fields
}
class Main {
    private double temp; // Temperature

    // Getter and setter for the temperature field
}

class WeatherDescription {
    @JsonProperty("description")
    private String description; // Description

    // Getter and setter for the description field
}
