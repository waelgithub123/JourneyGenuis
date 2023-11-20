package com.example.travel_inten;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class travel_advisory extends Application {

    private static final String API_URL = "https://www.travel-advisory.info/api?countrycode=";
    private TextField countryInput;
    private Label advisoryLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Travel Advisory App");
        VBox root = new VBox(10);

        countryInput = new TextField();
        Button getAdvisoryButton = new Button("Get Advisory");
        getAdvisoryButton.setOnAction(event -> getTravelAdvisory());

        advisoryLabel = new Label();

        root.getChildren().addAll(countryInput, getAdvisoryButton, advisoryLabel);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void getTravelAdvisory() {
        String countryCode = countryInput.getText().trim();

        if (!countryCode.isEmpty()) {
            try {
                String apiUrl = API_URL + countryCode;
                // Make an API call to the Travel Advisory API
                String jsonResponse = getApiResponse(apiUrl);

                // Parse the JSON response
                JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);

                // Extract advisory data for the specified country code
                JSONObject data = (JSONObject) jsonObject.get("data");
                if (data != null) {
                    JSONObject countryAdvisory = (JSONObject) data.get(countryCode);

                    if (countryAdvisory != null) {
                        String countryName = (String) countryAdvisory.get("name");
                        JSONObject advisoryData = (JSONObject) countryAdvisory.get("advisory");
                        double advisoryScore = (Double) advisoryData.get("score");
                        String advisoryMessage = (String) advisoryData.get("message");

                        // Display the travel advisory information
                        String advisoryText = String.format("Country: %s\nAdvisory Score: %.1f\nAdvisory Message: %s",
                                countryName, advisoryScore, advisoryMessage);

                        advisoryLabel.setText(advisoryText);
                    } else {
                        advisoryLabel.setText("Country not found or no advisory data available.");
                    }
                } else {
                    advisoryLabel.setText("Country not found or no advisory data available.");
                }
            } catch (IOException e) {
                advisoryLabel.setText("Error fetching travel advisory data.");
            }
        }
    }
    public JSONObject getTravelAdvisoryFromPanel(String countryCode) throws IOException {
        if (!countryCode.isEmpty()) {
            String apiUrl = API_URL + countryCode;
            String jsonResponse = getApiResponse(apiUrl);

            JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
            JSONObject data = (JSONObject) jsonObject.get("data");

            if (data != null) {
                JSONObject countryAdvisory = (JSONObject) data.get(countryCode);

                if (countryAdvisory != null) {
                    return countryAdvisory;
                }
            }
        }
        return null;
    }


    private String getApiResponse(String apiUrl) throws IOException {
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
