package com.example.travel_inten;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import org.json.simple.JSONObject;

import java.util.Optional;

public class TravelItineraryController {

    //-------------------------------
    @FXML
    private Rectangle wther_info_panel;
    @FXML
    private Pane weatherPane;
    private static final Logger logger = Logger.getLogger(TravelItineraryController.class.getName());
    private WeatherUser weatherUser;
    private Pane rootPane;


    //-------------------------------------


    @FXML
    private Rectangle budget_panel;

    @FXML
    private Pane mainPane;
    @FXML
    public Stage budgetStage;

    @FXML
    private void initialize() {
        budget_panel.setOnMouseClicked(event -> openBudgetWindow());

    }


    //-----------------------------------

    @FXML
    public Pane advisoryPane;
    public AdvisoryUser advisoryUser;

    //-----------------------
    public TravelItineraryController() {
        // Create a default Pane object (you may adjust this according to your requirements)
        Pane defaultPane = new Pane();
        this.weatherUser = new WeatherUser(defaultPane);

    }


    public TravelItineraryController(Pane displayPane) {
        this.weatherUser = new WeatherUser(displayPane);

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
        budgetLabel.setLayoutY(90 + 10 + 8);

        Label ticketPriceLabel = new Label("Airplane Ticket Price: $" + String.format("%.2f", ticketPrice));
        ticketPriceLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        ticketPriceLabel.setLayoutX(80);
        ticketPriceLabel.setLayoutY(130 + 10 + 8);

        Label hotelCostLabel = new Label("Hotel Cost: $" + String.format("%.2f", hotelCost));
        hotelCostLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        hotelCostLabel.setLayoutX(80);
        hotelCostLabel.setLayoutY(170 + 10 + 8);

        Label foodCostLabel = new Label("Food Costs: $" + String.format("%.2f", foodCost));
        foodCostLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        foodCostLabel.setLayoutX(80);
        foodCostLabel.setLayoutY(210 + 10 + 8);

        Label miscExpensesLabel = new Label("Miscellaneous Expenses: $" + String.format("%.2f", miscExpenses));
        miscExpensesLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        miscExpensesLabel.setLayoutX(80);
        miscExpensesLabel.setLayoutY(250 + 10 + 8);

        double totalCost = ticketPrice + hotelCost + foodCost + miscExpenses;

        Label totalCostLabel = new Label("Total Cost: $" + String.format("%.2f", totalCost));
        totalCostLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        totalCostLabel.setLayoutX(80);
        totalCostLabel.setLayoutY(290 + 10 + 8);

        mainPane.getChildren().addAll(budgetLabel, ticketPriceLabel, hotelCostLabel, foodCostLabel, miscExpensesLabel, totalCostLabel);

    }


    public void handleBudgetPanelClick(MouseEvent event) {
        System.out.println("Budget Panel Clicked"); // Debugging statement

        // Call your method to open the budget window
        openBudgetWindow();
    }


    //----------------------------------------------

    private void initializer() {
        weatherPane = new Pane(); // Create the weather pane
        // Customize the weather pane as needed

        // Add the weatherPane to the rootPane or another suitable container
        rootPane.getChildren().add(weatherPane);
    }

    // Other controller code
    public void handleWeatherPanelClick(javafx.scene.input.MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter City");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter city name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(cityName -> {
            logger.info("Weather panel clicked.");

            // Log statement before calling fetchAndDisplayWeather
            logger.info("Fetching weather for city: " + cityName);

            // Use weatherPane to display weather information
            weatherUser.fetchAndDisplayWeather(cityName, weatherPane);

            // Ensure weatherPane is brought to fro
        });
    }
    //----------------------------------------------

//    public void initialize(URL location, ResourceBundle resources) {
//        if (advisoryPane == null) {
//            System.out.println("advisoryPane is null");
//            logger.error("advisoryPane is null"); // Logging statement
//        } else {
//            this.advisoryUser = new AdvisoryUser(advisoryPane);
//            logger.info("AdvisoryUser initialized."); // Logging statement
//        }
//    }
//
//    @FXML
//    public void handleTravelAdvisoryPanelClick(MouseEvent event) {
//        if (advisoryPane == null) {
//            System.out.println("advisoryPane is null");
//            logger.error("advisoryPane is null"); // Logging statement
//            return;
//        }
//
//        if (advisoryUser == null) {
//            System.err.println("AdvisoryUser is not initialized properly.");
//            logger.error("AdvisoryUser is not initialized properly."); // Logging statement
//            return;
//        }
//
//        TextInputDialog dialog = new TextInputDialog();
//        dialog.setTitle("Enter Country Code");
//        dialog.setHeaderText(null);
//        dialog.setContentText("Enter country code:");
//
//        Optional<String> result = dialog.showAndWait();
//
//        result.ifPresent(countryCode -> {
//            JSONObject advisoryData = advisoryUser.getTravelAdvisoryFromPanel(countryCode);
//
//            // Handle the advisoryData - Display it in the advisoryPane or perform any other actions
//            if (advisoryData != null) {
//                // Example: Display advisory information in a TextArea
//                TextArea textArea = new TextArea(advisoryData.toJSONString());
//                advisoryPane.getChildren().add(textArea);
//                logger.info("Advisory information displayed."); // Logging statement
//            } else {
//                // Handle case when advisory data is not available
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Advisory Information");
//                alert.setHeaderText(null);
//                alert.setContentText("Advisory information not available for the entered country code.");
//                alert.showAndWait();
//                logger.warn("Advisory information not available."); // Logging statement
//            }
//        });
//    }
}
//------------------------------------------------------------

