package com.example.hotel.Modelo;

import com.example.hotel.Cliente;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import com.example.hotel.Util.ClienteUtil;

import java.util.ArrayList;

public class HotelModelo {

    private ArrayList<ClienteVO> clientesVO = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
    private ClienteUtil clienteUtil;

    // Constructor que inicializa personUtil
    public HotelModelo() {
        this.clienteUtil = new ClienteUtil();

    }

    public void setClienteRepository(ClienteRepositoryImpl clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ArrayList<Cliente> mostrarClientes() {
        try {
            clientesVO = clienteRepository.ObtenerListaClientes();
            clientes = clienteUtil.getPersons(clientesVO);
        } catch (ExcepcionHotel e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void nuevaPerson(ClienteVO clienteVO) throws ExcepcionHotel {
        clienteRepository.addCliente(clienteVO);
    }
    public void editarPerson(ClienteVO clienteVO) throws ExcepcionHotel {
        clienteRepository.editCliente(clienteVO);
    }
    public void borrarPerson(ClienteVO clienteVO) throws ExcepcionHotel {
        clienteRepository.deleteCliente(clienteVO.getDni());
    }

    public ArrayList<ClienteVO> obtenerPersonas() throws ExcepcionHotel {
        ArrayList<ClienteVO> clientesVO = clienteRepository.ObtenerListaClientes();
        return clientesVO;
    }
}
