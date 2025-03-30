package dev.vetapp;

import dev.vetapp.database.DatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Locale locale = Locale.getDefault();
        Locale locale = new Locale("pl", "PL");
        ResourceBundle.getBundle("messages_pl", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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