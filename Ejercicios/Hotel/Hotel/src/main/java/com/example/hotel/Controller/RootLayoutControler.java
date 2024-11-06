package com.example.hotel.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootLayoutControler {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}