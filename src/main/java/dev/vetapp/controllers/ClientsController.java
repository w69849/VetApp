package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.ClientService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class ClientsController {
    ClientService clientService;

    @FXML private TableView<ClientModel> clientsTable;
    @FXML private TableColumn<ClientModel, Integer> idColumn;
    @FXML private TableColumn<ClientModel, String> clientNameColumn;
    @FXML private TableColumn<ClientModel, String> clientSurnameColumn;
    @FXML private TableColumn<ClientModel, String> emailColumn;
    @FXML private TableColumn<ClientModel, String> phoneNumberColumn;
    @FXML private TableColumn<ClientModel, String> addressColumn;
    @FXML private TableColumn<ClientModel, String> locationColumn;
    @FXML private TableColumn actionsColumn;

    @FXML
    private void initialize(){
        clientService = new ClientService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientSurnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        clientsTable.setItems(clientService.getClients());
    }

    @FXML
    private void createNewClientModal(){
        try{
            NewClientController controller = FxmlManager.loadFxmlModal(FxmlManager.fxmlFiles.NewClientModal).getController();
            controller.setClientService(clientService);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
