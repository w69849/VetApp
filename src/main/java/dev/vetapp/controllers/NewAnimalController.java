package dev.vetapp.controllers;

import dev.vetapp.services.AnimalTypeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ResourceBundle;

public class NewAnimalController {
    @FXML private ComboBox<String> speciesComboBox;
    @FXML private ComboBox<String> breedComboBox;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ResourceBundle resources;
    @FXML private Spinner<Double> weightSpinner;

    @FXML private TextField ownerNameTextField;
    @FXML private TextField ownerSurnameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cityTextField;

    private AnimalTypeService animalTypeService;

    @FXML private void initialize(){
        animalTypeService = new AnimalTypeService();

        initComboBoxes();

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 200, 5, 0.1);
        weightSpinner.setValueFactory(valueFactory);
    }

    private void initComboBoxes(){
        genderComboBox.getItems().addAll(resources.getString("male"), resources.getString("female"));

        speciesComboBox.setItems(animalTypeService.animalSpecies);
//        speciesComboBox.setCellFactory(_ -> new ListCell<>(){
//            @Override
//            protected void updateItem(String species, boolean empty) {
//                super.updateItem(species, empty);
//                setText((empty || species == null) ? null : species);
//            }
//        });
//
//        speciesComboBox.setButtonCell(new ListCell<>() {
//            @Override
//            protected void updateItem(String species, boolean empty) {
//                super.updateItem(species, empty);
//                setText((empty || species == null) ? null : species);
//            }
//        });

        speciesComboBox.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    breedComboBox.setDisable(newVal == null || newVal.isEmpty());
        });
    }

    @FXML private void breedComboBox_onShowing() {
        breedComboBox.setItems(animalTypeService.getBreeds(speciesComboBox.getSelectionModel().getSelectedItem()));
    }

    @FXML private void onChooseButtonClick(){

    }
}
