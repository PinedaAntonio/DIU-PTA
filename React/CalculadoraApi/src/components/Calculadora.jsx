import React, { useState } from "react";
import 'bootstrap/dist/css/bootstrap.css';
import "./Calculadora.css";

function Calculadora({ onInput, onEquals, onClear }) {
  const [actualizar, setActualizar] = useState(false); // Variable para controlar la escritura en caso de pulsar "="

  const handleNumberClick = (num) => {
    if (actualizar) {
      onClear();  // Limpiar la pantalla cuando se presiona un número después de "="
      setActualizar(false);  // Resetear el estado de "actualizar", así permitimos que se pueda seguir escribiendo
    }
    onInput(num);
  };

  const handleOperationClick = (op) => {
    onInput(op);
    setActualizar(false);
  };

  const handleToggleSign = () => {
    onInput("toggleSign");
  };

  const handleDecimalClick = () => {
    onInput(".");
  };

  const handleClearClick = () => {
    onClear();
    setActualizar(false);  // Resetear estado de "actualizar" al limpiar
  };

  const handleEqualsClick = () => {
    onEquals();
    setActualizar(true);  // Al pulsar el igual, se setea a true el "actualizar"
  };

  return (
    <div className="calculadora container-fluid">
      <div className="row">
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={handleClearClick}>AC</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={handleToggleSign}>+/-</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleOperationClick("%")}>%</button>
        </div>
        <div className="col-3">
          <button className="btn btn-warning w-100" onClick={() => handleOperationClick("/")}>/</button>
        </div>
      </div>
      <div className="row">
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("7")}>7</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("8")}>8</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("9")}>9</button>
        </div>
        <div className="col-3">
          <button className="btn btn-warning w-100" onClick={() => handleOperationClick("*")}>X</button>
        </div>
      </div>
      <div className="row">
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("4")}>4</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("5")}>5</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("6")}>6</button>
        </div>
        <div className="col-3">
          <button className="btn btn-warning w-100" onClick={() => handleOperationClick("-")}>-</button>
        </div>
      </div>
      <div className="row">
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("1")}>1</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("2")}>2</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("3")}>3</button>
        </div>
        <div className="col-3">
          <button className="btn btn-warning w-100" onClick={() => handleOperationClick("+")}>+</button>
        </div>
      </div>
      <div className="row">
        <div className="col-6">
          <button className="btn btn-light w-100" onClick={() => handleNumberClick("0")}>0</button>
        </div>
        <div className="col-3">
          <button className="btn btn-light w-100" onClick={handleDecimalClick}>.</button>
        </div>
        <div className="col-3">
          <button className="btn btn-warning w-100" onClick={handleEqualsClick}>=</button>
        </div>
      </div>
    </div>
  );
}

export default Calculadora;
