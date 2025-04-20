package dev.vetapp.controllers;

import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.AnimalTypeModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.AnimalTypeService;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ResourceBundle;

public class NewAnimalController {
    @FXML private ResourceBundle resources;

    @FXML private String animalNameTextField;
    @FXML private ComboBox<String> speciesComboBox;
    @FXML private ComboBox<String> breedComboBox;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private DatePicker birthDatePicker;
    @FXML private Spinner<Double> weightSpinner;
    @FXML private TextArea notesTextArea;

    @FXML private TextField ownerNameTextField;
    @FXML private TextField ownerSurnameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cityTextField;

    private AnimalTypeService animalTypeService;
    private AnimalService animalService;
    private ClientService clientService;

    private ClientModel clientModel;

    @FXML private void initialize(){
        animalTypeService = new AnimalTypeService();
        animalService = new AnimalService();
        clientService = new ClientService();

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

    @FXML private void onSaveButtonAction(){
        AnimalTypeModel animalType = animalTypeService.getAnimalType(
                speciesComboBox.getSelectionModel().getSelectedItem(),
                breedComboBox.getSelectionModel().getSelectedItem());

        if(clientModel == null) {
            clientModel = new ClientModel();
            clientModel.setCity(cityTextField.getText());
            clientModel.setAddress(addressTextField.getText());
            clientModel.setEmail(emailTextField.getText());
            clientModel.setSurname(ownerSurnameTextField.getText());
            clientModel.setPhoneNumber(phoneNumberTextField.getText());
            clientModel.setZipCode(zipCodeTextField.getText());
            clientModel.setName(ownerNameTextField.getText());

            clientService.saveClient(clientModel);
        }

        AnimalModel animal = new AnimalModel();
        animal.setAnimalType(animalType);
        animal.setName(animalNameTextField);
        animal.setGender(genderComboBox.getSelectionModel().getSelectedItem());
        animal.setOwner(clientModel);
        animal.setNotes(notesTextArea.toString());
        animal.setWeight(weightSpinner.getValue());
        animal.setBirthDate(birthDatePicker.getValue());

        animalService.SaveAnimal(animal);
    }

    @FXML private void onChooseButtonAction(){

    }
}
