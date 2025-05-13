package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.ClientService;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class AnimalsController {
    AnimalService animalService;

    @FXML private TableView<AnimalModel> animalsTable;
    @FXML private TableColumn<AnimalModel, Integer> idColumn;
    @FXML private TableColumn<AnimalModel, String> animalNameColumn;
    @FXML private TableColumn<AnimalModel, String> ownerColumn;
    @FXML private TableColumn<AnimalModel, String> speciesColumn;
    @FXML private TableColumn<AnimalModel, String> breedColumn;
    @FXML private TableColumn<AnimalModel, String> ageColumn;
    @FXML private TableColumn<AnimalModel, String> genderColumn;
    @FXML private TableColumn<AnimalModel, String> weightColumn;
    @FXML private TableColumn<AnimalModel, String> notesColumn;
    @FXML private TableColumn actionsColumn;

    @FXML
    private void initialize(){
        animalService = new AnimalService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        animalNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        animalsTable.setItems(animalService.getAnimals());
    }

    @FXML
    private void createNewAnimalModal(){
        try{
            FxmlManager.loadFxmlModal(FxmlManager.fxmlFiles.NewAnimalModal);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
