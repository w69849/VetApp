package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.AppointmentService;
import dev.vetapp.services.ClientService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SimpleAnimalsController {
    AnimalService animalService;
    AppointmentService appointmentService;

    @FXML private TableView<AnimalModel> animalsTable;
    @FXML private TableColumn<AnimalModel, Integer> idColumn;
    @FXML private TableColumn<AnimalModel, String> animalNameColumn;
    @FXML private TableColumn<AnimalModel, String> ownerColumn;
    @FXML private TableColumn<AnimalModel, String> speciesColumn;
    @FXML private TableColumn<AnimalModel, String> breedColumn;
    @FXML private TableColumn<AnimalModel, LocalDate> ageColumn;
    @FXML private TableColumn<AnimalModel, String> genderColumn;
    @FXML private TableColumn<AnimalModel, String> weightColumn;

    @FXML
    private void initialize() {
        animalService = new AnimalService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        animalNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        ownerColumn.setCellValueFactory(cellData -> {
            var owner = cellData.getValue().getOwner();

            if(owner.getName() == null)
                return new SimpleStringProperty("--Usunięty--");
            else
                return new SimpleStringProperty(owner.toString());
        });

        animalsTable.setItems(animalService.getAnimals());

        animalsTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    if(newSelection != null) {
                        appointmentService.setSelectedAnimal(newSelection);

                        Stage stage = (Stage) animalsTable.getScene().getWindow();
                        stage.close();
                    }
                });
    }

    public void setAppointmentService(AppointmentService service) {
        appointmentService = service;

//        if(appointmentService == null)
//            return;
//
//        if(appointmentService.getAnimalsList().isEmpty())
//            animalsTable.setItems(animalService.getAnimals());
//        else
//            animalsTable.setItems(animalService.getAnimalsList());
    }
}
