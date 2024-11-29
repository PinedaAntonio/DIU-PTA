package com.example.hotel.Modelo;

import com.example.hotel.Cliente;
import com.example.hotel.Modelo.Repository.Impl.ClienteRepositoryImpl;
import com.example.hotel.Util.ClienteUtil;
import com.example.hotel.Reserva;
import com.example.hotel.Modelo.Repository.Impl.ReservaRepositoryImpl;
import com.example.hotel.Util.ReservaUtil;

import java.util.ArrayList;

public class HotelModelo {

    private ArrayList<ClienteVO> clientesVO = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
    private ClienteUtil clienteUtil;
    private ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private ArrayList<ReservaVO> reservasVO = new ArrayList<>();
    private ReservaUtil reservaUtil;

    // Constructor que inicializa personUtil
    public HotelModelo() {
        this.clienteUtil = new ClienteUtil();
        this.reservaUtil = new ReservaUtil();
    }

    public void setClienteRepository(ClienteRepositoryImpl clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void setReservaRepository(ReservaRepositoryImpl reservaRepository) {
        this.reservaRepository = reservaRepository;
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

    public ArrayList<Reserva> mostrarReservas(String dni_cliente) {
        try {
            reservasVO = reservaRepository.obtenerReservasPorDni(dni_cliente);
            reservas = reservaUtil.getReservas(reservasVO);
        } catch (ExcepcionHotel e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public void nuevaReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        reservaRepository.addReserva(reservaVO);
    }
    public void editarReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        System.out.println(reservaVO.getFecha_Llegada().toString());
        System.out.println(reservaVO.getFecha_Salida().toString());
        reservaRepository.editReserva(reservaVO);
    }
    public void borrarReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        reservaRepository.deleteReserva(reservaVO.getId());
    }

    public ArrayList<Reserva>  mostrarReservasAll() {
        ArrayList<ReservaVO> reservas = reservaRepository.obtenerTodasLasReservas();
        return reservaUtil.getReservas(reservas);
    }

    public ArrayList<Reserva>  mostrarReservasActivas() {
        ArrayList<ReservaVO> reservas = reservaRepository.obtenerReservasActivas();
        return reservaUtil.getReservas(reservas);
    }
}
