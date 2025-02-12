import { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col } from "react-bootstrap";
import AgendaList from "./components/AgendaList";
import axios from "axios";

function App() {
  const [contacts, setContacts] = useState([]);
  const [selectedContact, setSelectedContact] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8099/api/v1/Agenda")
      .then((response) => setContacts(response.data))
      .catch((error) => console.error("Error al cargar contactos:", error));
  }, []);

  return (
    <Container fluid className="app-container">
      <Row className="app-row">
        <AgendaList
          contacts={contacts}
          setSelectedContact={setSelectedContact}
          selectedContact={selectedContact}
        />
        <Col md={8} className="contact-details">
          <h2>Detalles Personales</h2>
          {selectedContact ? (
            <div>
              <p>
                <strong>Nombre:</strong> {selectedContact.firstName}
              </p>
              <p>
                <strong>Apellido:</strong> {selectedContact.lastName}
              </p>
              <p>
                <strong>Calle:</strong> {selectedContact.street}
              </p>
              <p>
                <strong>Ciudad:</strong> {selectedContact.city}
              </p>
              <p>
                <strong>Código Postal:</strong> {selectedContact.postalCode}
              </p>
              <p>
                <strong>Cumpleaños:</strong> {selectedContact.birthday}
              </p>
              <p>
                <strong>Tutoriales:</strong>{" "}
                {selectedContact.tutorials?.join(", ")}
              </p>
            </div>
          ) : (
            <p>Selecciona un contacto</p>
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
