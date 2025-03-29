package dev.michal.vetapp;

import dev.michal.vetapp.database.DatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("VetApp");
        stage.setScene(scene);
        stage.show();
        //System.out.println("java version: "+System.getProperty("java.version"));
        //System.out.println("javafx.version: " + System.getProperty("javafx.version"));
        DatabaseConnector.initDatabase();
    }

    public static void main(String[] args) {
        launch();
    }
}