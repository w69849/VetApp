package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ResourceBundle;


public class MainController {
    @FXML private AnchorPane contentArea;

    @FXML private void initialize(){
        try{
            Parent pane = FxmlManager.loadFxml(FxmlManager.fxmlFiles.ClientsView).load();
            contentArea.getChildren().setAll(pane);
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "XDDDD" + e.getStackTrace());
        }
    }

    @FXML private void setClientsView(){
        try{
            Parent pane = FxmlManager.loadFxml(FxmlManager.fxmlFiles.ClientsView).load();
            contentArea.getChildren().setAll(pane);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML private void setVisitsView(){

    }

    @FXML private void setAnimalsView(){

    }
}