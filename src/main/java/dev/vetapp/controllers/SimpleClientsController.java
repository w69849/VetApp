package dev.vetapp.controllers;

import dev.vetapp.models.ClientModel;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SimpleClientsController {
    ClientService clientService;

    @FXML private TableView<ClientModel> clientsTable;
    @FXML private TableColumn<ClientModel, Integer> idColumn;
    @FXML private TableColumn<ClientModel, String> clientNameColumn;
    @FXML private TableColumn<ClientModel, String> clientSurnameColumn;
    @FXML private TableColumn<ClientModel, String> emailColumn;
    @FXML private TableColumn<ClientModel, String> phoneNumberColumn;
    @FXML private TableColumn<ClientModel, String> addressColumn;
    @FXML private TableColumn<ClientModel, String> locationColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        clientsTable.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null) {
                clientService.setSelectedClient(newSelection);

                Stage stage = (Stage) clientsTable.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void setClientService(ClientService service) {
        clientService = service;

        if(clientService == null)
            return;

        if(clientService.clientsList.isEmpty())
            clientsTable.setItems(clientService.loadClients());
        else
            clientsTable.setItems(clientService.clientsList);
    }
}
