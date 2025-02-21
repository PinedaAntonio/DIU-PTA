import { useState } from "react";

import ConversorMonedas from "./components/ConversorMonedas";
import "./App.css";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <ConversorMonedas></ConversorMonedas>
    </>
  );
}

export default App;
