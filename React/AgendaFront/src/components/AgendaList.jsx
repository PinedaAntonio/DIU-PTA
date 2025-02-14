import React from "react";
import { Col } from "react-bootstrap";
import "../App.css";

const AgendaList = ({ selectedContact }) => {
  return (
    <Col md={4} className="contact-details">
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
        </div>
      ) : (
        <p>Selecciona un contacto</p>
      )}
    </Col>
  );
};

export default AgendaList;
