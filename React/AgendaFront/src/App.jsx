import { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, ListGroup } from "react-bootstrap";
import AgendaList from "./components/AgendaList";
import TutorialList from "./components/TutorialList";
import contactService from "./services/http-agenda";

function App() {
  const [contacts, setContacts] = useState([]);
  const [selectedContact, setSelectedContact] = useState(null);

  useEffect(() => {
    contactService
      .getAllContacts()
      .then((response) => setContacts(response.data))
      .catch((error) => console.error("Error al cargar contactos:", error));
  }, []);

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
        <button className="btn btn-primary">Nuevo...</button>
        <button className="btn btn-warning">Editar...</button>
        <button className="btn btn-danger">Borrar</button>
      </div>
    </Container>
  );
}

export default App;
