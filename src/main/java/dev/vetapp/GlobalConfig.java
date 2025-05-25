package dev.vetapp;

import java.util.ResourceBundle;

public class GlobalConfig {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("dev.vetapp.languages.messages");

    public static ResourceBundle getBundle(){
        return resourceBundle;
    }
}
