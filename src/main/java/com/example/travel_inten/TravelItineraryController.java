package com.example.travel_inten;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

public class TravelItineraryController {

    @FXML
    private Label budgetLabel;

    @FXML
    private TextField budgetTextField;

    private Stage budgetStage;

    @FXML
    private void openBudgetWindow() {
        if (budgetStage == null) {
            budgetStage = new Stage();

            // Create a VBox to hold the input field and label
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);

            // Create a label
            Label label = new Label("Enter Budget Amount:");

            // Create a TextField for input
            budgetTextField = new TextField();

            // Create a button to update the budget panel
            Button updateButton = new Button("Update Budget");
            updateButton.setOnAction(this::handleUpdateBudget);

            // Add the label, TextField, and button to the VBox
            vbox.getChildren().addAll(label, budgetTextField, updateButton);

            Scene budgetScene = new Scene(vbox, 300, 200);
            budgetStage.setTitle("Budget Panel Window");
            budgetStage.setScene(budgetScene);
            budgetStage.show();
        }
    }

    private void handleUpdateBudget(ActionEvent event) {
        if (budgetStage != null) {
            String inputText = budgetTextField.getText();
            try {
                double budgetAmount = Double.parseDouble(inputText);
                updateBudgetPanel(budgetAmount);
                budgetStage.close();
            } catch (NumberFormatException e) {
                // Handle invalid input (e.g., display an error message)
            }
        }
    }



    private void updateBudgetPanel(double budgetAmount) {
        budgetLabel.setText("Budget: $" + String.format("%.2f", budgetAmount));
    }
}

