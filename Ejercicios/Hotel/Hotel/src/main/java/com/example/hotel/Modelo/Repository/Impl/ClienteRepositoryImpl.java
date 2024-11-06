package com.example.hotel.Modelo.Repository.Impl;

import com.example.hotel.Modelo.ClienteVO;
import com.example.hotel.Modelo.ExcepcionCliente;
import com.example.hotel.Modelo.Repository.ClienteRepository;

import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    @Override
    public ArrayList<ClienteVO> ObtenerListaClientes() throws ExcepcionCliente {
        return null;
    }

    @Override
    public void addCliente(ClienteVO var1) throws ExcepcionCliente {

    }

    @Override
    public void deleteCliente(String var1) throws ExcepcionCliente {

    }

    @Override
    public void editCliente(ClienteVO var1) throws ExcepcionCliente {

    }

    @Override
    public int lastId() throws ExcepcionCliente {
        return 0;
    }
}
