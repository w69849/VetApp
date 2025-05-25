package dev.vetapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class AppointmentsController {
    @FXML GridPane calendarGrid;
    StackPane[][] dayCells = new StackPane[7][8];

    @FXML private void initialize(){
        calendarGrid.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null){
                System.out.println("XDDDDD " + getClass().getResource("/dev/vetapp/fxml/styles.css").toExternalForm());
                newScene.getStylesheets().add(getClass().getResource("/dev/vetapp/fxml/styles.css").toExternalForm());
            }
        });

        for(int col = 1; col < 8; col++){
            for(int row = 1; row < 9; row++){
                StackPane cell = new StackPane();
                calendarGrid.add(cell, col, row);
                dayCells[col-1][row-1] = cell;
            }
        }

        for(Node node : calendarGrid.getChildren()){
            node.getStyleClass().add("calendarCell");
        }
    }
}
