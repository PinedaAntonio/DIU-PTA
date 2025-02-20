
import React, { useState } from 'react';
import Contador from './components/Contador';

// Código con hooks

import './App.css';
function App() {
  const [num, setNum] = useState(0);

  const incrementar = () => setNum(num + 1);
  const decrementar = () => setNum(num - 1);
  const resetear = () => setNum(0);

  return (
    <div className="container-counter">
      {Contador}
      <Contador 
        num={num} 
        incrementar={incrementar} 
        decrementar={decrementar} 
        resetear={resetear}
      />
    </div>
  );
}

export default App;


/*
import React, { Component } from 'react';
import Contador from './components/Contador'
import './components/Contador.css';
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      num: 0,
    };

    this.incrementar = this.incrementar.bind(this);
    this.decrementar = this.decrementar.bind(this);
    this.resetear = this.resetear.bind(this);
  }

  // Funciones del contador (callbacks)
  incrementar() {
    this.setState((prevState) => ({ num: prevState.num + 1 }));
  }

  decrementar() {
    this.setState((prevState) => ({ num: prevState.num - 1 }));
  }

  resetear() {
    this.setState({ num: 0 });
  }

  render() {
    return (
      <div>
        <Contador
          num={this.state.num}
          incrementar={this.incrementar}
          decrementar={this.decrementar}
          resetear={this.resetear}
          clase="incButton" 
        />
      </div>
    );
  }
}

export default App;
*/