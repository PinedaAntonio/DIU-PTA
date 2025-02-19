import { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, ListGroup, Button } from "react-bootstrap";
import AgendaList from "./components/AgendaList";
import TutorialList from "./components/TutorialList";
import AddAgenda from "./components/AddAgenda";
import contactService from "./services/http-agenda";

function App() {
  const [contacts, setContacts] = useState([]);
  const [selectedContact, setSelectedContact] = useState(null);
  const [showAddModal, setShowAddModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false); // Nuevo estado para determinar si es edición

  const fetchContacts = () => {
    contactService
      .getAllContacts()
      .then((response) => setContacts(response.data))
      .catch((error) => console.error("Error al cargar contactos:", error));
  };

  useEffect(() => {
    fetchContacts();
  }, []);

  const handleEditContact = () => {
    if (selectedContact) {
      setIsEditMode(true); // Activar el modo de edición
      setShowAddModal(true); // Abrir el modal para editar
    } else {
      alert("Por favor, selecciona un contacto para editar.");
    }
  };

  const handleNewContact = () => {
    setSelectedContact(null); // Asegúrate de que no haya contacto seleccionado
    setIsEditMode(false); // Desactivar el modo de edición
    setShowAddModal(true); // Abrir el modal para un nuevo contacto
  };

  return (
    <Container fluid className="app-container">
      <Row className="app-row">
        <Col md={4} className="agenda-list">
          <ListGroup>
            <ListGroup.Item className="header-item">
              <Row>
                <Col>
                  <h2 className="list-title">Nombre</h2>
                </Col>
                <Col>
                  <h2 className="list-title">Apellidos</h2>
                </Col>
              </Row>
            </ListGroup.Item>
            {contacts.map((contact) => (
              <ListGroup.Item
                key={contact.id}
                onClick={() => setSelectedContact(contact)}
                active={selectedContact && selectedContact.id === contact.id}
                className="contact-item"
              >
                <Row>
                  <Col className="contact-name">{contact.firstName}</Col>
                  <Col className="contact-name">{contact.lastName}</Col>
                </Row>
              </ListGroup.Item>
            ))}
          </ListGroup>
        </Col>

        <AgendaList selectedContact={selectedContact} />

        <Col md={4} className="contact-details">
          <h2>Tutoriales</h2>
          {selectedContact ? (
            <TutorialList selectedContact={selectedContact} />
          ) : (
            <p>No hay tutoriales disponibles</p>
          )}
        </Col>
      </Row>

      <div className="buttons">
        <Button
          className="btn btn-primary"
          onClick={handleNewContact} // Para añadir un nuevo contacto
        >
          Nuevo
        </Button>
        <Button
          className="btn btn-warning"
          onClick={handleEditContact} // Para editar el contacto seleccionado
        >
          Editar
        </Button>
        <Button className="btn btn-danger">Borrar</Button>
      </div>

      {/* Modal para añadir o editar contacto */}
      <AddAgenda
        show={showAddModal}
        handleClose={() => setShowAddModal(false)}
        selectedContact={selectedContact}
        refreshContacts={fetchContacts}
        isEditMode={isEditMode}
      />
    </Container>
  );
}

export default App;
