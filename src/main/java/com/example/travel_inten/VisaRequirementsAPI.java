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

public class VisaRequirementsAPI extends Application {

    private static final String API_URL = "https://rough-sun-2523.fly.dev/api/";
    private TextField passportCountryInput;
    private TextField destinationCountryInput;
    private Label visaRequirementsLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visa Requirements App");
        VBox root = new VBox(10);

        passportCountryInput = new TextField();
        destinationCountryInput = new TextField();
        Button getVisaRequirementsButton = new Button("Get Visa Requirements");
        getVisaRequirementsButton.setOnAction(event -> getVisaRequirements());

        visaRequirementsLabel = new Label();

        root.getChildren().addAll(passportCountryInput, destinationCountryInput, getVisaRequirementsButton, visaRequirementsLabel);

        Scene scene = new Scene(root, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void getVisaRequirements() {
        String passportCountryCode = passportCountryInput.getText().trim();
        String destinationCountryCode = destinationCountryInput.getText().trim();

        if (!passportCountryCode.isEmpty() && !destinationCountryCode.isEmpty()) {
            try {
                String apiUrl = API_URL + passportCountryCode + "/" + destinationCountryCode;

                // Make an API call to fetch visa requirements
                String jsonResponse = getApiResponse(apiUrl);

                // Parse the JSON response
                JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);

                // Extract and display visa requirements
                visaRequirementsLabel.setText(jsonObject.toString());
            } catch (IOException e) {
                visaRequirementsLabel.setText("Error fetching visa requirements.");
            }
        }
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
    public String fetchVisaRequirements(String passportCountryCode, String destinationCountryCode) throws IOException {
        String apiUrl = API_URL + passportCountryCode + "/" + destinationCountryCode;
        String jsonResponse = getApiResponse(apiUrl);
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonResponse);
        return jsonObject.toString();
    }
}
