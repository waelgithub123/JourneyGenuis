package com.example.travel_inten;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VisaRquirementsUser {

    private TextField startCountryCodeInput;
    private TextField destinationCountryCodeInput;
    private Label visaRequirementsLabel;

    private static final String API_URL = "https://rough-sun-2523.fly.dev/api/";

    public VisaRquirementsUser(TextField startCountryCodeInput, TextField destinationCountryCodeInput, Label visaRequirementsLabel) {
        this.startCountryCodeInput = startCountryCodeInput;
        this.destinationCountryCodeInput = destinationCountryCodeInput;
        this.visaRequirementsLabel = visaRequirementsLabel;
    }

    public void fetchAndDisplayVisaRequirements() {
        String startCountryCode = startCountryCodeInput.getText().trim();
        String destinationCountryCode = destinationCountryCodeInput.getText().trim();

        if (!startCountryCode.isEmpty() && !destinationCountryCode.isEmpty()) {
            try {
                String apiUrl = API_URL + startCountryCode + "/" + destinationCountryCode;
                String visaRequirements = getApiResponse(apiUrl);

                // Display visa requirements in the label
                visaRequirementsLabel.setText(visaRequirements);

            } catch (IOException e) {
                // Handle exceptions if needed
                e.printStackTrace();
                visaRequirementsLabel.setText("Error fetching visa requirements.");
            }
        }
    }

    private String getApiResponse(String apiUrl) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        }

        return result.toString();
    }
}
