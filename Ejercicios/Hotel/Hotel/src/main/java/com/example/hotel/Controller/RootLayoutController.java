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
        mainApp.showOcupacionStatistics();
    }

    @FXML
    private void handleMostrarCarruselDoble() {
        mainApp.mostrarCarrusel("DOBLE");
    }

    @FXML
    private void handleMostrarCarruselSuite() {
        mainApp.mostrarCarrusel("SUITE");
    }

    @FXML
    private void handleMostrarCarruselJunior() {
        mainApp.mostrarCarrusel("JUNIOR_SUITE");
    }

    @FXML
    private void handleMostrarCarruselIndividual() {mainApp.mostrarCarrusel("DOBLE_USO_INDIVIDUAL");}

    @FXML
    private void handleWebView(){mainApp.showWebView();}
}