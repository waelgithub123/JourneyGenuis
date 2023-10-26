//package com.example.travel_inten;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class WeatherApp extends Application {
//
//    private static final String API_KEY = "YOUR_API_KEY";
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=" + API_KEY;
//`
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Weather App");
//        VBox root = new VBox(10);
//
//        try {
//            // Make an API call to OpenWeatherMap
//            String jsonResponse = getWeatherData(API_URL);
//
//            // Parse the JSON response
//            JSONObject jsonObject = new JSONObject(jsonResponse);
//            JSONObject main = jsonObject.getJSONObject("main");
//            JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
//
//            String cityName = jsonObject.getString("name");
//            double temperature = main.getDouble("temp");
//            String weatherDescription = weather.getString("description");
//
//            // Display the weather information
//            Label locationLabel = new Label("Location: " + cityName);
//            Label temperatureLabel = new Label("Temperature: " + temperature + "K");
//            Label weatherLabel = new Label("Weather: " + weatherDescription);
//
//            root.getChildren().addAll(locationLabel, temperatureLabel, weatherLabel);
//        } catch (IOException e) {
//            Label errorLabel = new Label("Error fetching weather data.");
//            root.getChildren().add(errorLabel);
//        }
//
//        Scene scene = new Scene(root, 300, 150);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private String getWeatherData(String apiUrl) throws IOException {
//        StringBuilder result = new StringBuilder();
//        URL url = new URL(apiUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            result.append(line);
//        }
//        reader.close();
//
//        return result.toString();
//    }
//}
