package dev.vetapp;

import dev.vetapp.database.DatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("dev.vetapp.languages.messages");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/MainView.fxml"));
        fxmlLoader.setResources(bundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("VetApp");
        stage.setScene(scene);
        stage.show();

//        Server server = null;
//        try {
//            server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
//            server.start();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        DatabaseConnector.initDatabase();
    }

    public static void main(String[] args) {
        launch();
    }
}