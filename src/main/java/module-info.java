module dev.michal.vetapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.michal.vetapp to javafx.fxml;
    exports dev.michal.vetapp;
}