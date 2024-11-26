package com.example.hotel.Util;

import com.example.hotel.Modelo.ReservaVO;
import com.example.hotel.Modelo.Repository.Impl.regimen;
import com.example.hotel.Modelo.Repository.Impl.tipoHabitacion;
import com.example.hotel.Reserva;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaUtil {

    // Método para convertir una lista de ReservaVO a una lista de Reserva
    public ArrayList<Reserva> getReservas(ArrayList<ReservaVO> listaVO) {
        ArrayList<Reserva> listaReservas = new ArrayList<>();
        for (ReservaVO reservaVO : listaVO) {
            Reserva reserva = convertirReservaVOAReserva(reservaVO);
            listaReservas.add(reserva);
        }
        return listaReservas;
    }

    // Método para convertir una lista de Reserva a una lista de ReservaVO
    public ArrayList<ReservaVO> getReservasVO(ArrayList<Reserva> listaReservas) {
        ArrayList<ReservaVO> listaReservasVO = new ArrayList<>();
        for (Reserva reserva : listaReservas) {
            ReservaVO reservaVO = convertirReservaAReservaVO(reserva);
            listaReservasVO.add(reservaVO);
        }
        return listaReservasVO;
    }

    // Método para convertir un objeto Reserva a un objeto ReservaVO
    public ReservaVO convertirReservaAReservaVO(Reserva reserva) {
        int id = reserva.getId();
        LocalDate fechaLlegada = reserva.getFecha_Llegada();
        LocalDate fechaSalida = reserva.getFecha_Salida();
        tipoHabitacion tipoHabitacionEnum = tipoHabitacion.valueOf(reserva.getTipo_Habitacion().toUpperCase().replace(" ", "_"));
        int nHabitaciones = reserva.getNHabitaciones();
        boolean fumador = reserva.isFumador();
        regimen regimenEnum = regimen.valueOf(reserva.getRegimen().toUpperCase());
        String dniCliente = reserva.getDni_Cliente();

        return new ReservaVO(id, fechaLlegada, fechaSalida, nHabitaciones, tipoHabitacionEnum, fumador, regimenEnum, dniCliente);
    }

    // Método para convertir un objeto ReservaVO a un objeto Reserva
    public Reserva convertirReservaVOAReserva(ReservaVO reservaVO) {
        SimpleIntegerProperty id = new SimpleIntegerProperty(reservaVO.getId());
        ObjectProperty<LocalDate> fechaLlegada = new SimpleObjectProperty<>(reservaVO.getFecha_Llegada());
        ObjectProperty<LocalDate> fechaSalida = new SimpleObjectProperty<>(reservaVO.getFecha_Salida());
        SimpleStringProperty tipoHabitacion = new SimpleStringProperty(reservaVO.getTipo_Habitacion().name().replace("_", " "));
        SimpleIntegerProperty nHabitaciones = new SimpleIntegerProperty(reservaVO.getNHabitaciones());
        SimpleBooleanProperty fumador = new SimpleBooleanProperty(reservaVO.isFumador());
        SimpleStringProperty regimen = new SimpleStringProperty(reservaVO.getRegimen().name());
        SimpleStringProperty dniCliente = new SimpleStringProperty(reservaVO.getDni_Cliente());

        return new Reserva(id, fechaLlegada, fechaSalida, nHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
    }
}
