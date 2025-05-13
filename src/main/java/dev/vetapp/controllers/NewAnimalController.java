package dev.vetapp.controllers;

import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.AnimalTypeModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.AnimalTypeService;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class NewAnimalController {
    @FXML private ResourceBundle resources;

    @FXML private TextField animalNameTextField;
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
        if(!validateAnimalForm() || !validateClientForm())
            return;

        if(clientModel == null) {
            try{
                clientModel = new ClientModel();
                clientModel.setLocation(cityTextField.getText(), zipCodeTextField.getText());
                clientModel.setAddress(addressTextField.getText());
                clientModel.setEmail(emailTextField.getText());
                clientModel.setSurname(ownerSurnameTextField.getText());
                clientModel.setPhoneNumber(phoneNumberTextField.getText());
                clientModel.setName(ownerNameTextField.getText());

                clientService.saveClient(clientModel);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return;
            }
        }

        AnimalModel animal = new AnimalModel();
        animal.setSpecies(speciesComboBox.getSelectionModel().getSelectedItem());
        animal.setBreed(breedComboBox.getSelectionModel().getSelectedItem());
        animal.setName(animalNameTextField.getText());
        animal.setGender(genderComboBox.getSelectionModel().getSelectedItem());

        System.out.println("XXDDD22 " + clientModel + clientModel.getName());
        animal.setOwner(clientModel);
        animal.setNotes(notesTextArea.getText());
        animal.setWeight(weightSpinner.getValue());

        Period period = Period.between(birthDatePicker.getValue(), LocalDate.now());
        int ageInMonths = period.getYears() * 12 + period.getMonths();
        animal.setAge(ageInMonths);

        animalService.SaveAnimal(animal);
    }

    @FXML private void onCancelButtonAction(){

    }

    @FXML private void onChooseButtonAction(){

    }

    private boolean validateAnimalForm(){
        if(animalNameTextField.getText().isEmpty())
            return false;

        if(speciesComboBox.getSelectionModel().getSelectedItem().isEmpty())
            return false;

        if(breedComboBox.getSelectionModel().getSelectedItem().isEmpty())
            return false;

        if(genderComboBox.getSelectionModel().getSelectedItem().isEmpty())
            return false;

        if(weightSpinner.getValue().isNaN())
            return false;

        if(birthDatePicker.getValue().toString().isEmpty())
            return false;

        return true;
    }

    private boolean validateClientForm(){
        return true;
    }
}
