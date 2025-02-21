import "../styles/ConversorMonedas.css";
import ProgressBar from "./ProgressBar";

const MAX_PRODUCTS = 100;

const MonedaTable = ({
  title,
  products,
  onSelect,
  selected: selectedMoneda,
}) => {
  return (
    <div>
      <h3>{title}</h3>
      {products.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Precio</th>
              <th>Descripción</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr
                key={product.id}
                onClick={() => onSelect(product)}
                className={selectedMoneda?.id === product.id ? "selected" : ""}
              >
                <td>{product.name}</td>
                <td>${product.price.toFixed(2)}</td>
                <td>{product.brand}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Cargando productos...</p> // Mensaje de carga si aún no hay productos
      )}
      <p>
        Seleccionado: {selectedMoneda ? selectedMoneda.name : "Ninguna moneda"}
      </p>

      {selectedMoneda ? (
        <div>
          <ProgressBar value={selectedMoneda.stock} max={MAX_PRODUCTS} />
        </div>
      ) : (
        <div>
          <ProgressBar value={0} max={MAX_PRODUCTS} />
        </div>
      )}
    </div>
  );
};

export default MonedaTable;
