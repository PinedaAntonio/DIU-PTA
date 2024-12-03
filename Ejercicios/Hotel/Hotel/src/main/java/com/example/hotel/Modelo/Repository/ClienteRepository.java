package com.example.hotel.Modelo.Repository;

import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionHotel;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ClienteRepository {
    ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionHotel;

    void addCliente(ClienteVO var1) throws ExcepcionHotel;

    void deleteCliente(String var1) throws ExcepcionHotel;

    void editCliente(ClienteVO var1) throws ExcepcionHotel;

    ObservableList<ClienteVO> buscarClientesPorDni(String var1) throws ExcepcionHotel;

    int lastId() throws ExcepcionHotel;
}
