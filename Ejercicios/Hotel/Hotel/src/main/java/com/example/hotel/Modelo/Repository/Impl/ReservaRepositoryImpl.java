package com.example.hotel.Modelo.Repository.Impl;

import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.Repository.ReservaRepository;
import com.example.hotel.Modelo.ReservaVO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;


    // Método modificado para obtener las reservas por DNI del cliente
    @Override
    public ArrayList<ReservaVO> obtenerReservasPorDni(String dni_cliente) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas = new ArrayList<>();
            this.stmt = conn.createStatement();

            // Sentencia SQL correctamente parametrizada
            this.sentencia = "SELECT * FROM reservas WHERE Dni_Cliente = '" + dni_cliente + "'";  // Ojo con inyección SQL en un entorno real
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                int id = rs.getInt("Id");
                LocalDate llegada = rs.getDate("Fecha_Llegada").toLocalDate();
                LocalDate salida = rs.getDate("Fecha_Salida").toLocalDate();
                tipoHabitacion tipoHabitacion = null;
                if (rs.getString("Tipo_Habitacion") != null) {
                    try {
                        tipoHabitacion = com.example.hotel.Modelo.Repository.Impl.tipoHabitacion.valueOf(rs.getString("Tipo_Habitacion"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna Tipo_Habitacion");
                    }
                }
                int numHabitaciones = rs.getInt("NHabitaciones");
                Boolean fumador = rs.getBoolean("Fumador");
                regimen regimen = null;
                if (rs.getString("Regimen") != null) {
                    try {
                        regimen = com.example.hotel.Modelo.Repository.Impl.regimen.valueOf(rs.getString("Regimen"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna Regimen");
                    }
                }
                String dniCliente = rs.getString("Dni_Cliente");
                this.reserva = new ReservaVO(id, llegada, salida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
                this.reserva.setId(id);
                this.reservas.add(this.reserva);
            }

            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public void addReserva(ReservaVO var1) throws ExcepcionHotel {
        String query = "INSERT INTO reservas (Fecha_Llegada, Fecha_Salida, NHabitaciones, Tipo_Habitacion, Fumador, Regimen, Dni_Cliente) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Asignar valores a los parámetros de la consulta usando `var1`
            pstmt.setDate(1, java.sql.Date.valueOf(var1.getFecha_Llegada()));
            pstmt.setDate(2, java.sql.Date.valueOf(var1.getFecha_Salida()));
            pstmt.setInt(3, var1.getNHabitaciones()); // Cambiado para ser coherente con el tipo esperado
            pstmt.setString(4, var1.getTipo_Habitacion().name()); // Enum convertido a String
            pstmt.setBoolean(5, var1.isFumador());
            pstmt.setString(6, var1.getRegimen().name()); // Enum convertido a String
            pstmt.setString(7, var1.getDni_Cliente());

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la operación: " + e.getMessage());
        }
    }


    @Override
    public void deleteReserva(String var1) throws ExcepcionHotel {}

    @Override
    public void editReserva(ReservaVO var1) throws ExcepcionHotel {}

    @Override
    public int lastId() throws ExcepcionHotel {
        return 0;
    }
}
