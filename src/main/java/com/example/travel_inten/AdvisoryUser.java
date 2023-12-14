package com.example.travel_inten;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AdvisoryUser {

    private Pane displayPane;
    private static final String API_URL = "https://www.travel-advisory.info/api?countrycode=";

    public AdvisoryUser(Pane displayPane) {
        this.displayPane = displayPane;
    }
    public JSONObject getTravelAdvisoryFromPanel(String countryCode) {
        if (countryCode != null && !countryCode.isEmpty()) {
            try {
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
            } catch (IOException e) {
                displayAlert("Error", "Failed to fetch travel advisory. Please try again later.");
                e.printStackTrace(); // Log the exception or handle it according to your application's logging strategy
            }
        } else {
            displayAlert("Warning", "Please provide a valid country code.");
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

    private void displayAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}