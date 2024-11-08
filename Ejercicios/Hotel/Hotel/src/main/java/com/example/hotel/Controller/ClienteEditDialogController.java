package com.example.hotel.Controller;

import com.example.hotel.Cliente;
import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.ClienteRepository;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ClienteEditDialogController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField localidadField;
    @FXML
    private TextField provinciaField;
    @FXML
    private TextField dniField;


    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label indicador;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;
    private ClienteRepository clienteRepository = new ClienteRepositoryImpl();
    private DoubleProperty progresoNum = new SimpleDoubleProperty();
    private HotelModelo modelo = new HotelModelo();

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() throws ExcepcionHotel {
        ArrayList<ClienteVO> personas = modelo.obtenerPersonas();
        cambiarBarra(personas.size());
        progressBar.progressProperty().bindBidirectional(progresoNum);
        indicador.textProperty().bind(progresoNum.multiply(100).asString("Completado: %.0f%%"));
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
     * Sets the person to be edited in the dialog.
     *
     * @param cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        nameField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellidos());
        direccionField.setText(cliente.getDireccion());
        provinciaField.setText(cliente.getProvincia());
        localidadField.setText(cliente.getLocalidad());
        dniField.setText(cliente.getDni());
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
            cliente.setNombre(nameField.getText());
            cliente.setApellidos(apellidoField.getText());
            cliente.setDireccion(direccionField.getText());
            cliente.setProvincia(provinciaField.getText());
            cliente.setLocalidad(localidadField.getText());
            cliente.setDni(dniField.getText());
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

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Nombre no válido\n";
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "Apellido no válido\n";
        }
        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "Dirección no válida\n";
        }
        if (provinciaField.getText() == null || provinciaField.getText().length() == 0) {
            errorMessage += "Provincia no válida\n";
        }
        if (localidadField.getText() == null || localidadField.getText().length() == 0) {
            errorMessage += "Localidad no válida\n";
        }
        if (dniField.getText() == null || dniField.getText().length() == 0) {
            errorMessage += "Dni no válido\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert=new Alert(Alert.AlertType.WARNING);
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
}
