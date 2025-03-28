module dev.michal.vetapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires ormlite.core;
    requires ormlite.jdbc;
    requires java.sql;


    opens dev.michal.vetapp to javafx.fxml;
    exports dev.michal.vetapp;
}