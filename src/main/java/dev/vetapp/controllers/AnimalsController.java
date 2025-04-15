package dev.vetapp.controllers;

import dev.vetapp.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class AnimalsController {

    @FXML
    private void createNewAnimalWindow(){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("dev.vetapp.languages.messages");

            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/dev/vetapp/fxml/NewAnimalModal.fxml"));
            fxmlLoader.setResources(bundle);
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch (IOException e){
            e.printStackTrace();
            e.getMessage();
        }

    }
}
