import { useState, useEffect } from "react";
import React from "react";
import { getAllProducts } from "../services/api.services";

export default function Conversor2() {
    const [products, setProducts] = useState([]);
    const [selectedProduct, setSelectedProduct] = useState(null);
    const [eurValue, setEurValue] = useState("");
    const [currencyValue, setCurrencyValue] = useState("");

    useEffect(() => {
        getAllProducts().then((response) => {
            setProducts(response.data);
        });
    }, []);

    const handleConvert = () => {
        if (!selectedProduct) return;
        
        if (selectedProduct.name.toLowerCase() === "euro") {
            setCurrencyValue(eurValue);
            return;
        }

        if (eurValue) {
            setCurrencyValue((parseFloat(eurValue) * selectedProduct.price).toFixed(2));
        } else if (currencyValue) {
            setEurValue((parseFloat(currencyValue) / selectedProduct.price).toFixed(2));
        }
    };

    const handleEurChange = (e) => {
        setEurValue(e.target.value);
        setCurrencyValue("");
    };

    const handleCurrencyChange = (e) => {
        setCurrencyValue(e.target.value);
        setEurValue("");
    };

    return (
        <div>
            <h1>Conversor de monedas</h1>
            <div style={{ display: "flex", justifyContent: "space-around" }}>
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
            </div>
            <div>
                <label htmlFor="eur">EUR:</label>
                <input
                    type="number"
                    id="eur"
                    value={eurValue}
                    onChange={handleEurChange}
                />
            </div>
            <div>
                <label htmlFor="currency">{selectedProduct ? selectedProduct.name : "Otra moneda"}:</label>
                <input
                    type="number"
                    id="currency"
                    value={currencyValue}
                    onChange={handleCurrencyChange}
                />
            </div>
            <button onClick={handleConvert}>Convertir</button>
        </div>
    );
}
