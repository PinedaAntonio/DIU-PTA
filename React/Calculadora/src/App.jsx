import { useState } from "react";
import * as math from "mathjs";
import "./App.css";
import Calculadora from "./components/Calculadora";

function App() {
  // Hacemos dos variables para poder alternar entre la expresión y el resultado
  const [expresion, setExpression] = useState(""); // Estado para manejar la expresión actual
  const [result, setResult] = useState("0"); // Estado para manejar el resultado calculado

  const handleInput = (input) => {
    if (input === "toggleSign") {
      if (expresion === "") return;
      const nuevaExpresion = expresion.startsWith("-")
        ? expresion.slice(1)
        : "-" + expresion;
      setExpression(nuevaExpresion);
    } else {
      setExpression((prev) => prev + input); // Concatenar la entrada a la expresión actual
    }
  };

  const handleEquals = () => {
    try {
      const resultado = math.evaluate(expresion); // Evaluar la expresión con math.js
      setResult(resultado.toString()); // Actualizar el resultado
      setExpression(resultado.toString()); // Reiniciar la expresión con el resultado
    } catch (error) {
      setResult("Error"); // Mostrar "Error" en caso de una expresión inválida
      setExpression("");
    }
  };

  const handleClear = () => {
    setExpression("");
    setResult("0");
  };

  return (
    <div className="app">
      <div className="display">
        <h1>{expresion || result}</h1> {/* Mostrar la expresión o el resultado */}
      </div>
      <Calculadora
        onInput={handleInput}
        onEquals={handleEquals}
        onClear={handleClear}
      />
    </div>
  );
}

export default App;
