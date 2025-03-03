import { useState, useEffect, useContext } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Container,
  Row,
  Col,
  ListGroup,
  Button,
  Modal,
  ProgressBar,
} from "react-bootstrap";
import AgendaList from "./components/AgendaList";
import TutorialList from "./components/TutorialList";
import AddAgenda from "./components/AddAgenda";
import SignIn from "./components/SignIn";
import contactService from "./services/http-agenda";
import { UserContext } from "./providers/UserProvider";
import { auth } from "./firebase";

function App() {
  const [contacts, setContacts] = useState([]);
  const [selectedContact, setSelectedContact] = useState(null);
  const [showAddModal, setShowAddModal] = useState(false);
  const [showSignInModal, setShowSignInModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const { user } = useContext(UserContext);

  const MAX_CONTACTS = 50;

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
    if (user && selectedContact) {
      setIsEditMode(true);
      setShowAddModal(true);
      fetchContacts();
    } else {
      alert("Debes iniciar sesión y seleccionar un contacto para editar.");
    }
  };

  const handleNewContact = () => {
    if (user) {
      setSelectedContact(null);
      setIsEditMode(false);
      setShowAddModal(true);
      fetchContacts();
    } else {
      alert("Debes iniciar sesión para crear un contacto.");
    }
  };

  const handleDeleteContact = () => {
    if (user && selectedContact) {
      contactService
        .deleteContact(selectedContact.id)
        .then(() => {
          setContacts(
            contacts.filter((contact) => contact.id !== selectedContact.id)
          );
          setSelectedContact(null);
          fetchContacts();
        })
        .catch((error) => console.error("Error al borrar contacto:", error));
    } else {
      alert("Debes iniciar sesión y seleccionar un contacto para borrar.");
    }
  };

  return (
    <Container fluid className="app-container">
      <div
        className="auth-container"
        style={{ position: "absolute", top: 10, right: 10 }}
      >
        {user ? (
          <div className="d-flex align-items-center">
            <img
              src={user.photoURL}
              alt="User Avatar"
              style={{
                width: 40,
                height: 40,
                borderRadius: "50%",
                marginRight: 10,
              }}
            />
            <span>
              {user.displayName} ({user.name})
            </span>
            <Button
              onClick={() => auth.signOut()}
              variant="danger"
              size="sm"
              className="ms-2"
            >
              Cerrar sesión
            </Button>
          </div>
        ) : (
          <Button onClick={() => setShowSignInModal(true)} variant="primary">
            Iniciar sesión
          </Button>
        )}
      </div>

      <Row className="app-row">
        <Col md={4} className="agenda-list">
          <ListGroup>
            <ListGroup.Item
              className="header-item"
              style={{
                position: "sticky",
                top: 0,
                zIndex: 1,
                background: "white",
              }}
            >
              <Row>
                <Col>
                  <h2 className="list-title">Nombre</h2>
                </Col>
                <Col>
                  <h2 className="list-title">Apellidos</h2>
                </Col>
              </Row>
            </ListGroup.Item>
            <div style={{ maxHeight: "65vh", overflowY: "auto" }}>
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
            </div>
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

      {/* Row que contiene la barra de progreso y los botones */}
      <Row className="align-items-center mt-3">
        {/* Barra de progreso alineada a la izquierda */}
        <Col md={6}>
          <ProgressBar
            now={(contacts.length / MAX_CONTACTS) * 100}
            label={`${contacts.length}/${MAX_CONTACTS}`}
            variant={
              contacts.length < MAX_CONTACTS * 0.7
                ? "success"
                : contacts.length < MAX_CONTACTS * 0.9
                ? "warning"
                : "danger"
            }
          />
        </Col>

        {/* Botones alineados a la derecha */}
        <Col md={6} className="d-flex justify-content-end">
          <Button
            className="btn btn-primary me-2"
            onClick={handleNewContact}
            disabled={!user}
          >
            Nuevo
          </Button>
          <Button
            className="btn btn-warning me-2"
            onClick={handleEditContact}
            disabled={!user || !selectedContact}
          >
            Editar
          </Button>
          <Button
            className="btn btn-danger"
            onClick={handleDeleteContact}
            disabled={!user || !selectedContact}
          >
            Borrar
          </Button>
        </Col>
      </Row>

      <AddAgenda
        show={showAddModal}
        handleClose={() => setShowAddModal(false)}
        selectedContact={selectedContact}
        refreshContacts={fetchContacts}
        isEditMode={isEditMode}
      />

      <Modal show={showSignInModal} onHide={() => setShowSignInModal(false)}>
        <Modal.Body>
          <SignIn onClose={() => setShowSignInModal(false)} />
        </Modal.Body>
      </Modal>
    </Container>
  );
}

export default App;
