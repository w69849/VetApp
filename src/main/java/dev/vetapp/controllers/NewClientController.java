package dev.vetapp.controllers;

import dev.vetapp.models.ClientModel;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NewClientController {
    @FXML private TextField clientNameTextField;
    @FXML private TextField clientSurnameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cityTextField;

    @FXML private Label clientNameErrorLabel;
    @FXML private Label clientSurnameErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label phoneNumberErrorLabel;
    @FXML private Label addressErrorLabel;
    @FXML private Label zipCodeErrorLabel;
    @FXML private Label cityErrorLabel;

    private ClientService clientService;
    private ClientModel clientModel;
    boolean updating = false;

    @FXML private void initialize(){
        updating = false;
    }

    public void setClientService(ClientService service){
        clientService = service;
        if(clientService.getEditedClient() != null)
            fillForm(clientService.getEditedClient());
    }

    @FXML private void onSaveButtonAction(){
        if(!validateForm())
            return;

        if(clientModel == null) {
            clientModel = new ClientModel();
        }

            clientModel.setAddress(addressTextField.getText());
            clientModel.setEmail(emailTextField.getText());
            clientModel.setSurname(clientSurnameTextField.getText());
            clientModel.setPhoneNumber(phoneNumberTextField.getText());
            clientModel.setName(clientNameTextField.getText());
            clientModel.setLocation(cityTextField.getText(), zipCodeTextField.getText());

            //if(updating)
            //    clientService.updateClient(clientModel);
            //else
        clientService.saveClient(clientModel, updating);

        Stage stage = (Stage) clientNameTextField.getScene().getWindow();
        stage.close();
    }

    private boolean validateForm(){
        boolean valid = true;

        if(clientNameTextField.getText().isEmpty()) {
            valid = false;
            clientNameErrorLabel.setText("To pole nie może być puste");
            clientNameErrorLabel.setVisible(true);
        }
        else
            clientNameErrorLabel.setVisible(false);

        if(clientSurnameTextField.getText().isEmpty()) {
            valid = false;
            clientSurnameErrorLabel.setText("To pole nie może być puste");
            clientSurnameErrorLabel.setVisible(true);
        }
        else
            clientSurnameErrorLabel.setVisible(false);

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

        if(!cityTextField.getText().isEmpty() && !cityTextField.getText().matches("[a-zA-Z]+")) {
            valid = false;
            cityErrorLabel.setText("Miejscowość może zawierać tylko znaki alfabetyczne");
            cityErrorLabel.setVisible(true);
        }
        else
            cityErrorLabel.setVisible(false);

        if(!zipCodeTextField.getText().isEmpty() && !zipCodeTextField.getText().matches("\\d+-\\d+")) {
            valid = false;
            zipCodeErrorLabel.setText("Niepoprawny kod pocztowy");
            zipCodeErrorLabel.setVisible(true);
        }
        else
            zipCodeErrorLabel.setVisible(false);

        return valid;
    }

    private void fillForm(ClientModel model) {
        clientNameTextField.setText(model.getName());
        clientSurnameTextField.setText(model.getSurname());
        addressTextField.setText(model.getAddress());
        phoneNumberTextField.setText(model.getPhoneNumber());
        emailTextField.setText(model.getEmail());
        String[] s = model.getLocation().split(" ");
        zipCodeTextField.setText(s[0]);
        cityTextField.setText(s[1]);

        clientModel = model;
        updating = true;
    }
}