package com.example.travel_inten;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class WeatherUser {


    private static final String API_KEY = "6586aeda5c8bb557273ca8c95e213ce9";
    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/weather?";

    private static final Logger LOGGER = Logger.getLogger(WeatherUser.class.getName());

    private Pane displayPane; // Declare the displayPane

    // Constructor or Setter method to initialize the displayPane
    public WeatherUser(Pane displayPane) {
        this.displayPane = displayPane;
    }


public void fetchAndDisplayWeather(String cityName, Pane displayPane) {
    if (!cityName.isEmpty() && displayPane != null) {
        try {
            String apiUrl = BASE_API_URL + "q=" + cityName + "&units=imperial" + "&appid=" + API_KEY;

            // Make an API call to OpenWeatherMap
            String jsonResponse = getWeatherData(apiUrl);
            System.out.println("Received JSON response: " + jsonResponse);

            // Parse the JSON response
            JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
            JSONObject main = (JSONObject) jsonObject.get("main");

            String city = jsonObject.get("name").toString(); // Get the city name
            double temperature = Double.parseDouble(main.get("temp").toString());
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);
            String weatherDescription = weather.get("description").toString();

            // Create labels for weather information
            Label locationLabel = new Label("Location: " + city);
            Label temperatureLabel = new Label("Temperature: " + temperature + "°F");
            Label weatherLabel = new Label("Weather: " + weatherDescription);

            // Log weather information
            System.out.println("Location: " + city);
            System.out.println("Temperature: " + temperature + "°F");
            System.out.println("Weather: " + weatherDescription);

            int fontSize = 18;
            String fontStyle = "-fx-font-size: " + fontSize + "px; -fx-font-weight: bold;";
            locationLabel.setStyle(fontStyle);
            temperatureLabel.setStyle(fontStyle);
            weatherLabel.setStyle(fontStyle);

            locationLabel.setStyle("-fx-background-color: transparent; -fx-font-size: 20px; -fx-font-weight: bold; -fx-layout-z: 2;");
            temperatureLabel.setStyle("-fx-background-color: transparent; -fx-font-size: 20px; -fx-font-weight: bold; -fx-layout-z: 2;");
            weatherLabel.setStyle("-fx-background-color: transparent; -fx-font-size: 20px; -fx-font-weight: bold; -fx-layout-z: 2;");

            Platform.runLater(() -> {
                // Set layout parameters for labels
                locationLabel.setLayoutX(355 + 10);
                locationLabel.setLayoutY(430 + 20 + 50);
                temperatureLabel.setLayoutX(355 + 10);
                temperatureLabel.setLayoutY(40 + 430 + 20 + 50);
                weatherLabel.setLayoutX(355 + 10);
                weatherLabel.setLayoutY(70 + 430 + 20 + 50 + 10);

                // Set z-order for the labels
                locationLabel.setMouseTransparent(true);
                locationLabel.toFront();

                temperatureLabel.setMouseTransparent(true);
                temperatureLabel.toFront();

                weatherLabel.setMouseTransparent(true);
                weatherLabel.toFront();



                // Add labels to the displayPane
                displayPane.getChildren().addAll(locationLabel, temperatureLabel, weatherLabel);
            });

        } catch (IOException e) {
            // Handle IO Exception (e.g., display an error message)
            e.printStackTrace();
        }
    }
}

    private String getWeatherData(String apiUrl) throws IOException {
        StringBuilder result = new StringBuilder();

        try {
            LOGGER.info("Attempting to fetch weather data from: " + apiUrl);

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            LOGGER.info("HTTP Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            LOGGER.info("Received JSON response: " + result.toString());
        } catch (IOException e) {
            LOGGER.severe("Error fetching weather data: " + e.getMessage());
            throw e; // Rethrow the exception or handle it as needed
        }

        return result.toString();
    }
}