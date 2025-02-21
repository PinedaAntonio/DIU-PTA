import "./App.css";
import React, { Component } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import { Navbar, Nav, Container } from "react-bootstrap";

import AddProduct from "./components/add-producto";
import EditProduct from "./components/edit-producto";
import ProductsList from "./components/product-list";
import DeleteProduct from "./components/delete-producto";
import BuyProduct from "./components/buy-producto";

class App extends Component {
  render() {
    return (
      <div className="app-container">
        <Navbar bg="dark" variant="dark" expand="lg" className="mb-3">
          <Container>
            <Navbar.Brand as={Link} to="/products">
              Productos
            </Navbar.Brand>
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/products">
                Productos
              </Nav.Link>
              <Nav.Link as={Link} to="/add">
                Añadir
              </Nav.Link>
            </Nav>
          </Container>
        </Navbar>

        <Container>
          <Routes>
            <Route path="/" element={<ProductsList />} />
            <Route path="/products" element={<ProductsList />} />
            <Route path="/add" element={<AddProduct />} />
            <Route path="/products/:id" element={<EditProduct />} />
            <Route path="/products/:id/delete" element={<DeleteProduct />} />
            <Route path="/products/:id/buy" element={<BuyProduct />} />
          </Routes>
        </Container>
      </div>
    );
  }
}

export default App;
