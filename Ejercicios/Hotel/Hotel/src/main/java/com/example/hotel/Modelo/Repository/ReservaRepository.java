package com.example.hotel.Modelo.Repository;

import com.example.hotel.Modelo.ExcepcionHotel;
import com.example.hotel.Modelo.ReservaVO;

import java.util.ArrayList;

public interface ReservaRepository {
    // Método modificado para obtener las reservas por DNI del cliente
    ArrayList<ReservaVO> obtenerReservasPorDni(String dni_cliente) throws ExcepcionHotel;

    void addReserva(ReservaVO var1) throws ExcepcionHotel;

    void deleteReserva(String var1) throws ExcepcionHotel;

    void editReserva(ReservaVO var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}

