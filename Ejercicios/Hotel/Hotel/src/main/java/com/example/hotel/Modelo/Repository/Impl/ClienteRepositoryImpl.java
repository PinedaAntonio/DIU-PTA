package com.example.hotel.Modelo.Repository.Impl;

import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.Repository.ClienteRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private String sentencia2;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO cliente;

    @Override
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM clientes";
            this.sentencia2 = "SELECT * FROM reservas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);
            ResultSet rs2 = this.stmt.executeQuery(this.sentencia2);

            while(rs.next()) {
                String n = rs.getString("Nombre");
                String a = rs.getString("Apellidos");
                String d = rs.getString("Direccion");
                String l = rs.getString("Localidad");
                String p = rs.getString("Provincia");
                String dni = rs.getString("Dni");
                //ArrayList<Reserva> r = rs2.getArray(); insertar reservas
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
            this.sentencia = "INSERT INTO clientes (Dni, Nombre, Apellidos, Direccion, Localidad, Provincia) VALUES ('" + c.getDni() + "','" + c.getNombre() + "','" + c.getApellidos() + "','" + c.getDireccion()+ "','" + c.getLocalidad() + "','" + c.getProvincia() +"');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deleteCliente(String var1) throws ExcepcionHotel {

    }

    @Override
    public void editCliente(ClienteVO var1) throws ExcepcionHotel {

    }

    @Override
    public int lastId() throws ExcepcionHotel {
        return 0;
    }
}
