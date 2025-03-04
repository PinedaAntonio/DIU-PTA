import React, { useState } from "react";
import { auth } from "../firebase"; // Asegúrate de que la ruta sea correcta
import { signInWithEmailAndPassword } from "firebase/auth";
import { Button, Form } from "react-bootstrap";

function SignIn({ onClose }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const signInWithEmailAndPasswordHandler = async (e) => {
    e.preventDefault();
    try {
      await signInWithEmailAndPassword(auth, email, password);
      onClose(); // Cierra el modal al iniciar sesión correctamente
    } catch (error) {
      setError("Error al iniciar sesión: " + error.message);
    }
  };

  return (
    <div>
      <h2>Iniciar sesión</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <Form onSubmit={signInWithEmailAndPasswordHandler}>
        <Form.Group>
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            placeholder="Ingresa tu email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
        <Form.Group>
          <Form.Label>Contraseña</Form.Label>
          <Form.Control
            type="password"
            placeholder="Ingresa tu contraseña"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Button variant="primary" type="submit">
          Ingresar
        </Button>
        <Button variant="secondary" onClick={onClose} className="ms-2">
          Cerrar
        </Button>
      </Form>
    </div>
  );
}

export default SignIn;
