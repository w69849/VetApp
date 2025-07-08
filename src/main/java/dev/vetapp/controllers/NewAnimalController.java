package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.AnimalTypeService;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class NewAnimalController {
    @FXML private ResourceBundle resources;

    @FXML private Button assignOwnerButton;
    @FXML private Button removeOwnerButton;

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

    @FXML private Label animalNameErrorLabel;
    @FXML private Label birthDateErrorLabel;

    @FXML private Label ownerNameErrorLabel;
    @FXML private Label ownerSurnameErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label phoneNumberErrorLabel;
    @FXML private Label addressErrorLabel;
    @FXML private Label zipCodeErrorLabel;
    @FXML private Label cityErrorLabel;

    private AnimalTypeService animalTypeService;
    private AnimalService animalService;
    private ClientService clientService;
    private ClientModel clientModel;

    @FXML private void initialize(){
        animalTypeService = new AnimalTypeService();
        clientService = new ClientService();

        initForm();

        clientService.getSelectedClientProperty()
                .addListener((obs, oldClient, newClient) -> {
                    if(newClient != null) {
                        fillClientFormWithSelection(newClient);
                    }
        });

        removeOwnerButton.disableProperty().bind(clientService.getSelectedClientProperty().isNull());
    }

    public void setAnimalService(AnimalService service) { this.animalService = service; }

    private void initForm(){
        genderComboBox.getItems().addAll(resources.getString("male"), resources.getString("female"));
        genderComboBox.setValue(genderComboBox.getItems().get(0));

        speciesComboBox.setItems(animalTypeService.animalSpecies);

        if(!speciesComboBox.getItems().isEmpty())
            speciesComboBox.setValue(speciesComboBox.getItems().get(0));

        breedComboBox.setItems(animalTypeService.getBreeds(speciesComboBox.getSelectionModel().getSelectedItem()));
        breedComboBox.setValue(breedComboBox.getItems().get(0));

        speciesComboBox.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, newVal) -> {
                    breedComboBox.setItems(animalTypeService.getBreeds(speciesComboBox.getSelectionModel().getSelectedItem()));
                    breedComboBox.setValue(breedComboBox.getItems().get(0));
        });

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 200, 5, 0.1);
        weightSpinner.setValueFactory(valueFactory);
    }

    @FXML private void onSaveButtonAction(){
        if(!validateAnimalForm() || !validateClientForm())
            return;

        if(clientService.getSelectedClient() == null)
        {
            if(clientModel == null) {
                try{
                    clientModel = new ClientModel();
                    clientModel.setLocation(cityTextField.getText(), zipCodeTextField.getText());
                    clientModel.setAddress(addressTextField.getText());
                    clientModel.setEmail(emailTextField.getText());
                    clientModel.setSurname(ownerSurnameTextField.getText());
                    clientModel.setPhoneNumber(phoneNumberTextField.getText());
                    clientModel.setName(ownerNameTextField.getText());

                    clientService.saveClient(clientModel, false);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
        else
            clientModel = clientService.getSelectedClient();

        AnimalModel animal = new AnimalModel();
        animal.setSpecies(speciesComboBox.getSelectionModel().getSelectedItem());
        animal.setBreed(breedComboBox.getSelectionModel().getSelectedItem());
        animal.setName(animalNameTextField.getText());
        animal.setGender(genderComboBox.getSelectionModel().getSelectedItem());

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

    @FXML private void showClientsList(){
        try{
            SimpleClientsController controller = FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.SimpleClientsModal).getController();
            controller.setClientService(clientService);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void fillClientFormWithSelection(ClientModel model) {
        ownerNameTextField.setText(model.getName());
        ownerSurnameTextField.setText(model.getSurname());
        emailTextField.setText(model.getEmail());
        phoneNumberTextField.setText(model.getPhoneNumber());
        addressTextField.setText(model.getAddress());

        String[] s = model.getLocation().split(" ");

        zipCodeTextField.setText(s[0]);
        cityTextField.setText(s[1]);

        ownerSurnameTextField.setDisable(true);
        ownerNameTextField.setDisable(true);
        emailTextField.setDisable(true);
        phoneNumberTextField.setDisable(true);
        addressTextField.setDisable(true);
        zipCodeTextField.setDisable(true);
        cityTextField.setDisable(true);
    }

    @FXML private void removeOwner() {
        clientService.setSelectedClient(null);

        ownerSurnameTextField.setText("");
        ownerNameTextField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
        addressTextField.setText("");
        zipCodeTextField.setText("");
        cityTextField.setText("");

        ownerSurnameTextField.setDisable(false);
        ownerNameTextField.setDisable(false);
        emailTextField.setDisable(false);
        phoneNumberTextField.setDisable(false);
        addressTextField.setDisable(false);
        zipCodeTextField.setDisable(false);
        cityTextField.setDisable(false);
    }

    private boolean validateAnimalForm(){
        boolean valid = true;

        if(animalNameTextField.getText().isEmpty()) {
            valid = false;
            animalNameErrorLabel.setText("To pole nie może być puste");
            animalNameErrorLabel.setVisible(true);
        }
        else
            animalNameErrorLabel.setVisible(false);

        if(birthDatePicker.getValue() == null) {
            valid = false;
            birthDateErrorLabel.setText("To pole nie może być puste");
            birthDateErrorLabel.setVisible(true);
        }
        else
            birthDateErrorLabel.setVisible(false);

        return valid;
    }

    private boolean validateClientForm(){
        boolean valid = true;

        if(ownerNameErrorLabel.getText().isEmpty()) {
            valid = false;
            ownerNameErrorLabel.setText("To pole nie może być puste");
            ownerNameErrorLabel.setVisible(true);
        }
        else
            ownerSurnameErrorLabel.setVisible(false);

        if(ownerSurnameErrorLabel.getText().isEmpty()) {
            valid = false;
            ownerSurnameErrorLabel.setText("To pole nie może być puste");
            ownerSurnameErrorLabel.setVisible(true);
        }
        else
            ownerSurnameErrorLabel.setVisible(false);

        return valid;
    }
}
