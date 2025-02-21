import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import ProductList from "./components/productList";
import Conversor1 from "./components/conversor1";
import Conversor2 from "./components/conversor2";

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
      <Conversor2 />
    </>
  );
}

export default App;
