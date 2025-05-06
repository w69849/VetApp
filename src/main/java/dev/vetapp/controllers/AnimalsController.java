package dev.vetapp.controllers;

import dev.vetapp.FxmlManager;
import javafx.fxml.FXML;
import java.io.IOException;

public class AnimalsController {
    @FXML
    private void createNewAnimalModal(){
        try{
            FxmlManager.loadFxmlModal(FxmlManager.fxmlFiles.NewAnimalModal);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
