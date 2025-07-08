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