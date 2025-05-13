package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane contentArea;
    @FXML private ToggleGroup navigationGroup;

    Parent clientsView;

    @FXML private void initialize(){
        try{
            Parent pane = FxmlManager.loadFxml(FxmlManager.fxmlFiles.ClientsView).load();
            contentArea.setCenter(pane);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        navigationGroup.selectedToggleProperty()
                .addListener((obs, oldToggle, newToggle) -> {
                    if(newToggle == null)
                        navigationGroup.selectToggle(oldToggle);
                    else if(oldToggle != null){
                        ToggleButton button = (ToggleButton) newToggle;
                        button.setStyle("-fx-background-color: #ff9800;");
                        button = (ToggleButton) oldToggle;
                        button.setStyle("");
                    }
                });

        ToggleButton button = (ToggleButton)navigationGroup.getSelectedToggle();
        button.setStyle("-fx-background-color: #ff9800");
    }

    @FXML private void setClientsView(){
        try{
            //if(clientsView == null)
                clientsView = FxmlManager.loadFxml(FxmlManager.fxmlFiles.ClientsView).load();
            contentArea.setCenter(clientsView);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @FXML private void setVisitsView(){

    }

    @FXML private void setAnimalsView(){
        try{
            //if(clientsView == null)
            Parent animalsView = FxmlManager.loadFxml(FxmlManager.fxmlFiles.AnimalsView).load();
            contentArea.setCenter(animalsView);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}