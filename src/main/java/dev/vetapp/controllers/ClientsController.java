package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.ClientService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

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
    @FXML private TableColumn<ClientModel, Void> actionsColumn;

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

        Callback<TableColumn<ClientModel, Void>, TableCell<ClientModel, Void>> cellFactory = new Callback<TableColumn<ClientModel, Void>, TableCell<ClientModel, Void>>() {
            @Override
            public TableCell<ClientModel, Void> call(TableColumn<ClientModel, Void> clientModelVoidTableColumn) {
                return new TableCell<>() {
                    private final HBox hbox = new HBox(5);
                    private final Button editButton = new Button();
                    private final Button deleteButton = new Button();

                    {
                        Image editIcon = new Image(getClass().getResourceAsStream("/dev/vetapp/icons/edit_icon.png"));
                        ImageView editIconView = new ImageView(editIcon);
                        editIconView.setFitHeight(16);
                        editIconView.setFitWidth(16);
                        editButton.setGraphic(editIconView);

                        Image deleteIcon = new Image(getClass().getResourceAsStream("/dev/vetapp/icons/delete_icon.png"));
                        ImageView deleteIconView = new ImageView(deleteIcon);
                        deleteIconView.setFitHeight(16);
                        deleteIconView.setFitWidth(16);
                        deleteButton.setGraphic(deleteIconView);

                        editButton.setOnAction(event -> {
                            clientService.setEditedClient(getTableView().getItems().get(getIndex()));
                            createNewClientModal();
                        });

                        deleteButton.setOnAction(event -> {
                            clientService.deleteClient(getTableView().getItems().get(getIndex()));
                        });

                        hbox.getChildren().addAll(editButton, deleteButton);
                    }


                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty)
                            setGraphic(null);
                        else
                            setGraphic(hbox);
                    }
                };
            }
        };

        actionsColumn.setCellFactory(cellFactory);

        locationColumn.setCellValueFactory(cellData -> {
            var location = cellData.getValue().getLocation();

            if(location == null)
                return new SimpleStringProperty("");
            else
                return new SimpleStringProperty(location);
        });

        clientsTable.setItems(clientService.loadClients());
    }

    @FXML
    private void createNewClientModal(){
        try{
            NewClientController controller = FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.NewClientModal).getController();
            controller.setClientService(clientService);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
