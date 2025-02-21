import "../App.css";
import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Table, Button, Container, Form, Row, Col } from "react-bootstrap";
import ProductDataService from "../services/product.service";
import ProgressBar from "./progressBar";
import "../styles/ListProducts.css";
import "../styles/BuscarNombre.css";

const MAX_PRODUCTS = 100;

const ProductsList = () => {
  const [products, setProducts] = useState([]);
  const [allProducts, setAllProducts] = useState([]);
  const [currentProduct, setCurrentProduct] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(-1);
  const [searchName, setSearchName] = useState("");
  const [error, setError] = useState("");
  const [totalProducts, setTotalProducts] = useState(0);

  useEffect(() => {
    retrieveProducts();
  }, []);

  const retrieveProducts = () => {
    ProductDataService.findAll()
      .then((response) => {
        setProducts(response.data);
        setAllProducts(response.data);
        setTotalProducts(response.data.length);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const removeAllProducts = async () => {
    try {
      const response = await ProductDataService.findAll();
      const productsList = response.data;
      for (const product of productsList) {
        await ProductDataService.delete(product.id);
      }
      retrieveProducts();
    } catch (error) {
      console.error("Error al eliminar todos los productos:", error);
    }
  };

  const selectProduct = (product, index) => {
    setCurrentProduct(product);
    setCurrentIndex(index);
    if (!product.active) {
      setError("Producto inactivo");
    } else if (product.stock === 0) {
      setError("Producto agotado, stock = 0");
    } else {
      setError("");
    }
  };

  return (
    <Container className="list">
      <Row>
        <Col md={12} className="mb-3">
          <ProgressBar value={totalProducts} max={MAX_PRODUCTS} />
        </Col>
        <Col md={6}>
          <Form.Group controlId="searchName" className="mb-3">
            <Form.Control
              type="text"
              placeholder="Buscar producto por nombre..."
              value={searchName}
              onChange={(e) => setSearchName(e.target.value)}
              className="search-input"
            />
          </Form.Group>
          <h4>Lista de productos</h4>
          <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>Nombre</th>
              </tr>
            </thead>
            <tbody>
              {products.map((product, index) => (
                <tr
                  key={product.id}
                  onClick={() => selectProduct(product, index)}
                  className={index === currentIndex ? "table-active" : ""}
                >
                  <td>{product.name}</td>
                </tr>
              ))}
            </tbody>
          </Table>
          <Button variant="danger" onClick={removeAllProducts} className="mt-3">
            Eliminar todos
          </Button>
        </Col>
        <Col md={6}>
          {currentProduct ? (
            <div>
              <h4>Detalles del Producto</h4>
              <p>
                <strong>Nombre:</strong> {currentProduct.name}
              </p>
              <p>
                <strong>Stock:</strong> {currentProduct.stock}
              </p>
              <p>
                <strong>Marca:</strong> {currentProduct.brand}
              </p>
              <p>
                <strong>Precio:</strong> ${currentProduct.price}
              </p>
              <p>
                <strong>Estado:</strong>{" "}
                {currentProduct.active ? "Activo" : "Inactivo"}
              </p>
              {currentProduct.active && currentProduct.stock > 0 ? (
                <Button
                  as={Link}
                  to={`/products/${currentProduct.id}/buy`}
                  variant="success"
                  size="sm"
                  className="me-2"
                >
                  Comprar
                </Button>
              ) : (
                <Button variant="secondary" size="sm" className="me-2" disabled>
                  Comprar
                </Button>
              )}
              <Button
                as={Link}
                to={`/products/${currentProduct.id}`}
                variant="warning"
                size="sm"
                className="me-2"
              >
                Editar
              </Button>
              <Button
                as={Link}
                to={`/products/${currentProduct.id}/delete`}
                variant="danger"
                size="sm"
              >
                Eliminar
              </Button>
              {error && <p className="text-danger mt-2">{error}</p>}
            </div>
          ) : (
            <p>Pulsa en un producto para ver detalles...</p>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default ProductsList;
