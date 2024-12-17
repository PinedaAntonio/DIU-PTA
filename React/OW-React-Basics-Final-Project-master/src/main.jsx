import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { PokemonProviderWrapper } from './context/pokemon.context.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <PokemonProviderWrapper>
      <App />
    </PokemonProviderWrapper>
  </React.StrictMode>,
)
