package com.example.hotel.Util;

import com.example.hotel.Cliente;
import com.example.hotel.Modelo.ClienteVO;

import java.util.ArrayList;

public class ClienteUtil {
    public ArrayList<Cliente> getPersons(ArrayList<ClienteVO> listaVO) {
        ArrayList<Cliente> listaPerson = new ArrayList<>();
        for (ClienteVO clienteVO : listaVO) {
            Cliente cliente = new Cliente();

            // Asignación de campos desde PersonVO a Person
            cliente.setDni(clienteVO.getDni()); // Asegúrate de que se asigna el ID
            cliente.setNombre(clienteVO.getNombre());
            cliente.setApellidos(clienteVO.getApellidos());
            cliente.setDireccion(clienteVO.getDireccion());
            cliente.setLocalidad(clienteVO.getLocalidad());
            cliente.setProvincia(clienteVO.getProvincia());

            listaPerson.add(cliente);
        }
        return listaPerson;
    }


    public ArrayList<ClienteVO> getClientesVO(ArrayList<Cliente> listaClientes) {
        ArrayList<ClienteVO> listaClientesVO = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            ClienteVO clienteVO = new ClienteVO(
                    cliente.getDni(),
                    cliente.getNombre(),
                    cliente.getApellidos(),
                    cliente.getDireccion(),
                    cliente.getLocalidad(),
                    cliente.getProvincia()
            );
        }
        return listaClientesVO;
    }

    public ClienteVO convertToClienteVO(Cliente cliente) {
        return new ClienteVO(cliente.getDni(), cliente.getNombre(), cliente.getApellidos(),
                cliente.getDireccion(), cliente.getLocalidad(), cliente.getProvincia()); //Implementar reservas
    }

}