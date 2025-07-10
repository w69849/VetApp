package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import dev.vetapp.services.AnimalService;
import dev.vetapp.services.ClientService;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
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
import java.time.LocalDate;

public class AnimalsController {
    AnimalService animalService;

    @FXML private TableView<AnimalModel> animalsTable;
    @FXML private TableColumn<AnimalModel, Integer> idColumn;
    @FXML private TableColumn<AnimalModel, String> animalNameColumn;
    @FXML private TableColumn<AnimalModel, String> ownerColumn;
    @FXML private TableColumn<AnimalModel, String> speciesColumn;
    @FXML private TableColumn<AnimalModel, String> breedColumn;
    @FXML private TableColumn<AnimalModel, LocalDate> ageColumn;
    @FXML private TableColumn<AnimalModel, String> genderColumn;
    @FXML private TableColumn<AnimalModel, String> weightColumn;
    @FXML private TableColumn<AnimalModel, String> notesColumn;
    @FXML private TableColumn actionsColumn;

    @FXML
    private void initialize(){
        animalService = new AnimalService();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        //ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        animalNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        ownerColumn.setCellValueFactory(cellData -> {
            var owner = cellData.getValue().getOwner();

            if(owner.getName() == null)
                return new SimpleStringProperty("--Usunięty--");
            else
                return new SimpleStringProperty(owner.toString());
        });

        Callback<TableColumn<AnimalModel, Void>, TableCell<AnimalModel, Void>> cellFactory
                = new Callback<TableColumn<AnimalModel, Void>, TableCell<AnimalModel, Void>>() {
            @Override
            public TableCell<AnimalModel, Void> call(TableColumn<AnimalModel, Void> clientModelVoidTableColumn) {
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
                            animalService.setEditedAnimal(getTableView().getItems().get(getIndex()));
                            createNewAnimalModal();
                        });

                        deleteButton.setOnAction(event -> {
                            animalService.deleteAnimal(getTableView().getItems().get(getIndex()));
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

        animalsTable.setItems(animalService.getAnimals());
    }

    @FXML
    private void createNewAnimalModal(){
        try{
            animalService.setEditedAnimal(null);
            NewAnimalController controller = FxmlManager.loadFxmlModal(FxmlManager.FxmlFile.NewAnimalModal).getController();
            controller.setAnimalService(animalService);
        }
        catch (IOException e){
            System.out.println(e.getMessage() + "NEW ANIMAL ERROR");
            e.printStackTrace();
        }
    }
}
