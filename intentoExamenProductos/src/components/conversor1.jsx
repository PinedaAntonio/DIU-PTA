import React, { useState, useEffect } from "react";
import { getAllProducts } from "../services/api.services";

export default function Conversor1() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [selectedProduct2, setSelectedProduct2] = useState(null);
  const [inputValue, setInputValue] = useState("");
  const [result, setResult] = useState(0);

  useEffect(() => {
    getAllProducts().then((response) => {
      setProducts(response.data);
    });
  }, []);

  const handleConvert = () => {
    const value = parseFloat(inputValue);
    if (!isNaN(value) && selectedProduct && selectedProduct2) {
      const conversion = (value * selectedProduct2.price) / selectedProduct.price;
      setResult(conversion.toFixed(2));
    } else {
      setResult("Ingrese un valor válido");
    }
  };

  return (
    <div>
      <h1>Conversor de monedas
      </h1>

      {/* Input para ingresar el valor a convertir */}
      <div>
        <label htmlFor="value">Valor a convertir:</label>
        <input 
          type="number" 
          id="value" 
          value={inputValue} 
          onChange={(e) => setInputValue(e.target.value)} 
        />
      </div>

      <div style={{ display: "flex", justifyContent: "space-around" }}>
        {/* Tabla de Moneda de Origen */}
        <div>
          <h3>Moneda de origen</h3>
          <table border="1">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Precio</th>
              </tr>
            </thead>
            <tbody>
              {products.map((product) => (
                <tr 
                  key={product.id} 
                  onClick={() => setSelectedProduct(product)} 
                 
                >
                  <td>{product.name}</td>
                  <td>${product.price.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <p>Seleccionado: {selectedProduct ? selectedProduct.name : "Ninguno"}</p>
        </div>

        {/* Tabla de Moneda de Destino */}
        <div>
          <h3>Moneda de destino</h3>
          <table border="1">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Precio</th>
              </tr>
            </thead>
            <tbody>
              {products.map((product) => (
                <tr 
                  key={product.id} 
                  onClick={() => setSelectedProduct2(product)} 
                 
                >
                  <td>{product.name}</td>
                  <td>${product.price.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <p>Seleccionado: {selectedProduct2 ? selectedProduct2.name : "Ninguno"}</p>
        </div>
      </div>

      {/* Botón para convertir */}
      <div>
        <button onClick={handleConvert}>Convertir</button>
      </div>

      {/* Resultado */}
      <div>
        <p>Resultado: {result}</p>
      </div>
    </div>
  );
}
