package com.example.travel_inten;

import javafx.scene.control.TextField;

public class VisaUser {

    private VisaRequirementsAPI visaRequirementsAPI;
    private TextField passportCountryInput;
    private TextField destinationCountryInput;

    public VisaUser(VisaRequirementsAPI visaRequirementsAPI, TextField passportCountryInput, TextField destinationCountryInput) {
        this.visaRequirementsAPI = visaRequirementsAPI;
        this.passportCountryInput = passportCountryInput;
        this.destinationCountryInput = destinationCountryInput;
    }

    public void handleVisaRequirements() {
        String passportCountryCode = passportCountryInput.getText().trim();
        String destinationCountryCode = destinationCountryInput.getText().trim();

        if (!passportCountryCode.isEmpty() && !destinationCountryCode.isEmpty()) {
            try {
                // Call the VisaRequirementsAPI method to fetch visa requirements
                String visaInfo = visaRequirementsAPI.fetchVisaRequirements(passportCountryCode, destinationCountryCode);

                // Display visa information in the weather_info_panel or any appropriate panel
                TravelItineraryController.setVisaInformation(visaInfo); // Assuming a method in Controller to set visa information
            } catch (Exception e) {
                // Handle exceptions or display an error message
                TravelItineraryController.displayErrorMessage("Error fetching visa requirements.");
            }
        }
    }

}
