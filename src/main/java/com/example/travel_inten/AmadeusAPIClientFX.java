package com.example.travel_inten;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AmadeusAPIClientFX extends Application {

    private String accessToken;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Amadeus API Client");

        // Create UI components
        Button getTokenButton = new Button("Get Access Token");
        Button searchButton = new Button("Search");
        TextField apiKeyField = new TextField();
        Label resultLabel = new Label();

        getTokenButton.setOnAction(event -> {
            // Handle access token retrieval here
            String apiKey = apiKeyField.getText();

            try {
                HttpClient httpClient = HttpClients.createDefault();

                String tokenEndpoint = "https://test.api.amadeus.com/v1/security/oauth2/token";
                HttpGet tokenRequest = new HttpGet(tokenEndpoint);
                tokenRequest.setHeader("Authorization", "Basic " + apiKey); // Use Basic Auth header

                HttpResponse tokenResponse = httpClient.execute(tokenRequest);

                if (tokenResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = tokenResponse.getEntity();
                    String tokenResponseString = EntityUtils.toString(entity);
                    // Parse and store the access token securely
                    accessToken = "your_access_token"; // Replace with the obtained access token
                    resultLabel.setText("Access token obtained.");
                } else {
                    resultLabel.setText("Access token request failed with status code: " + tokenResponse.getStatusLine().getStatusCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultLabel.setText("An error occurred while obtaining the access token.");
            }
        });

        searchButton.setOnAction(event -> {
            // Handle the API request using the obtained access token
            if (accessToken != null) {
                try {
                    HttpClient httpClient = HttpClients.createDefault();

                    String searchEndpoint = "https://test.api.amadeus.com/v2/reference-data/urls/checkin-links?airline=IB";
                    HttpGet searchRequest = new HttpGet(searchEndpoint);
                    searchRequest.setHeader("Authorization", "Bearer " + accessToken);

                    HttpResponse searchResponse = httpClient.execute(searchRequest);

                    if (searchResponse.getStatusLine().getStatusCode() == 200) {
                        HttpEntity entity = searchResponse.getEntity();
                        String searchResponseString = EntityUtils.toString(entity);
                        resultLabel.setText("Search result: " + searchResponseString);
                    } else {
                        resultLabel.setText("Search request failed with status code: " + searchResponse.getStatusLine().getStatusCode());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    resultLabel.setText("An error occurred while making the search request.");
                }
            } else {
                resultLabel.setText("Access token is not available. Obtain the access token first.");
            }
        });

        // Create the layout
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                new Label("API Key:"),
                apiKeyField,
                getTokenButton,
                searchButton,
                resultLabel
        );

        // Create the scene and set it on the stage
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
}
