package dev.vetapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class FxmlManager {
    public enum fxmlFiles{
        MainView,
        AnimalsView,
        ClientsView,
        NewAnimalModal,
        NewClientModal
    }

    private static String resourcePath = "dev.vetapp.languages.messages";

    public static FXMLLoader loadFxml(fxmlFiles file){
        ResourceBundle bundle = ResourceBundle.getBundle(resourcePath);

        String fxmlPath = getFxmlPath(file);

        if(fxmlPath == null)
            return null;

        System.out.println(fxmlPath);

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlPath));
        fxmlLoader.setResources(bundle);

        return fxmlLoader;
    }

    public static FXMLLoader loadFxmlModal(fxmlFiles file) throws IOException {
        FXMLLoader fxmlLoader = loadFxml(file);

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        return fxmlLoader;
    }

    private static String getFxmlPath(fxmlFiles file){
        switch (file){
            case fxmlFiles.MainView -> {
                return "fxml/MainView.fxml";
            }
            case fxmlFiles.AnimalsView -> {
                return "/dev/vetapp/fxml/AnimalsView.fxml";
            }
            case fxmlFiles.ClientsView -> {
                return "/dev/vetapp/fxml/ClientsView.fxml";
            }
            case fxmlFiles.NewAnimalModal -> {
                return "/dev/vetapp/fxml/NewAnimalModal.fxml";
            }
            case fxmlFiles.NewClientModal -> {
                return "/dev/vetapp/fxml/NewClientModal.fxml";
            }
            default -> { return null; }
        }
    }
}
