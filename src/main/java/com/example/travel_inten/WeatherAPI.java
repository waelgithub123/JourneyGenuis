package com.example.travel_inten;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI extends Application {

    private static final String API_KEY = "6586aeda5c8bb557273ca8c95e213ce9";
    private static final String BASE_API_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private TextField cityInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather App");
        VBox root = new VBox(10);

        cityInput = new TextField();
        Button getWeatherButton = new Button("Get Weather");
        getWeatherButton.setOnAction(event -> getWeatherForCity());

        root.getChildren().addAll(cityInput, getWeatherButton);

        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getWeatherForCity() {
        String cityName = cityInput.getText().trim();

        if (!cityName.isEmpty()) {
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

                VBox resultBox = new VBox(10);
                resultBox.getChildren().addAll(locationLabel, temperatureLabel, weatherLabel);

                Stage resultStage = new Stage();
                resultStage.setTitle("Weather Information");
                Scene resultScene = new Scene(resultBox, 300, 150);
                resultStage.setScene(resultScene);
                resultStage.show();
            } catch (IOException e) {
                Label errorLabel = new Label("Error fetching weather data.");
                Stage errorStage = new Stage();
                VBox errorBox = new VBox(10);
                errorBox.getChildren().add(errorLabel);
                errorStage.setTitle("Error");
                Scene errorScene = new Scene(errorBox, 200, 100);
                errorStage.setScene(errorScene);
                errorStage.show();
            }
        }
    }

    public String getWeatherData(String apiUrl) throws IOException {
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