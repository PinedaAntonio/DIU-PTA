package com.example.hotel.Controller;

import com.example.hotel.Cliente;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.Impl.ReservaRepositoryImpl;
import com.example.hotel.Reserva;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;


public class ReservaEditDialogController {

    @FXML
    private DatePicker llegadaField;
    @FXML
    private DatePicker salidaField;
    @FXML
    private MenuButton nHabField;
    @FXML
    private MenuButton tHabField;
    @FXML
    private CheckBox fumadorField;
    @FXML
    private MenuButton regimenField;
    @FXML
    private TextField dniField;


    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label indicador;

    private Stage dialogStage;
    private Reserva reserva;
    private boolean okClicked = false;
    private ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
    private DoubleProperty progresoNum = new SimpleDoubleProperty();
    private HotelModelo modelo = new HotelModelo();
    private Cliente cliente;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Configurar opciones para nHabField
        for (int i = 1; i <= 1; i++) {
            MenuItem item = new MenuItem(String.valueOf(i));
            item.setOnAction(event -> nHabField.setText(item.getText()));
            nHabField.getItems().add(item);
        }

        // Configurar opciones para tHabField
        String[] tiposHabitacion = {"DOBLE_USO_INDIVIDUAL", "DOBLE", "JUNIOR_SUITE", "SUITE"};
        for (String tipo : tiposHabitacion) {
            MenuItem item = new MenuItem(tipo);
            item.setOnAction(event -> tHabField.setText(item.getText()));
            tHabField.getItems().add(item);
        }

        // Configurar opciones para regimenField
        String[] regimenes = {"ALOJAMIENTO_Y_DESAYUNO", "MEDIA_PENSION", "PENSION_COMPLETA"};
        for (String regimen : regimenes) {
            MenuItem item = new MenuItem(regimen);
            item.setOnAction(event -> regimenField.setText(item.getText()));
            regimenField.getItems().add(item);
        }
    }




    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            reserva.setFecha_Llegada(llegadaField.getValue());
            reserva.setFecha_Salida(salidaField.getValue());

            reserva.setNHabitaciones(Integer.parseInt(nHabField.getText()));
            reserva.setTipo_Habitacion(tHabField.getText());
            reserva.setRegimen(regimenField.getText());

            reserva.setFumador(fumadorField.isSelected());
            reserva.setDni_Cliente(dniField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }




    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        // Verificar la fecha de llegada
        if (llegadaField.getValue() == null || llegadaField.getValue().isBefore(LocalDate.now())) {
            errorMessage += "La fecha de llegada no puede ser anterior a la fecha actual.\n";
        }

        // Verificar la fecha de salida
        if (salidaField.getValue() == null || salidaField.getValue().isBefore(llegadaField.getValue())) {
            errorMessage += "La fecha de salida no puede ser anterior a la fecha de llegada.\n";
        }

        // Verificar el número de habitaciones
        if ("Seleccione".equals(nHabField.getText())) {
            errorMessage += "Número de habitaciones no válido\n";
        }

        // Verificar el tipo de habitación
        if ("Seleccione".equals(tHabField.getText())) {
            errorMessage += "Tipo de habitación no válido\n";
        }

        // Verificar el régimen
        if ("Seleccione".equals(regimenField.getText())) {
            errorMessage += "Régimen no válido\n";
        }

        // Verificar el DNI
        if (dniField.getText() == null || dniField.getText().isEmpty()) {
            errorMessage += "DNI no válido\n";
        }

        // Si hay errores, mostrar una alerta con los mensajes
        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos inválidos");
            alert.setHeaderText("Por favor, corrija los campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }




    private void cambiarBarra(int n) {
        progresoNum.set(n / 50.0);
    }

    public IntegerProperty numProperty() {
        return new SimpleIntegerProperty((int) (progresoNum.get() * 50));
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param reserva
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        if (reserva.getFecha_Llegada() != null) {
            llegadaField.setValue(reserva.getFecha_Llegada());
        }
        if (reserva.getFecha_Salida() != null) {
            salidaField.setValue(reserva.getFecha_Salida());
        }

        nHabField.setText(String.valueOf(reserva.getNHabitaciones()));
        tHabField.setText(reserva.getTipo_Habitacion());
        regimenField.setText(reserva.getRegimen());
        fumadorField.setSelected(reserva.isFumador());
        dniField.setEditable(false);
        dniField.setText(reserva.getDni_Cliente());
    }


    public void setNewReserva(Reserva reserva, Cliente cliente) {
        this.reserva = reserva;
        this.cliente = cliente;

        llegadaField.setValue(null);
        salidaField.setValue(null);

        nHabField.setText("Seleccione");
        tHabField.setText("Seleccione");
        regimenField.setText("Seleccione");

        fumadorField.setSelected(false);
        dniField.setEditable(false);
        dniField.setText(cliente.getDni());
    }


}
