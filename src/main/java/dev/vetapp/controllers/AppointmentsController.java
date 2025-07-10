package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AppointmentModel;
import dev.vetapp.services.AppointmentService;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class AppointmentsController {
    @FXML private GridPane calendarGrid;
    @FXML private Label weekLabel;

    private AppointmentService appointmentService;
    private StackPane[][] dayCells = new StackPane[7][8];
    private LocalDate firstDayOfWeek;

    @FXML private void initialize(){
        appointmentService = new AppointmentService();

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

                final int finalCol = col - 1;
                final int finalRow = row - 1;

                cell.setOnMouseClicked(event -> {
                    if(!cell.getChildren().isEmpty()) {
                        return;
                    }

                    int hour = 8 + finalRow;
                    LocalDate date = firstDayOfWeek.plusDays(finalCol);
                    LocalDateTime dateTime = date.atTime(hour, 0);
                    appointmentService.setSelectedDateTime(dateTime);

                    openNewAppointmentModal();
                });
            }
        }

        //for(Node node : calendarGrid.getChildren()){
            //node.getStyleClass().add("calendarCell");
        //}

        firstDayOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        updateCalendar(firstDayOfWeek);
    }

    private void renderAppointments(ObservableList<AppointmentModel> appointmentList) {
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 8; row++) {
                StackPane cell = dayCells[col][row];
                cell.getChildren().clear();

                // Reset styles
                cell.getStyleClass().remove("bookedCell");
                if (!cell.getStyleClass().contains("calendarCell")) {
                    cell.getStyleClass().add("calendarCell");
                }

                Tooltip.uninstall(cell, null); // remove any existing tooltip
            }
        }

        // For each appointment, calculate the position and add a Label
        for (AppointmentModel appointment : appointmentList) {
            LocalDate date = appointment.getDate().toLocalDate();
            int hour = appointment.getDate().getHour(); // 0 = first hour block, 7 = last

            // Calculate the column (day of week offset from firstDayOfWeek)
            int dayOffset = (int) firstDayOfWeek.until(date).getDays();

            int startHour = 8;
            int endHour = 15;
            int rowIndex = hour - startHour;

            if (dayOffset >= 0 && dayOffset < 7 && rowIndex >= 0 && rowIndex < 8) {
                Label label = new Label(appointment.getSubject());
                label.getStyleClass().add("appointmentLabel");

                StackPane cell = dayCells[dayOffset][rowIndex];
                cell.getChildren().add(label);

                Tooltip.install(cell, new Tooltip("Time slot already booked"));
                cell.getStyleClass().remove("calendarCell"); // Remove default style if needed
                if (!cell.getStyleClass().contains("bookedCell"))
                    cell.getStyleClass().add("bookedCell");
            }
        }
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

        var appointments = appointmentService.getAppointments();

        appointments.addListener((ListChangeListener<AppointmentModel>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    renderAppointments(appointments);
                }
                if (change.wasRemoved()) {
                    renderAppointments(appointments);
                }
            }
        });

        renderAppointments(appointments);
    }

    private void openNewAppointmentModal() {
        try{
            NewAppointmentController controller = FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.NewAppointmentModal).getController();
            controller.setAppointmentService(appointmentService);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
