package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.AppointmentModel;
import dev.vetapp.services.AppointmentService;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewAppointmentController {
    @FXML private Button saveButton;
    @FXML private Label animalText;
    @FXML private Label ownerText;
    @FXML private TextField subjectTextField;
    @FXML private Label subjectErrorLabel;
    @FXML private Label animalErrorLabel;

    private AppointmentService appointmentService;

    @FXML private void initialize() {
        //appointmentService = new AppointmentService();

//        animalText.textProperty().bind(appointmentService.getSelectedAnimal().getNameProperty());
//        ownerText.textProperty().bind(
//                Bindings.concat(appointmentService.getSelectedAnimal().getOwner().getNameProperty(),
//                        " ",
//                        appointmentService.getSelectedAnimal().getOwner().getSurnameProperty()));
    }

    public void setAppointmentService(AppointmentService service) {
        appointmentService = service;

        appointmentService.getSelectedAnimalProperty()
                .addListener((obs, oldClient, newClient) -> {
                    if(newClient != null) {
                        fillFormWithSelection(newClient);
                    }
                });
    }

    @FXML private void saveAppointment() {
        if(!validateForm())
            return;

        AppointmentModel appointment = new AppointmentModel();

        appointment.setAnimal(appointmentService.getSelectedAnimal());
        appointment.setDate(appointmentService.getSelectedDateTime());
        appointment.setSubject(subjectTextField.getText());

        appointmentService.SaveAppointment(appointment);

        Stage stage = (Stage) animalText.getScene().getWindow();
        stage.close();
    }

    @FXML private void showAnimalList() {
        try{
            SimpleAnimalsController controller = FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.SimpleAnimalsModal).getController();
            controller.setAppointmentService(appointmentService);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void fillFormWithSelection(AnimalModel animalModel) {
        animalText.setText(animalModel.getName());
        ownerText.setText(animalModel.getOwner().getName() + ' ' + animalModel.getOwner().getSurname());
    }

    private boolean validateForm() {
        boolean valid = true;

        if(subjectTextField.getText().isEmpty()) {
            valid = false;
            subjectErrorLabel.setText("To pole nie może być puste");
            subjectErrorLabel.setVisible(true);
        }
        else
            subjectErrorLabel.setVisible(false);

        if(appointmentService.getSelectedAnimal() == null) {
            valid = false;
            animalErrorLabel.setText("Musisz wybrać zwierzę");
            animalErrorLabel.setVisible(true);
        }
        else
            animalErrorLabel.setVisible(false);

        return valid;
    }
}
