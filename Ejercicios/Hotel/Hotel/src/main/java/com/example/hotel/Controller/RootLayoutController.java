package com.example.hotel.Controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {

    private MainApp mainApp= new MainApp();

    /**
     * Opens the birthday statistics.
     */
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
}