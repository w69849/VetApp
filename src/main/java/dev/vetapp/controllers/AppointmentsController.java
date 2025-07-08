package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class AppointmentsController {
    @FXML private GridPane calendarGrid;
    @FXML private Label weekLabel;
    @FXML private Label mondayLabel;
    @FXML private Label tuesdayLabel;
    @FXML private Label wednesdayLabel;
    @FXML private Label thursdayLabel;
    @FXML private Label fridayLabel;
    @FXML private Label saturdayLabel;
    @FXML private Label sundayLabel;

    private StackPane[][] dayCells = new StackPane[7][8];
    private LocalDate firstDayOfWeek;

    @FXML private void initialize(){
        firstDayOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        updateCalendar(firstDayOfWeek);

        calendarGrid.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if(newScene != null){
                newScene.getStylesheets().add(getClass().getResource("/dev/vetapp/fxml/styles.css").toExternalForm());
            }
        });

        for(int col = 1; col < 8; col++){
            for(int row = 1; row < 9; row++){
                StackPane cell = new StackPane();
                calendarGrid.add(cell, col, row);
                dayCells[col-1][row-1] = cell;
                cell.getStyleClass().add("calendarCell");
                cell.setOnMouseClicked(event -> {
                    openNewAppointmentModal();
                });
            }
        }

        //for(Node node : calendarGrid.getChildren()){
            //node.getStyleClass().add("calendarCell");
        //}
    }

    @FXML private void showNextWeek() {
        firstDayOfWeek = firstDayOfWeek.plusDays(7);
        updateCalendar(firstDayOfWeek);
    }

    @FXML private void showPreviousWeek() {
        firstDayOfWeek = firstDayOfWeek.minusDays(7);
        updateCalendar(firstDayOfWeek);
    }

    private void updateCalendar(LocalDate firstDay) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String weekRange = String.format("%s - %s",
                firstDay.format(formatter),
                firstDay.plusDays(6).format(formatter));
        weekLabel.setText(weekRange);
    }

    private void openNewAppointmentModal() {
        try{
            FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.NewAppointmentModal);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
