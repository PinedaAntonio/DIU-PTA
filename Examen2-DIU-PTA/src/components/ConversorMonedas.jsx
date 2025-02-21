import { useState, useEffect } from "react";
import React from "react";
import {
  getAllProducts,
  getProductById,
  updateProduct,
} from "../services/moneda-services";
import "../styles/ConversorMonedas.css";
import MonedaTable from "./ProductTable";

export default function ConversorMonedas() {
  const [monedas, setMonedas] = useState([]);
  const [selectedMoneda, setSelectedMoneda] = useState(null);
  const [cantidad, setCantidad] = useState("");
  const [equivalencia, setEquivalencia] = useState("");

  useEffect(() => {
    getAllProducts().then((response) => setMonedas(response.data));
  }, []);

  const handleConversion = () => {
    if (!selectedMoneda) return;

    if (cantidad) {
      if (cantidad < selectedMoneda.stock && selectedMoneda.active) {
        setEquivalencia(
          (parseFloat(cantidad) * selectedMoneda.price).toFixed(2)
        );
      } else {
        console.log("Cantidad superior al stock de la moneda");
      }
    }
  };

  const handleCantidad = (e) => {
    const value = e.target.value;
    if (selectedMoneda) {
      if (value === "" || parseFloat(value) >= 0) {
        if (selectedMoneda.active) {
          setCantidad(value);
          setEquivalencia("");
        }
      }
    }
  };

  const handleEquivalencia = (e) => {
    const value = e.target.value;
    if (selectedMoneda) {
      if (value === "" || parseFloat(value) >= 0) {
        if (selectedMoneda.active) {
          setEquivalencia(value);
        }
      }
    }
  };

  return (
    <div className="conversor-container">
      <h1>Conversor de monedas</h1>

      <div className="tables-container">
        <MonedaTable
          products={monedas}
          onSelect={setSelectedMoneda}
          selected={selectedMoneda}
        />
      </div>

      <div className="input-container">
        <div className="input-group">
          <label htmlFor="eur">Cantidad</label>
          <input
            type="number"
            id="eur"
            value={cantidad}
            min="0"
            onChange={handleCantidad}
          />
          {selectedMoneda && selectedMoneda.stock > 100 ? (
            <div className="llena">Reserva llena</div>
          ) : (
            <div className="nollena">Reserva no llena</div>
          )}
        </div>

        <div className="input-group">
          <label htmlFor="currency">Equivalencia en euros:</label>
          <input
            type="number"
            id="currency"
            value={equivalencia}
            min="0"
            onChange={handleEquivalencia}
          />
        </div>
      </div>

      <div>
        <button
          onClick={handleConversion}
          disabled={!selectedMoneda || (!cantidad && !equivalencia)}
        >
          Convertir
        </button>
      </div>
    </div>
  );
}
