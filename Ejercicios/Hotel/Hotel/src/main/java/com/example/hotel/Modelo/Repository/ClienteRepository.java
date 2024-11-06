package com.example.hotel.Modelo.Repository;

import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionCliente;

import java.util.ArrayList;

public interface ClienteRepository {
    ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionCliente;

    void addCliente(ClienteVO var1) throws ExcepcionCliente;

    void deleteCliente(String var1) throws ExcepcionCliente;

    void editCliente(ClienteVO var1) throws ExcepcionCliente;

    int lastId() throws ExcepcionCliente;
}
