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


//public void fetchAndDisplayWeather(String cityName, Pane displayPane) {
//    if (!cityName.isEmpty() && displayPane != null) {
//        try {
//            String apiUrl = BASE_API_URL + "q=" + cityName + "&units=imperial" + "&appid=" + API_KEY;
//
//            // Make an API call to OpenWeatherMap
//            String jsonResponse = getWeatherData(apiUrl);
//            System.out.println("Received JSON response: " + jsonResponse);
//
//            // Parse the JSON response
//            JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
//            JSONObject main = (JSONObject) jsonObject.get("main");
//
//            String city = jsonObject.get("name").toString(); // Get the city name
//            double temperature = Double.parseDouble(main.get("temp").toString());
//            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
//            JSONObject weather = (JSONObject) weatherArray.get(0);
//            String weatherDescription = weather.get("description").toString();
//
//            // Create labels for weather information
//            Label locationLabel = new Label("Location: " + city);
//            Label temperatureLabel = new Label("Temperature: " + temperature + "°F");
//            Label weatherLabel = new Label("Weather: " + weatherDescription);
//
//            // Log weather information
//            System.out.println("Location: " + city);
//            System.out.println("Temperature: " + temperature + "°F");
//            System.out.println("Weather: " + weatherDescription);
//
//            int fontSize = 18;
//            String fontStyle = "-fx-font-size: " + fontSize + "px; -fx-font-weight: bold;";
//            locationLabel.setStyle(fontStyle);
//            temperatureLabel.setStyle(fontStyle);
//            weatherLabel.setStyle(fontStyle);
//            Platform.runLater(() -> {
//                displayPane.getChildren().clear(); // Clear any previous content
//
//                // Set layout parameters for labels
//                locationLabel.setLayoutX(355+5);
//                locationLabel.setLayoutY(10+430);
//                temperatureLabel.setLayoutX(355);
//                temperatureLabel.setLayoutY(40);
//                weatherLabel.setLayoutX(355);
//                weatherLabel.setLayoutY(70);
//
//                // Add labels to the weatherPane
//                displayPane.getChildren().addAll(locationLabel, temperatureLabel, weatherLabel);
//            });
//        } catch (IOException e) {
//            // Handle IO Exception (e.g., display an error message)
//            e.printStackTrace();
//        }
//    }
//}


    public void fetchAndDisplayWeather(String cityName, Pane displayPane) {
        if (!cityName.isEmpty() && displayPane != null) {
            try {
                String apiUrl = BASE_API_URL + "q=" + cityName + "&units=imperial" + "&appid=" + API_KEY;

                // Make an API call to OpenWeatherMap
                String jsonResponse = getWeatherData(apiUrl);
                System.out.println("Received JSON response: " + jsonResponse); // Log received JSON response

                // Parse the JSON response
                JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
                JSONObject main = (JSONObject) jsonObject.get("main");

                cityName = jsonObject.get("name").toString();
                double temperature = Double.parseDouble(main.get("temp").toString());
                JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
                JSONObject weather = (JSONObject) weatherArray.get(0);
                String weatherDescription = weather.get("description").toString();

                // Create a Pane to hold the weather information labels
                Pane weatherInfoPane = new Pane();
                weatherInfoPane.setStyle("-fx-background-color: #D3D3D3;");
                weatherInfoPane.setPrefSize(400, 150);

                Label locationLabel = new Label("Location: " + cityName);
                locationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                locationLabel.setLayoutX(450);
                locationLabel.setLayoutY(440);

                Label temperatureLabel = new Label("Temperature: " + temperature + "°F");
                temperatureLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                temperatureLabel.setLayoutX(20);
                temperatureLabel.setLayoutY(50);

                Label weatherLabel = new Label("Weather: " + weatherDescription);
                weatherLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                weatherLabel.setLayoutX(20);
                weatherLabel.setLayoutY(80);

                // Add labels to the weatherInfoPane
                weatherInfoPane.getChildren().addAll(locationLabel, temperatureLabel, weatherLabel);

                // Add weatherInfoPane to displayPane with specific layout coordinates
                displayPane.getChildren().add(weatherInfoPane);
                displayPane.setLayoutX(350);
                displayPane.setLayoutY(450);
            } catch (IOException e) {
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