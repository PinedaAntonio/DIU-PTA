import service from "../http-commons";

// Obtener todos los productos
export const getAllProducts = () => service.get("/products");

// Obtener un producto por ID
export const getProductById = (id) => service.get(`/products/${id}`);

// Actualizar un producto
export const updateProduct = (id, data) => service.put(`/products/${id}`, data);
