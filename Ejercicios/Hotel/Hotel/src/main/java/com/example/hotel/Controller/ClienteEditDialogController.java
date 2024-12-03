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
    public void setNewCliente(Cliente cliente) {
        this.cliente = cliente;

        nameField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellidos());
        direccionField.setText(cliente.getDireccion());
        provinciaField.setText(cliente.getProvincia());
        localidadField.setText(cliente.getLocalidad());
        dniField.setEditable(false);
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

    private boolean isDniValid(String dni) {
        // Verificar que el DNI no sea nulo y tenga 9 caracteres
        if (dni == null || dni.length() != 9) {
            return false;
        }

        // Verificar que los primeros 8 caracteres son dígitos
        String dniNumeros = dni.substring(0, 8);
        if (!dniNumeros.matches("\\d{8}")) {
            return false;
        }

        // Verificar que el noveno carácter sea una letra
        char letra = dni.charAt(8);
        if (!Character.isLetter(letra)) {
            return false;
        }

        // Comprobar que la letra es válida según el número de DNI
        int dniNumber = Integer.parseInt(dniNumeros);
        char[] letras = "TRWAGMYFPDXBNJZSQVHLCKE".toCharArray();
        int remainder = dniNumber % 23;
        char expectedLetter = letras[remainder];

        return letra == expectedLetter;
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

        String dni = dniField.getText();
        if (dni == null || dni.length() == 0 || !isDniValid(dni)) {
            errorMessage += "DNI no válido\n";
        }

        if (errorMessage.length() == 0) {
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



    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        nameField.setText(cliente.getNombre());
        apellidoField.setText(cliente.getApellidos());
        direccionField.setText(cliente.getDireccion());
        provinciaField.setText(cliente.getProvincia());
        localidadField.setText(cliente.getLocalidad());
        dniField.setEditable(true);
        dniField.setText(cliente.getDni());
    }
}
