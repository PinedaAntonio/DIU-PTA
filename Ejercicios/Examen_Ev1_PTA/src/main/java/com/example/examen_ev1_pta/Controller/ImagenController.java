package com.example.examen_ev1_pta.Controller;

import com.example.examen_ev1_pta.Modelo.ConversorModelo;
import javafx.stage.Stage;

public class ImagenController {

    private Stage dialogStage;
    private boolean okClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    // Indica si el usuario ha confirmado la acción.
    public boolean OkClicked() {
        dialogStage.close();
        return okClicked;
    }

    
}
