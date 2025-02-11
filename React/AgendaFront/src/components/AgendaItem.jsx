import React from "react";
import { Card, ListGroup } from "react-bootstrap";

const AgendaItem = ({ agenda }) => {
  return (
    <Card className="mb-3">
      <Card.Body>
        <Card.Title>
          {agenda.firstName} {agenda.lastName}
        </Card.Title>
        <Card.Text>
          <strong>Dirección:</strong> {agenda.street}, {agenda.city} -{" "}
          {agenda.postalCode}
          <br />
          <strong>Fecha de Nacimiento:</strong> {agenda.birthday}
          <br />
          <strong>Tutoriales:</strong> {agenda.tutorials?.join(", ") || "N/A"}
        </Card.Text>
      </Card.Body>
    </Card>
  );
};

export default AgendaItem;
