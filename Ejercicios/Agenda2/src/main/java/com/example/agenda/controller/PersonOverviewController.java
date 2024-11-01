package com.example.agenda.controller;

import com.example.agenda.Modelo.AgendaModelo;
import com.example.agenda.Modelo.ExcepcionPerson;
import com.example.agenda.Modelo.PersonVO;
import com.example.agenda.Modelo.repository.PersonRepository;
import com.example.agenda.Modelo.repository.impl.PersonRepositoryImpl;
import com.example.agenda.util.DateUtil;
import com.example.agenda.util.PersonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.example.agenda.MainApp;
import com.example.agenda.Person;

import java.util.ArrayList;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;
    private PersonUtil personUtil = new PersonUtil();
    private AgendaModelo modelo;

    // Añadir instancia de repositorio
    private PersonRepository personRepository = new PersonRepositoryImpl(); // Repositorio

    public PersonOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Clear person details
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        loadPersonData(); // Cargar datos al inicio
    }

    public void setAgendaModelo(AgendaModelo modelo) {
        this.modelo = modelo;
    }

    // Método para cargar personas desde la base de datos
    private void loadPersonData() {
        try {
            ArrayList<PersonVO> personVOList = personRepository.ObtenerListaPersonas();
            ArrayList<Person> personList = personUtil.getPersons(personVOList);
            personTable.getItems().setAll(personList); // Actualiza la tabla con nuevos datos
        } catch (ExcepcionPerson e) {
            mostrarAlertaError("Error al cargar la lista de personas", e.getMessage());
        }
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Person personToDelete = personTable.getItems().get(selectedIndex);
            try {
                PersonVO personVO = personUtil.convertToPersonVO(personToDelete);
                modelo.borrarPerson(personVO); // Asumiendo que tienes un método en modelo para borrar
                loadPersonData(); // Recargar la lista después de borrar
            } catch (ExcepcionPerson e) {
                mostrarAlertaError("Error al borrar la persona", e.getMessage());
            }
        } else {
            Alert alert= new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            try {
                PersonVO personVO = personUtil.convertToPersonVO(tempPerson);
                modelo.nuevaPerson(personVO); // Asumiendo que tienes un método en modelo para crear
                loadPersonData(); // Recargar la lista después de añadir
            } catch (ExcepcionPerson e) {
                mostrarAlertaError("Error al guardar la persona", e.getMessage());
            }
        }
    }

    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                try {
                    PersonVO personVO = personUtil.convertToPersonVO(selectedPerson);
                    modelo.editarPerson(personVO); // Actualiza la persona
                    loadPersonData(); // Recargar la lista después de editar
                    showPersonDetails(selectedPerson); // Muestra los detalles actualizados
                } catch (ExcepcionPerson e) {
                    mostrarAlertaError("Error al editar la persona", e.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
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
