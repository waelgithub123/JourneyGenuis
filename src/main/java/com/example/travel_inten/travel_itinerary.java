
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