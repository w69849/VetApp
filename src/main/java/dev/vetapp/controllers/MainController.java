package dev.vetapp.controllers;

import dev.vetapp.Dialog;
import dev.vetapp.FxmlManager;
import dev.vetapp.GlobalConfig;
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

    @FXML private void initialize() throws IOException {
        setClientsView();

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

    @FXML private void setClientsView() throws IOException {
        //if(clientsView == null)
        clientsView = FxmlManager.loadFxml(FxmlManager.FxmlFile.ClientsView).load();
        contentArea.setCenter(clientsView);
    }

    @FXML private void setVisitsView() throws IOException {
        Parent visitsView = FxmlManager.loadFxml(FxmlManager.FxmlFile.AppointmentsView).load();
        contentArea.setCenter(visitsView);
    }

    @FXML private void setAnimalsView() throws IOException {
        //if(clientsView == null)
        Parent animalsView = FxmlManager.loadFxml(FxmlManager.FxmlFile.AnimalsView).load();
        contentArea.setCenter(animalsView);
    }
}