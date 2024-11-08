package com.example.hotel.Controller;
import com.example.hotel.Cliente;
import com.example.hotel.MainApp;
import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.HotelModelo;
import com.example.hotel.Modelo.Repository.ClienteRepository;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import com.example.hotel.Util.ClienteUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ClienteOverviewController {
    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, String> NameColumn;
    @FXML
    private TableColumn<Cliente, String> ApellidoColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label localidadLabel;
    @FXML
    private Label provinciaLabel;
    @FXML
    private Label dniLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private ClienteUtil clienteUtil = new ClienteUtil();
    private HotelModelo modelo;

    // Añadir instancia de repositorio
    private ClienteRepository clienteRepository = new ClienteRepositoryImpl(); // Repositorio

    public ClienteOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        ApellidoColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());

        // Clear person details
        showClienteDetails(null);

        // Listen for selection changes and show the person details when changed.
        clienteTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadPersonData(); // Cargar datos al inicio
    }

    public void setHotelModelo(HotelModelo modelo) {
        this.modelo = modelo;
    }

    // Método para cargar personas desde la base de datos
    private void loadPersonData() {
        try {
            ArrayList<ClienteVO> clienteVOList = clienteRepository.ObtenerListaClientes();
            ArrayList<Cliente> clienteList = clienteUtil.getPersons(clienteVOList);
            clienteTable.getItems().setAll(clienteList); // Actualiza la tabla con nuevos datos
        } catch (ExcepcionHotel e) {
            mostrarAlertaError("Error al cargar la lista de personas", e.getMessage());
        }
    }

    private void showClienteDetails(Cliente cliente) {
        if (cliente != null) {
            nameLabel.setText(cliente.getNombre());
            apellidoLabel.setText(cliente.getApellidos());
            direccionLabel.setText(cliente.getDireccion());
            provinciaLabel.setText(cliente.getProvincia());
            localidadLabel.setText(cliente.getLocalidad());
            dniLabel.setText(cliente.getDni());
        } else {
            nameLabel.setText("");
            apellidoLabel.setText("");
            direccionLabel.setText("");
            provinciaLabel.setText("");
            localidadLabel.setText("");
            dniLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = clienteTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Cliente clienteToDelete = clienteTable.getItems().get(selectedIndex);
            try {
                ClienteVO clienteVO = clienteUtil.convertToClienteVO(clienteToDelete);
                modelo.borrarPerson(clienteVO); // Asumiendo que tienes un método en modelo para borrar
                loadPersonData(); // Recargar la lista después de borrar
            } catch (ExcepcionHotel e) {
                mostrarAlertaError("Error al borrar la persona", e.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showClienteEditDialog(tempCliente);
        if (okClicked) {
            try {
                ClienteVO clienteVO = clienteUtil.convertToClienteVO(tempCliente);
                modelo.nuevaPerson(clienteVO); // Asumiendo que tienes un método en modelo para crear
                loadPersonData(); // Recargar la lista después de añadir
            } catch (ExcepcionHotel e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
        }
    }

    @FXML
    private void handleEditPerson() {
        Cliente selectedCliente = clienteTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                try {
                    ClienteVO clienteVO = clienteUtil.convertToClienteVO(selectedCliente);
                    modelo.editarPerson(clienteVO); // Actualiza la persona
                    loadPersonData(); // Recargar la lista después de editar
                    showClienteDetails(selectedCliente); // Muestra los detalles actualizados
                } catch (ExcepcionHotel e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
