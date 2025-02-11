import { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container } from "react-bootstrap";
import AgendaList from "./components/AgendaList";

function App() {
  return (
    <Container className="app-container">
      <h1 className="text-center my-4">Agenda App</h1>
      <AgendaList />
    </Container>
  );
}

export default App;
