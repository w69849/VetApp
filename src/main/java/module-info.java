module dev.vetapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires ormlite.jdbc;
    requires java.sql;

    opens dev.vetapp to javafx.fxml;
    exports dev.vetapp;
    exports dev.vetapp.database.entities to ormlite.jdbc;
    opens dev.vetapp.database.entities to ormlite.jdbc;
    exports dev.vetapp.controllers;
    opens dev.vetapp.controllers to javafx.fxml;
}