package dev.vetapp.controllers;

import dev.vetapp.models.ClientModel;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class NewClientController {
    @FXML private TextField clientNameTextField;
    @FXML private TextField clientSurnameTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField cityTextField;

    private ClientService clientService;
    private ClientModel clientModel;

    @FXML private void initialize(){

    }

    @FXML private void onSaveButtonAction(){
        if(!validateForm())
            return;

        if(clientModel == null) {
            clientModel = new ClientModel();
            clientModel.setAddress(addressTextField.getText());
            clientModel.setEmail(emailTextField.getText());
            clientModel.setSurname(clientSurnameTextField.getText());
            clientModel.setPhoneNumber(phoneNumberTextField.getText());
            clientModel.setName(clientNameTextField.getText());
            clientModel.setLocation(cityTextField.getText(), zipCodeTextField.getText());

            clientService.saveClient(clientModel);
        }
    }

    private boolean validateForm(){
        if(clientNameTextField.getText().isEmpty())
            return false;

        if(clientSurnameTextField.getText().isEmpty())
            return false;

        if(emailTextField.getText().isEmpty())
            return false;

        if(phoneNumberTextField.getText().isEmpty())
            return false;

        if(addressTextField.getText().isEmpty())
            return false;

        if(zipCodeTextField.getText().isEmpty())
            return false;

        if(cityTextField.getText().isEmpty())
            return false;

        return true;
    }

    public void setClientService(ClientService service){
        clientService = service;
    }
}