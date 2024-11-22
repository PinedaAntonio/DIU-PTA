package com.example.agenda.model.repository.impl;

import com.example.agenda.model.ExcepcionArticulo;
import com.example.agenda.model.ArticuloVO;
import com.example.agenda.model.repository.ArticuloRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticuloRepositoryImpl implements ArticuloRepository {
    private final ConexionCatalogo conexion = new ConexionCatalogo(); // Gestión de la conexión a la base de datos.
    private Statement stmt; // Objeto para ejecutar sentencias SQL.
    private String sentencia; // Almacena las sentencias SQL a ejecutar.
    private ArrayList<ArticuloVO> articulos; // Lista para guardar los artículos obtenidos de la base de datos.
    private ArticuloVO articulo; // Objeto que representa un artículo individual.

    public ArticuloRepositoryImpl() {
        // Constructor vacío.
    }

    // Obtiene una lista de todos los artículos almacenados en la base de datos.
    public ArrayList<ArticuloVO> ObtenerListaArticulos() throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD(); // Abre una conexión con la base de datos.
            this.articulos = new ArrayList<>(); // Inicializa la lista de artículos.
            this.stmt = conn.createStatement(); // Prepara la ejecución de sentencias SQL.
            this.sentencia = "SELECT * FROM articulo"; // Define la consulta SQL.
            ResultSet rs = this.stmt.executeQuery(this.sentencia); // Ejecuta la consulta y obtiene el resultado.

            while (rs.next()) { // Itera sobre los resultados.
                Integer codigo = rs.getInt("id");
                String Nombre = rs.getString("nombre");
                String Descripcion = rs.getString("descripcion");
                Double Precio = rs.getDouble("precio");
                Integer Stock = rs.getInt("stock");
                this.articulo = new ArticuloVO(codigo, Nombre, Descripcion, Precio, Stock); // Crea un objeto ArticuloVO con los datos.
                this.articulos.add(this.articulo); // Añade el artículo a la lista.
            }

            this.conexion.desconectarBD(conn); // Cierra la conexión con la base de datos.
            return this.articulos;
        } catch (SQLException var6) {
            throw new ExcepcionArticulo("No se ha podido realizar la operación"); // Manejo de errores.
        }
    }

    // Agrega un nuevo artículo a la base de datos.
    public void addArticulo(ArticuloVO p) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD(); // Conecta a la base de datos.
            this.stmt = conn.createStatement(); // Prepara la ejecución de sentencias SQL.
            this.sentencia = "INSERT INTO articulo (nombre, descripcion, precio, stock) VALUES ('" + p.getNombre() +
                    "','" + p.getDescripcion() + "','" + p.getPrecio() + "','" + p.getStock() + "');";
            this.stmt.executeUpdate(this.sentencia); // Ejecuta la sentencia SQL para insertar datos.
            this.stmt.close(); // Cierra el Statement.
            this.conexion.desconectarBD(conn); // Cierra la conexión con la base de datos.
        } catch (SQLException var3) {
            throw new ExcepcionArticulo("No se ha podido realizar la operación");
        }
    }

    // Elimina un artículo de la base de datos por su ID.
    public void deleteArticulo(Integer idArticulo) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD(); // Conecta a la base de datos.
            this.stmt = conn.createStatement(); // Prepara la ejecución de sentencias SQL.
            String sql = String.format("DELETE FROM articulo WHERE id = %d", idArticulo); // Sentencia SQL para eliminar.
            this.stmt.executeUpdate(sql); // Ejecuta la sentencia SQL.
            this.conexion.desconectarBD(conn); // Cierra la conexión con la base de datos.
        } catch (SQLException var5) {
            throw new ExcepcionArticulo("No se ha podido realizar la eliminación");
        }
    }

    // Edita un artículo existente en la base de datos.
    public void editArticulo(ArticuloVO articuloVO) throws ExcepcionArticulo {
        try {
            Connection conn = this.conexion.conectarBD(); // Conecta a la base de datos.
            this.stmt = conn.createStatement(); // Prepara la ejecución de sentencias SQL.
            String sql = String.format(
                    "UPDATE articulo SET nombre = '%s', descripcion = '%s', precio = '%s', stock = '%s' WHERE id = %d",
                    articuloVO.getNombre(), articuloVO.getDescripcion(), articuloVO.getPrecio(), articuloVO.getStock(), articuloVO.getId());
            this.stmt.executeUpdate(sql); // Ejecuta la sentencia SQL para actualizar.
        } catch (SQLException var4) {
            throw new ExcepcionArticulo("No se ha podido realizar la edición");
        }
    }

    // Obtiene el ID del último artículo insertado en la base de datos.
    public int lastId() throws ExcepcionArticulo {
        int lastPersonaId = 0;

        try {
            Connection conn = this.conexion.conectarBD(); // Conecta a la base de datos.
            Statement comando = conn.createStatement(); // Prepara la ejecución de sentencias SQL.
            ResultSet registro = comando.executeQuery("SELECT id FROM articulo ORDER BY id DESC LIMIT 1"); // Consulta para obtener el último ID.
            if (registro.next()) {
                lastPersonaId = registro.getInt("id"); // Asigna el ID obtenido.
            }
            return lastPersonaId;
        } catch (SQLException var5) {
            throw new ExcepcionArticulo("No se ha podido realizar la búsqueda del ID");
        }
    }
}
