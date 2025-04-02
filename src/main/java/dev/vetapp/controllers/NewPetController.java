package dev.vetapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class NewPetController {
    @FXML
    private ComboBox speciesComboBox;
    @FXML
    private ComboBox breedComboBox;
    @FXML
    private ComboBox genderComboBox;
    @FXML
    private ResourceBundle resources;
    @FXML
    private Spinner weightSpinner;

    @FXML
    private void initialize(){
        genderComboBox.getItems().addAll(resources.getString("male"), resources.getString("female"));

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 200, 5, 0.1);
        weightSpinner.setValueFactory(valueFactory);
    }
}
