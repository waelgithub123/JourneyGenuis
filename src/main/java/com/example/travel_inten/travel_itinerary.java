//
//package com.example.travel_inten;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.scene.layout.Pane;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.geometry.Pos;
//
//
//public class travel_itinerary extends Application {
//    Label budgetLabel = new Label("Budget: $0.00");
//    private void updateBudgetPanel(double budgetAmount) {
//        // Update the budget panel text
//        budgetLabel.setText("Budget: $" + String.format("%.2f", budgetAmount));
//    }
//
//    private void openBudgetWindow() {
//        Stage budgetStage = new Stage();
//
//        // Create a VBox to hold the input field and label
//        VBox vbox = new VBox();
//        vbox.setAlignment(Pos.CENTER);
//
//        // Create a label
//        Label label = new Label("Enter Budget Amount:");
//
//        // Create a TextField for input
//        TextField textField = new TextField();
//
//        // Create a button to update the budget panel
//        Button updateButton = new Button("Update Budget");
//        updateButton.setOnAction(event -> {
//            // Get the input text from the TextField
//            String inputText = textField.getText();
//
//            // Check if the input is a valid number
//            try {
//                double budgetAmount = Double.parseDouble(inputText);
//
//                // Update the budget panel with the entered amount
//                updateBudgetPanel(budgetAmount);
//
//                // Close the budget window
//                budgetStage.close();
//            } catch (NumberFormatException e) {
//                // Handle invalid input (e.g., not a valid number)
//                // You can display an error message here if needed
//            }
//        });
//
//        // Add the label, TextField, and button to the VBox
//        vbox.getChildren().addAll(label, textField, updateButton);
//
//        Scene budgetScene = new Scene(vbox, 300, 200);
//        budgetStage.setTitle("Budget Panel Window");
//        budgetStage.setScene(budgetScene);
//        budgetStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.initStyle(StageStyle.TRANSPARENT); // Set the window style to transparent
//
//        StackPane root = new StackPane();
//        Pane container = new Pane(); // Use Pane for freeform positioning
//
//        Rectangle background = new Rectangle(1344, 756);
//        background.setFill(Color.BLACK); // Set the background color to black
//
//        // Set the corner radius to make the window's corners rounded
//        double cornerRadius = 50;
//        background.setArcWidth(cornerRadius);
//        background.setArcHeight(cornerRadius);
//
//        Rectangle  budget_panel = new Rectangle(220, 320);
//        budget_panel.setFill(Color.GRAY); // Set the gray panel's color to gray
//
//        // Set the corner radius for the gray panel
//        budget_panel.setArcWidth(cornerRadius);
//        budget_panel.setArcHeight(cornerRadius);
//
//        // Set the position of the gray panel
//        budget_panel.setLayoutX(80);
//        budget_panel.setLayoutY(60);
//
//
//        budgetLabel.setLayoutX(100); // Adjust the position as needed
//        budgetLabel.setLayoutY(400); // Adjust the position as needed
//        container.getChildren().add(budgetLabel);
//
//        budget_panel.setOnMouseClicked(event -> openBudgetWindow());
//
//        Rectangle flightPanel = new Rectangle(950, 131);
//        flightPanel.setFill(Color.GRAY);
//
//        // Set the corner radius for the flight panel
//        flightPanel.setArcWidth(cornerRadius);
//        flightPanel.setArcHeight(cornerRadius);
//
//        // Set the position of the flight panel
//        flightPanel.setLayoutX(350);
//        flightPanel.setLayoutY(70);
//
//        Rectangle flightPanel2 = new Rectangle(950, 131);
//        flightPanel2.setFill(Color.GRAY); // Corrected line
//
//// Set the corner radius for the flight panel
//        flightPanel2.setArcWidth(cornerRadius);
//        flightPanel2.setArcHeight(cornerRadius);
//
//// Set the position of the flight panel
//        flightPanel2.setLayoutX(350);
//        flightPanel2.setLayoutY(250);
//
//        Rectangle travel_adivsoryPanel = new Rectangle(430, 230);
//        travel_adivsoryPanel.setFill(Color.GRAY);
//
//        travel_adivsoryPanel.setArcWidth(cornerRadius);
//        travel_adivsoryPanel.setArcHeight(cornerRadius);
//
//        travel_adivsoryPanel.setLayoutX(350);
//        travel_adivsoryPanel.setLayoutY(450);
//
//        // Create the hotel panel
//        Rectangle hotelPanel = new Rectangle(430, 230);
//        hotelPanel.setFill(Color.GRAY);
//
//        hotelPanel.setArcWidth(cornerRadius);
//        hotelPanel.setArcHeight(cornerRadius);
//
//        hotelPanel.setLayoutX(850);
//        hotelPanel.setLayoutY(450);
//
//        Rectangle  weather_panel = new Rectangle(220, 230);
//        weather_panel.setFill(Color.GRAY); // Set the gray panel's color to gray
//
//        // Set the corner radius for the gray panel
//        weather_panel.setArcWidth(cornerRadius);
//        weather_panel.setArcHeight(cornerRadius);
//
//        // Set the position of the gray panel
//
//        weather_panel.setLayoutX(80);
//        weather_panel.setLayoutY(450);
//
//        container.getChildren().addAll(background, budget_panel, flightPanel, flightPanel2, travel_adivsoryPanel, hotelPanel, weather_panel);
//        root.getChildren().addAll(container);
//
//        Scene scene = new Scene(root, 1344, 756);
//        scene.setFill(Color.TRANSPARENT); // Make the scene background transparent
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}

package com.example.travel_inten;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class travel_itinerary extends Application {

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/travel_itinerary.fxml"));
        Parent root = loader.load();

        TravelItineraryController controller = loader.getController();

        Scene scene = new Scene(root, 1344, 756);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}