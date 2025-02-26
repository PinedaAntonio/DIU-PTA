import React, { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import tutorialService from "../services/http-tutorials";
import contactService from "../services/http-agenda";

const AddAgenda = ({
  show,
  handleClose,
  selectedContact,
  refreshContacts,
  isEditMode,
}) => {
  const [contactData, setContactData] = useState({
    firstName: "",
    lastName: "",
    street: "",
    city: "",
    postalCode: "",
    birthday: "",
    tutorials: [],
  });
  const [tutorials, setTutorials] = useState([]);

  // Cargar tutoriales disponibles
  useEffect(() => {
    tutorialService.getAllTutorials().then((response) => {
      setTutorials(response.data);
    });
  }, []);

  // Si estamos editando, cargar los datos del contacto seleccionado
  useEffect(() => {
    if (selectedContact && isEditMode) {
      setContactData({
        firstName: selectedContact.firstName,
        lastName: selectedContact.lastName,
        street: selectedContact.street,
        city: selectedContact.city,
        postalCode: selectedContact.postalCode,
        birthday: selectedContact.birthday,
        tutorials: selectedContact.tutorials || [],
      });
    } else {
      setContactData({
        firstName: "",
        lastName: "",
        street: "",
        city: "",
        postalCode: "",
        birthday: "",
        tutorials: [],
      });
    }
  }, [selectedContact, isEditMode]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setContactData({ ...contactData, [name]: value });
  };

  const handleTutorialsChange = (e) => {
    const { options } = e.target;
    const selectedTutorials = Array.from(options)
      .filter((option) => option.selected)
      .map((option) => option.value);
    setContactData({ ...contactData, tutorials: selectedTutorials });
  };

  const handleSubmit = () => {
    if (isEditMode) {
      const updatedContactData = {
        id: selectedContact.id,
        ...contactData,
      };
      contactService
        .editContact(selectedContact.id, updatedContactData)
        .then(() => {
          refreshContacts();
          handleClose();
        })
        .catch(
          (error) =>
            console.error("Error al editar contacto:", error) +
            alert("Valores erróneos en el contacto")
        );
    } else {
      contactService
        .addContact(contactData)
        .then(() => {
          refreshContacts();
          handleClose();
        })
        .catch(
          (error) =>
            console.error("Error al agregar contacto:", error) +
            alert("Valores erróneos en el contacto")
        );
    }
  };

  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>
          {isEditMode ? "Editar Contacto" : "Nuevo Contacto"}
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group controlId="firstName">
            <Form.Label>Nombre</Form.Label>
            <Form.Control
              type="text"
              placeholder="Nombre"
              name="firstName"
              value={contactData.firstName}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="lastName">
            <Form.Label>Apellidos</Form.Label>
            <Form.Control
              type="text"
              placeholder="Apellidos"
              name="lastName"
              value={contactData.lastName}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="street">
            <Form.Label>Calle</Form.Label>
            <Form.Control
              type="text"
              placeholder="Calle"
              name="street"
              value={contactData.street}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="city">
            <Form.Label>Ciudad</Form.Label>
            <Form.Control
              type="text"
              placeholder="Ciudad"
              name="city"
              value={contactData.city}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="postalCode">
            <Form.Label>Código Postal</Form.Label>
            <Form.Control
              type="text"
              placeholder="Código Postal"
              name="postalCode"
              value={contactData.postalCode}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="birthday">
            <Form.Label>Cumpleaños</Form.Label>
            <Form.Control
              type="date"
              name="birthday"
              value={contactData.birthday}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group controlId="tutorials">
            <Form.Label>Tutoriales</Form.Label>
            <Form.Control
              as="select"
              multiple
              name="tutorials"
              value={contactData.tutorials}
              onChange={handleTutorialsChange}
            >
              {tutorials.map((tutorial) => (
                <option key={tutorial.id} value={tutorial.id}>
                  {tutorial.title}
                </option>
              ))}
            </Form.Control>
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cerrar
        </Button>
        <Button variant="primary" onClick={handleSubmit}>
          {isEditMode ? "Actualizar" : "Agregar"}
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default AddAgenda;
