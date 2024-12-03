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
            pstmt.setInt(3, var1.getNHabitaciones());
            pstmt.setString(4, var1.getTipo_Habitacion().name());
            pstmt.setBoolean(5, var1.isFumador());
            pstmt.setString(6, var1.getRegimen().name());
            pstmt.setString(7, var1.getDni_Cliente());

            // Ejecutar la consulta
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la operación: " + e.getMessage());
        }
    }


    @Override
    public void deleteReserva(int id) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            String sql = "DELETE FROM reservas WHERE Id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ExcepcionHotel("No se encontró ninguna reserva con el Id especificado.");
            }
            pstmt.close();
            this.conexion.desconectarBD(conn);

        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la eliminación: " + e.getMessage());
        }
    }


    @Override
    public void editReserva(ReservaVO var1) throws ExcepcionHotel {
        String query = "UPDATE reservas SET Fecha_Llegada = ?, Fecha_Salida = ?, NHabitaciones = ?, " +
                "Tipo_Habitacion = ?, Fumador = ?, Regimen = ?, Dni_Cliente = ? WHERE Id = ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Asignar valores a los parámetros de la consulta usando `var1`
            pstmt.setDate(1, java.sql.Date.valueOf(var1.getFecha_Llegada()));
            pstmt.setDate(2, java.sql.Date.valueOf(var1.getFecha_Salida()));
            pstmt.setInt(3, var1.getNHabitaciones());
            pstmt.setString(4, var1.getTipo_Habitacion().name());
            pstmt.setBoolean(5, var1.isFumador());
            pstmt.setString(6, var1.getRegimen().name());
            pstmt.setString(7, var1.getDni_Cliente());
            pstmt.setInt(8, var1.getId());

            // Ejecutar la consulta
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new ExcepcionHotel("No se encontró una reserva con el ID especificado.");
            }
        } catch (SQLException e) {
            throw new ExcepcionHotel("No se ha podido realizar la operación: " + e.getMessage());
        }
    }

    public ArrayList<ReservaVO> obtenerTodasLasReservas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            ArrayList<ReservaVO> reservas = new ArrayList<>();
            this.stmt = conn.createStatement();

            // Sentencia SQL para obtener todas las reservas
            this.sentencia = "SELECT * FROM reservas";
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
                ReservaVO reserva = new ReservaVO(id, llegada, salida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
                reservas.add(reserva);
            }

            this.conexion.desconectarBD(conn);
            return reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public ArrayList<ReservaVO> obtenerReservasActivas() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            ArrayList<ReservaVO> reservas = new ArrayList<>();
            this.stmt = conn.createStatement();

            LocalDate today = LocalDate.now();

            this.sentencia = "SELECT * FROM reservas WHERE Fecha_Llegada <= '" + today + "' AND Fecha_Salida >= '" + today + "'";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                int id = rs.getInt("Id");
                LocalDate llegada = rs.getDate("Fecha_Llegada").toLocalDate();
                LocalDate salida = rs.getDate("Fecha_Salida").toLocalDate();
                tipoHabitacion tipoHabitacion = null;
                if (rs.getString("Tipo_Habitacion") != null) {
                    try {
                        tipoHabitacion = tipoHabitacion.valueOf(rs.getString("Tipo_Habitacion"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna Tipo_Habitacion");
                    }
                }
                int numHabitaciones = rs.getInt("NHabitaciones");
                Boolean fumador = rs.getBoolean("Fumador");
                regimen regimen = null;
                if (rs.getString("Regimen") != null) {
                    try {
                        regimen = regimen.valueOf(rs.getString("Regimen"));
                    } catch (IllegalArgumentException e) {
                        throw new ExcepcionHotel("Valor no válido en columna Regimen");
                    }
                }
                String dniCliente = rs.getString("Dni_Cliente");
                ReservaVO reserva = new ReservaVO(id, llegada, salida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
                reservas.add(reserva);
            }

            this.conexion.desconectarBD(conn);
            return reservas;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    @Override
    public int lastId() throws ExcepcionHotel {
        return 0;
    }
}
