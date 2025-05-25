package dev.vetapp;

import javafx.scene.control.Alert;
import static dev.vetapp.GlobalConfig.getBundle;

public class Dialog {
    public static void showError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(getBundle().getString("error"));
        alert.setHeaderText(getBundle().getString("errorHeader"));
        alert.setContentText(message);
        alert.showAndWait();
    }
}
