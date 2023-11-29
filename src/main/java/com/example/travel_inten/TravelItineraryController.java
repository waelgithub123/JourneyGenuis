package com.example.travel_inten;


import javafx.scene.input.MouseEvent;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;


import java.util.Optional;

public class TravelItineraryController {
    @FXML
    private Rectangle wther_info_panel;

    private WeatherUser weatherUser;
    public TravelItineraryController(Pane displayPane) {
        this.weatherUser = new WeatherUser(displayPane);
    }


//private Pane mainPane;



    @FXML
    private Rectangle budget_panel;

    @FXML
    private Pane mainPane;

    @FXML
    private Pane weatherPane;


    @FXML
    public Stage budgetStage;

    @FXML
    private void initialize() {
        budget_panel.setOnMouseClicked(event -> openBudgetWindow());
    }


    private void openBudgetWindow() {
        if (budgetStage == null) {
            budgetStage = new Stage();

            // Create a Pane to hold the input fields and labels
            Pane pane = new Pane();

            Label budgetLabel = new Label("Enter Budget Amount:");
            budgetLabel.setLayoutX(10);
            budgetLabel.setLayoutY(10);

            TextField budgetTextField = new TextField();
            budgetTextField.setLayoutX(10);
            budgetTextField.setLayoutY(40);

            Label ticketPriceLabel = new Label("Enter Airplane Ticket Price:");
            ticketPriceLabel.setLayoutX(10);
            ticketPriceLabel.setLayoutY(70);

            TextField ticketPriceTextField = new TextField();
            ticketPriceTextField.setLayoutX(10);
            ticketPriceTextField.setLayoutY(100);

            Label hotelCostLabel = new Label("Enter Total Cost for Hotel:");
            hotelCostLabel.setLayoutX(10);
            hotelCostLabel.setLayoutY(130);

            TextField hotelCostTextField = new TextField();
            hotelCostTextField.setLayoutX(10);
            hotelCostTextField.setLayoutY(160);

            Label foodCostLabel = new Label("Enter Total Food Costs:");
            foodCostLabel.setLayoutX(10);
            foodCostLabel.setLayoutY(190);

            TextField foodCostTextField = new TextField();
            foodCostTextField.setLayoutX(10);
            foodCostTextField.setLayoutY(220);

            Label miscExpensesLabel = new Label("Enter Miscellaneous Expenses:");
            miscExpensesLabel.setLayoutX(10);
            miscExpensesLabel.setLayoutY(250);

            TextField miscExpensesTextField = new TextField();
            miscExpensesTextField.setLayoutX(10);
            miscExpensesTextField.setLayoutY(280);

            Button updateButton = new Button("Update");
            updateButton.setLayoutX(10);
            updateButton.setLayoutY(320);
            updateButton.setOnAction(event -> handleUpdate(budgetTextField, ticketPriceTextField, hotelCostTextField, foodCostTextField, miscExpensesTextField));

            pane.getChildren().addAll(budgetLabel, budgetTextField, ticketPriceLabel, ticketPriceTextField,
                    hotelCostLabel, hotelCostTextField, foodCostLabel, foodCostTextField,
                    miscExpensesLabel, miscExpensesTextField, updateButton);

            Scene budgetScene = new Scene(pane, 300, 370);
            budgetStage.setTitle("Budget Panel Window");
            budgetStage.setScene(budgetScene);
            budgetStage.show();
        }
    }




    private void handleUpdate(TextField budgetTextField, TextField ticketPriceTextField, TextField hotelCostTextField, TextField foodCostTextField, TextField miscExpensesTextField) {
        if (budgetStage != null) {
            String budgetInput = budgetTextField.getText();
            String ticketPriceInput = ticketPriceTextField.getText();
            String hotelCostInput = hotelCostTextField.getText();
            String foodCostInput = foodCostTextField.getText();
            String miscExpensesInput = miscExpensesTextField.getText();

            try {
                double budgetAmount = Double.parseDouble(budgetInput);
                double ticketPrice = Double.parseDouble(ticketPriceInput);
                double hotelCost = Double.parseDouble(hotelCostInput);
                double foodCost = Double.parseDouble(foodCostInput);
                double miscExpenses = Double.parseDouble(miscExpensesInput);

                updateBudgetPanel(budgetAmount, ticketPrice, hotelCost, foodCost, miscExpenses);
                budgetStage.close();
            } catch (NumberFormatException e) {
                // Handle invalid input (e.g., display an error message)
            }
        }
    }



    private void updateBudgetPanel(double budgetAmount, double ticketPrice, double hotelCost, double foodCost, double miscExpenses) {
        mainPane.getChildren().removeIf(node -> node instanceof Label);

        Label budgetLabel = new Label("Budget: $" + String.format("%.2f", budgetAmount));
        budgetLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        budgetLabel.setLayoutX(80);
        budgetLabel.setLayoutY(90+10+8);

        Label ticketPriceLabel = new Label("Airplane Ticket Price: $" + String.format("%.2f", ticketPrice));
        ticketPriceLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        ticketPriceLabel.setLayoutX(80);
        ticketPriceLabel.setLayoutY(130+10+8);

        Label hotelCostLabel = new Label("Hotel Cost: $" + String.format("%.2f", hotelCost));
        hotelCostLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        hotelCostLabel.setLayoutX(80);
        hotelCostLabel.setLayoutY(170+10+8);

        Label foodCostLabel = new Label("Food Costs: $" + String.format("%.2f", foodCost));
        foodCostLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        foodCostLabel.setLayoutX(80);
        foodCostLabel.setLayoutY(210 +10+8);

        Label miscExpensesLabel = new Label("Miscellaneous Expenses: $" + String.format("%.2f", miscExpenses));
        miscExpensesLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        miscExpensesLabel.setLayoutX(80);
        miscExpensesLabel.setLayoutY(250+10+8);

        double totalCost = budgetAmount + ticketPrice + hotelCost + foodCost + miscExpenses;

        Label totalCostLabel = new Label("Total Cost: $" + String.format("%.2f", totalCost));
        totalCostLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        totalCostLabel.setLayoutX(80);
        totalCostLabel.setLayoutY(290+10+8);

        mainPane.getChildren().addAll(budgetLabel, ticketPriceLabel, hotelCostLabel, foodCostLabel, miscExpensesLabel, totalCostLabel);
    }


    public void handleBudgetPanelClick() {
        // This method will handle the click event for the budget_panel Rectangle
        openBudgetWindow();
    }

    public TravelItineraryController() {
        // Create a default Pane object (you may adjust this according to your requirements)
        Pane defaultPane = new Pane();
        this.weatherUser = new WeatherUser(defaultPane);
    }

    private void initializer() {
        wther_info_panel.setOnMouseClicked(this::handleWeatherPanelClick);
    }

    public void handleWeatherPanelClick(javafx.scene.input.MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter City");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter city name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(cityName -> {
            // Use weatherPane to display weather information
            weatherUser.fetchAndDisplayWeather(cityName, weatherPane);
        });
    }
}

