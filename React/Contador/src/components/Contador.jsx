//import React from 'react';


//Código con hooks
/*
function Contador({ num, incrementar, decrementar, resetear }) {
  return (
    <>
      <div className="counter">{num}</div>
      <div className="buttons">
        <button className="incButton" onClick={incrementar}>Incre</button>
        <button onClick={decrementar}>Decre</button>
        <button onClick={resetear}>Reseteo</button>
      </div>
    </>
  );
}

export default Contador;
*/
import React, { Component } from 'react';
class Contador extends Component {
  render() {
    const { num, incrementar, decrementar, resetear, clase } = this.props;

    return (
      <div className="container-counter">
        <div className="counter">{num}</div>
        <div className="buttons">
          <button className={clase} onClick={incrementar}>
            Incrementar
          </button>
          <button onClick={decrementar}>Decrementar</button>
          <button onClick={resetear}>Reseteo</button>
        </div>
      </div>
    );
  }
}

export default Contador;