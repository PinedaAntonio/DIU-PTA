import logo from './logo.svg';
import './App.css';
import HelloComponent from './components/HelloComponent';
import { Component } from 'react';

class App extends Component {
  constructor(){
    super()
    this.state={
      name: "Antonio"
    }
  }

  changeName=(event)=>{
    this.setState({
      name: event.target.value
    })
  }

  render(){
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <HelloComponent nombre={this.state.name}></HelloComponent>
          <input value={this.state.name} onChange={this.changeName}></input>
        </header>
      </div>
    );
  }
}

export default App;
