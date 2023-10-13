package com.example.travel_inten;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;


public class TravelItineraryController {
    @FXML
    private Label budgetLabel;

    @FXML
    private TextField textField;

    private Stage budgetStage;

    // Reference to the main application
    private travel_itinerary mainApp;

    public void setMainApp(travel_itinerary mainApp) {
        this.mainApp = mainApp;
    }

    public void updateBudgetPanel(double budgetAmount) {
        budgetLabel.setText("Budget: $" + String.format("%.2f", budgetAmount));
    }

    public void openBudgetWindow() {
        budgetStage = new Stage();

        // Create a VBox to hold the input field and label
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // Create a label
        Label label = new Label("Enter Budget Amount:");

        // Create a TextField for input
        TextField textField = new TextField();

        // Create a button to update the budget panel
        Button updateButton = new Button("Update Budget");
        updateButton.setOnAction(event -> {
            // Get the input text from the TextField
            String inputText = textField.getText();

            // Check if the input is a valid number
            try {
                double budgetAmount = Double.parseDouble(inputText);

                // Update the budget panel with the entered amount
                updateBudgetPanel(budgetAmount);

                // Close the budget window
                budgetStage.close();
            } catch (NumberFormatException e) {
                // Handle invalid input (e.g., not a valid number)
                // You can display an error message here if needed
            }
        });

        // Add the label, TextField, and button to the VBox
        vbox.getChildren().addAll(label, textField, updateButton);

        Scene budgetScene = new Scene(vbox, 300, 200);
        budgetStage.setTitle("Budget Panel Window");
        budgetStage.setScene(budgetScene);
        budgetStage.show();
    }

    @FXML
    private void handleUpdateBudget(ActionEvent event) {
        String inputText = textField.getText();

        try {
            double budgetAmount = Double.parseDouble(inputText);
            updateBudgetPanel(budgetAmount);
            budgetStage.close();
        } catch (NumberFormatException e) {
            // Handle invalid input here
        }
    }
}