package com.example.travel_inten;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class travel_itinerary extends Application {

    private static final double PANEL_WIDTH = 1200.0 / 3;  // Width to fit the 1200px width
    private static final double PANEL_HEIGHT = 700.0 / 3;  // Height to fit the 700px height

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard Example");

        // Create a GridPane to fill the whole scene
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Create 3x3 panels with labels
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Pane panel = createClickablePanel("Panel " + (row * 3 + col + 1));
                panel.setMinSize(PANEL_WIDTH, PANEL_HEIGHT);
                panel.setMaxSize(PANEL_WIDTH, PANEL_HEIGHT);
                GridPane.setConstraints(panel, col, row);
                grid.getChildren().add(panel);
            }
        }

        Scene scene = new Scene(grid, 1200, 700); // Set the scene size to maintain aspect ratio
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createClickablePanel(String labelText) {
        // Create a simple panel with a colored background and a label
        Pane panel = new Pane();
        panel.setStyle("-fx-background-color: #645d5d;");


        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: rgba(94,86,86,0.82);");
        label.setPadding(new Insets(5));

        panel.getChildren().add(label);

        // Make the panel clickable
        panel.setOnMouseClicked(event -> {
            // Handle the click event here
            System.out.println("Clicked on " + labelText);
        });

        return panel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}



//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.StageStyle;
//
//public class travel_itinerary extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.initStyle(StageStyle.TRANSPARENT); // Set the window style to transparent
//
//        StackPane root = new StackPane();
//        Rectangle background = new Rectangle(1920, 1080);
//        background.setFill(Color.BLACK); // Set the background color to black
//
//        // Set the corner radius to make the window's corners rounded
//        background.setArcWidth(50);
//        background.setArcHeight(50);
//
//        root.getChildren().add(background);
//
//        Scene scene = new Scene(root, 1920, 1080);
//        scene.setFill(Color.TRANSPARENT); // Make the scene background transparent
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}

