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
        DatabaseConnector.initDatabase();

        FXMLLoader fxmlLoader = FxmlManager.loadFxml(FxmlManager.fxmlFiles.MainView);

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
    }

    public static void main(String[] args) {
        launch();
    }
}