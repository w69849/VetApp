package dev.vetapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class FxmlManager {
    public enum FxmlFile{
        MainView("fxml/MainView.fxml"),
        AnimalsView("/dev/vetapp/fxml/AnimalsView.fxml"),
        ClientsView("/dev/vetapp/fxml/ClientsView.fxml"),
        AppointmentsView("/dev/vetapp/fxml/AppointmentsView.fxml"),
        NewAnimalModal("/dev/vetapp/fxml/NewAnimalModal.fxml"),
        NewClientModal("/dev/vetapp/fxml/NewClientModal.fxml"),
        NewAppointmentModal("/dev/vetapp/fxml/NewAppointmentModal.fxml"),
        SimpleClientsModal("/dev/vetapp/fxml/SimpleClientsModal.fxml"),
        SimpleAnimalsModal("/dev/vetapp/fxml/SimpleAnimalsModal.fxml");

        private final String path;

        FxmlFile(String path){
            this.path = path;
        }

        public String getPath(){
            return path;
        }
    }

    private static String resourcePath = "dev.vetapp.languages.messages";

    public static FXMLLoader loadFxml(FxmlFile file){
        ResourceBundle bundle = ResourceBundle.getBundle(resourcePath);

        if(file.getPath() == null)
            return null;

        System.out.println("[LOG] Fxml path: " + file.getPath());

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(file.getPath()));
        fxmlLoader.setResources(bundle);

        return fxmlLoader;
    }

    public static FXMLLoader loadFxmlModal(FxmlFile file) throws IOException {
        FXMLLoader fxmlLoader = loadFxml(file);

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        return fxmlLoader;
    }

//    public class ModalResult {
//        public final FXMLLoader loader;
//        public final Stage stage;
//
//        public ModalResult(FXMLLoader loader, Stage stage) {
//            this.loader = loader;
//            this.stage = stage;
//        }
//    }
}
