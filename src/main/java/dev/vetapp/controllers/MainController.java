package dev.vetapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ResourceBundle;


public class MainController {
    @FXML
    private AnchorPane contentArea;

    @FXML
    protected void onHelloButtonClick(){
    }

    @FXML
    private void initialize(){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("dev.vetapp.languages.messages");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dev/vetapp/fxml/PetsView.fxml"));
            //fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("dev/vetapp/fxml/PetsView.fxml"));

            //FXMLLoader fxmlLoader = new FXMLLoader();
            //fxmlLoader.setLocation();
            fxmlLoader.setResources(bundle);
            Parent pane = fxmlLoader.load();
            contentArea.getChildren().setAll(pane);
        }
        catch (IOException e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}