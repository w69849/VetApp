module dev.michal.vetapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires ormlite.jdbc;
    requires java.sql;

    opens dev.michal.vetapp to javafx.fxml;
    exports dev.michal.vetapp;
    exports dev.michal.vetapp.database.entities to ormlite.jdbc;
    opens dev.michal.vetapp.database.entities to ormlite.jdbc;
    exports dev.michal.vetapp.controllers;
    opens dev.michal.vetapp.controllers to javafx.fxml;
}