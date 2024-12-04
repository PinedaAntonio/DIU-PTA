package com.example.hotel.Modelo.Repository.Impl;

import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.Repository.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    @Override
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellidos");
                String d = rs.getString("Direccion");
                String l = rs.getString("Localidad");
                String p = rs.getString("Provincia");
                String dni = rs.getString("Dni");
                this.cliente = new ClienteVO(dni, n, a, d, l, p);
                this.clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public void addCliente(ClienteVO c) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO clientes (Dni, Nombre, Apellidos, Direccion, Localidad, Provincia) VALUES ('" + c.getDni() + "','" + c.getNombre() + "','" + c.getApellidos() + "','" + c.getDireccion() + "','" + c.getLocalidad() + "','" + c.getProvincia() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }
    @Override
    public void deleteCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM clientes WHERE Dni = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dni);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ExcepcionHotel("No se encontró ningún cliente con el Dni especificado.");
            }
            pstmt.close();
            this.conexion.desconectarBD(conn);

        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación: " + e.getMessage());
        }
    }

    @Override
    public void editCliente(ClienteVO clienteVO) throws ExcepcionHotel {
        Connection conn = null;
        try {
            conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();

            // Verifica que el Dni no sea nulo ni vacío
            if (clienteVO.getDni() == null || clienteVO.getDni().trim().isEmpty()) {
                throw new ExcepcionHotel("DNI de la persona no es válido: " + clienteVO.getDni());
            }

            // Prepara la consulta SQL usando String.format, asegurando que los valores de tipo String estén entre comillas
            String sql = String.format(
                    "UPDATE clientes SET  Nombre = '%s', Apellidos = '%s', Direccion = '%s', Localidad = '%s', Provincia = '%s' WHERE Dni = '%s'",
                    clienteVO.getNombre(),
                    clienteVO.getApellidos(),
                    clienteVO.getDireccion(),
                    clienteVO.getLocalidad(),  // asumiendo que Localidad es String
                    clienteVO.getProvincia(),
                    clienteVO.getDni()
            );


            int rowsAffected = this.stmt.executeUpdate(sql);

            // Verifica si alguna fila fue afectada
            if (rowsAffected == 0) {
                throw new ExcepcionHotel("No se encontró la persona con el Dni: " + clienteVO.getDni());
            }

        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la edición: " + e.getMessage());
        } catch (Exception e) {
            throw new ExcepcionHotel("No se ha podido realizar la edición: " + e.getMessage());
        } finally {
            if (conn != null) {
                this.conexion.desconectarBD(conn);
            }
        }
    }

    // En ClienteRepositoryImpl.java
    public ObservableList<ClienteVO> buscarClientesPorDni(String id) {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes where Dni = '" + id + "';";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellidos");
                String d = rs.getString("Direccion");
                String l = rs.getString("Localidad");
                String p = rs.getString("Provincia");
                String dni = rs.getString("Dni");
                this.cliente = new ClienteVO(dni, n, a, d, l, p);
                this.clientes.add(this.cliente);
            }

            this.conexion.desconectarBD(conn);
            ObservableList<ClienteVO> observableClientes = FXCollections.observableArrayList(this.clientes);
            return observableClientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }


    @Override
    public int lastId() throws ExcepcionHotel {
        return 0;
    }
}