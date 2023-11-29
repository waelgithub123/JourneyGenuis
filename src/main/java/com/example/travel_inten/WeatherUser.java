package com.example.travel_inten;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherUser {

    private static final String API_KEY = "6586aeda5c8bb557273ca8c95e213ce9";
    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/weather?";


        private Pane displayPane; // Declare the displayPane

        // Constructor or Setter method to initialize the displayPane
        public WeatherUser(Pane displayPane) {
            this.displayPane = displayPane;
        }
    public void fetchAndDisplayWeather(String cityName, Pane displayPane) {
        if (!cityName.isEmpty() && displayPane != null) {
            try {
                String apiUrl = BASE_API_URL + "q=" + cityName + "&units=imperial" + "&appid=" + API_KEY; // Set units to "imperial" for Fahrenheit

                // Make an API call to OpenWeatherMap
                String jsonResponse = getWeatherData(apiUrl);

                // Parse the JSON response
                JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
                JSONObject main = (JSONObject) jsonObject.get("main");

                cityName = jsonObject.get("name").toString(); // Get the city name
                double temperature = Double.parseDouble(main.get("temp").toString());
                JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
                JSONObject weather = (JSONObject) weatherArray.get(0);
                String weatherDescription = weather.get("description").toString();

                // Display the weather information
                Label locationLabel = new Label("Location: " + cityName);
                Label temperatureLabel = new Label("Temperature: " + temperature + "°F"); // Display temperature in Fahrenheit
                Label weatherLabel = new Label("Weather: " + weatherDescription);



                Platform.runLater(() -> {
                    displayPane.getChildren().clear();
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
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            return result.toString();
        }
    }
