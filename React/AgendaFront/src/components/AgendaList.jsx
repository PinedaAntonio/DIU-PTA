import React from "react";
import { Col, ListGroup, Row } from "react-bootstrap";
import "../App.css";

const AgendaList = ({ contacts, setSelectedContact, selectedContact }) => {
  return (
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
  );
};

export default AgendaList;
