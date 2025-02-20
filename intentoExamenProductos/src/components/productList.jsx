import React, { useEffect, useState } from "react";
import { getAllProducts } from "../services/api.services.js";
import { Container, Row, Col, Table, Button, ProgressBar } from "react-bootstrap";
import "../App.css"

export default function ProductList() {
  const [products, setProducts] = useState([]); // Estado para los productos
  const [total, setTotal] = useState(0); // Estado para el total de la compra
  const [selectedProduct, setSelectedProduct] = useState(null); // Estado para el producto seleccionado
  const [progress, setProgress] = useState(0); // Estado para el progreso de la barra

  function handleCalcularTotal() {
    let newTotal = 0;
    products.forEach(product => {
      newTotal += product.price;
    });
    setTotal(newTotal); // Usamos setTotal para actualizar el estado
  }

  useEffect(() => {
    getAllProducts()
      .then((response) => {
        console.log("Productos obtenidos:", response.data); // Depuración
        setProducts(response.data); // Almacena los productos en el estado
      })
      .catch((error) => {
        console.error("Error al obtener productos:", error);
      });
  }, []);

  useEffect(() => {
    // Calcular el stock total y el progreso
    const totalStock = products.reduce((acc, product) => acc + product.stock, 0);
    const progressValue = Math.min((totalStock / 1000) * 100, 100); // Máximo de 100%
    setProgress(progressValue);
  }, [products]);

  return (
    <Container fluid className="app-container">
      {/* Barra de Progreso */}
      <ProgressBar now={progress} max={100} label={`${Math.round(progress)}%`} />
      
      <Row className="app-row">
        {/* Lista de Productos */}
        <Col md={6} className="product-list">
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Precio</th>
              </tr>
            </thead>
            <tbody>
              {products.length > 0 ? (
                products.map((product) => (
                  <tr
                    key={product.id}
                    onClick={() => setSelectedProduct(product)}
                    className={selectedProduct?.id === product.id ? "active" : ""}
                  >
                    <td>{product.name}</td>
                    <td>${product.price.toFixed(2)}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="2">No hay productos disponibles</td>
                </tr>
              )}
            </tbody>
          </Table>
        </Col>

        {/* Detalles del Producto Seleccionado */}
        <Col md={6} className="product-details">
          <h2>Producto seleccionado</h2>
          {selectedProduct ? (
            <div>
              <p>
                <strong>Nombre:</strong> {selectedProduct.name}
              </p>
              <p>
                <strong>Precio:</strong> ${selectedProduct.price.toFixed(2)}
              </p>
              <p>
                <strong>Stock:</strong> {selectedProduct.stock}
              </p>
            </div>
          ) : (
            <p>Selecciona un producto</p>
          )}
        </Col>
      </Row>

      <div className="buttons">
        <Button
          className="btn btn-primary"
          onClick={handleCalcularTotal}
        >
          Calcular total
        </Button>
      </div>

      <h3>Total: ${total.toFixed(2)}</h3>
    </Container>
  );
}
