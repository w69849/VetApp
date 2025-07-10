package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.AnimalTypeService;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

public class NewAnimalController {
    @FXML private ResourceBundle resources;

    @FXML private Label ownerErrorLabel;

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

    @FXML private Button addPhotoButton;
    @FXML private ImageView photo;

    private AnimalTypeService animalTypeService;
    private AnimalService animalService;
    private ClientService clientService;
    private ClientModel clientModel;
    private AnimalModel animalModel;
    private boolean updating = false;

    @FXML private void initialize(){
        updating = false;

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

        ownerSurnameTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        ownerNameTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        addressTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        phoneNumberTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        cityTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        zipCodeTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
        emailTextField.disableProperty().bind(clientService.getSelectedClientProperty().isNotNull());
    }

    public void setAnimalService(AnimalService service) {
        this.animalService = service;
        if(animalService.getEditedAnimal() != null)
            fillAnimalForm(animalService.getEditedAnimal());
    }

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
                    e.printStackTrace();
                    return;
                }
            }
        }
        else
            clientModel = clientService.getSelectedClient();

        if(animalModel == null)
            animalModel = new AnimalModel();

        animalModel.setSpecies(speciesComboBox.getSelectionModel().getSelectedItem());
        animalModel.setBreed(breedComboBox.getSelectionModel().getSelectedItem());
        animalModel.setName(animalNameTextField.getText());
        animalModel.setGender(genderComboBox.getSelectionModel().getSelectedItem());

        animalModel.setOwner(clientModel);
        animalModel.setNotes(notesTextArea.getText());
        animalModel.setWeight(weightSpinner.getValue());

//        Period period = Period.between(birthDatePicker.getValue(), LocalDate.now());
//        int ageInMonths = period.getYears() * 12 + period.getMonths();
//        animal.setAge(ageInMonths);
        animalModel.setAge(birthDatePicker.getValue());

        animalService.SaveAnimal(animalModel, updating);

        Stage stage = (Stage) animalNameTextField.getScene().getWindow();
        stage.close();
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
    }

    private void fillAnimalForm(AnimalModel model) {
        animalNameTextField.setText(model.getName());
        speciesComboBox.setValue(model.getSpecies());
        breedComboBox.setValue(model.getBreed());
        genderComboBox.setValue(model.getGender());
        //LocalDate birthDate = LocalDate.now().minusMonths(model.getAge());
        birthDatePicker.setValue(model.getAge());
        weightSpinner.getValueFactory().setValue(model.getWeight());
        notesTextArea.setText(model.getNotes());

        clientService.setSelectedClient(model.getOwner());

        animalModel = model;
        updating = true;
    }

    @FXML private void addPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz zdjęcie");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(addPhotoButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                photo.setImage(image);
            } catch (Exception e) {
                e.printStackTrace(); // or show an error dialog
            }
        }
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

        if(ownerNameTextField.getText().isEmpty()) {
            valid = false;
            ownerNameErrorLabel.setText("To pole nie może być puste");
            ownerNameErrorLabel.setVisible(true);
        }
        else
            ownerSurnameErrorLabel.setVisible(false);

        if(ownerSurnameTextField.getText().isEmpty()) {
            valid = false;
            ownerSurnameErrorLabel.setText("To pole nie może być puste");
            ownerSurnameErrorLabel.setVisible(true);
        }
        else
            ownerSurnameErrorLabel.setVisible(false);

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(emailTextField.getText().isEmpty()) {
            valid = false;
            emailErrorLabel.setText("To pole nie może być puste");
            emailErrorLabel.setVisible(true);
        } else if (!emailTextField.getText().matches(emailRegex)) {
            valid = false;
            emailErrorLabel.setText("Niepoprawny adres email");
            emailErrorLabel.setVisible(true);
        } else
            emailErrorLabel.setVisible(false);
        
        if(phoneNumberTextField.getText().isEmpty()) {
            valid = false;
            phoneNumberErrorLabel.setText("To pole nie może być puste");
            phoneNumberErrorLabel.setVisible(true);
        } else if (!phoneNumberTextField.getText().matches("^\\d{9}$")) {
            valid = false;
            phoneNumberErrorLabel.setText("Niepoprawny numer telefonu. Podaj 9 liczb");
            phoneNumberErrorLabel.setVisible(true);
        } else 
            phoneNumberErrorLabel.setVisible(false);

        if (!valid) {
            ownerErrorLabel.setText("Niepoprawne dane właściciela");
            ownerErrorLabel.setVisible(true);
        } else {
            ownerErrorLabel.setVisible(false);
        }

        return valid;
    }
}
