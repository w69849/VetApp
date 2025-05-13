module dev.vetapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires ormlite.jdbc;
    requires java.sql;
    requires com.h2database;
    requires jdk.compiler;

    exports dev.vetapp;
    opens dev.vetapp to javafx.fxml;
    exports dev.vetapp.database.entities to ormlite.jdbc;
    opens dev.vetapp.database.entities to ormlite.jdbc;
    exports dev.vetapp.controllers;
    opens dev.vetapp.controllers to javafx.fxml;

    exports dev.vetapp.models;
    opens dev.vetapp.models to javafx.base;
}