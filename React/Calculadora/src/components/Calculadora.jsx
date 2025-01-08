import React from "react";
import "./Calculadora.css";

function Calculadora() {
  return (
    <span>
      <table>
        <tr>
            <td className="gray" colSpan={4}>
                <div>0</div>
            </td>
        </tr>
        <tr>
          <td>
            <button>AC</button>
          </td>
          <td>
            <button>+/-</button>
          </td>
          <td>
            <button>%</button>
          </td>
          <td className="orange">
            <button>/</button>
          </td>
        </tr>
        <tr>
          <td>
            <button>7</button>
          </td>
          <td>
            <button>8</button>
          </td>
          <td>
            <button>9</button>
          </td>
          <td className="orange">
            <button>X</button>
          </td>
        </tr>
        <tr>
          <td>
            <button>4</button>
          </td>
          <td>
            <button>5</button>
          </td>
          <td>
            <button>6</button>
          </td>
          <td className="orange">
            <button>-</button>
          </td>
        </tr>
        <tr>
          <td>
            <button>1</button>
          </td>
          <td>
            <button>2</button>
          </td>
          <td>
            <button>3</button>
          </td>
          <td className="orange">
            <button>+</button>
          </td>
        </tr>
        <tr>
          <td colSpan={2}>
            <button>0</button>
          </td>
          <td>
            <button>.</button>
          </td>
          <td className="orange">
            <button>=</button>
          </td>
        </tr>
      </table>
    </span>
  );
}

export default Calculadora;
