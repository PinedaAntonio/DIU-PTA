package com.example.hotel.Modelo.Repository.Impl;

import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.Repository.ReservaRepository;
import com.example.hotel.Modelo.ReservaVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                this.reserva = new ReservaVO(id, llegada, salida, tipoHabitacion, numHabitaciones, fumador, regimen, dniCliente);
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
    public void addReserva(ReservaVO var1) throws ExcepcionHotel {}

    @Override
    public void deleteReserva(String var1) throws ExcepcionHotel {}

    @Override
    public void editReserva(ReservaVO var1) throws ExcepcionHotel {}

    @Override
    public int lastId() throws ExcepcionHotel {
        return 0;
    }
}
